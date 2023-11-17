package com.caterpillar.exam.service.impl;

import com.caterpillar.exam.dto.InputParamDTO;
import com.caterpillar.exam.model.Criteria;
import com.caterpillar.exam.model.InputParam;
import com.caterpillar.exam.repo.CriteriaRepo;
import com.caterpillar.exam.repo.InputParamRepo;
import com.caterpillar.exam.service.InputParamService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class InputParamServiceImpl implements InputParamService {

    private final InputParamRepo inputParamRepo;
    private final ModelMapper modelMapper;
    private final CriteriaRepo criteriaRepo;

    @Autowired
    public InputParamServiceImpl(InputParamRepo inputParamRepo, CriteriaRepo criteriaRepo, ModelMapper modelMapper) {
        this.inputParamRepo = inputParamRepo;
        this.criteriaRepo = criteriaRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public InputParamDTO createParam(String paramName) {
        InputParam inputParam = new InputParam();
        inputParam.setParameter(paramName);
        inputParamRepo.save(inputParam);
        log.info("In createParam - created new input parameter {}", inputParam);
        return modelMapper.map(inputParam, InputParamDTO.class);
    }

    @Override
    public InputParamDTO updateParam(String oldParam, String newParam) {
        InputParam inputParam = inputParamRepo.findInputParamByParameter(oldParam);
        if (inputParam == null) {
            log.info("In updateParam - input parameter with name {} not found", oldParam);
            return null;
        }
        inputParam.setParameter(newParam);
        inputParamRepo.save(inputParam);
        return modelMapper.map(inputParam, InputParamDTO.class);
    }

    @Override
    public InputParamDTO addCriteriaToParam(String paramName, List<Criteria> criteriaList) {
        InputParam inputParam = inputParamRepo.findInputParamByParameter(paramName);
        if (inputParam == null) {
            log.info("In addCriteriaToParam - input parameter with name {} not found", paramName);
            return null;
        }

        for (Criteria criteria : criteriaList) {
            Criteria existingCriteria = criteriaRepo.findByCriteria(criteria.getCriteria());
            if (existingCriteria != null) {
                if (!inputParam.getCriterias().contains(existingCriteria)) {
                    inputParam.getCriterias().add(existingCriteria);
                }
            } else {
                criteriaRepo.save(criteria);
                inputParam.getCriterias().add(criteria);
            }
        }

        inputParamRepo.save(inputParam);
        return modelMapper.map(inputParam, InputParamDTO.class);
    }

    @Override
    public InputParamDTO getInputParam(String paramName) {
        InputParam inputParam = inputParamRepo.findInputParamByParameter(paramName);
        if (inputParam == null) {
            log.info("In getInputParam - input parameter with name {} not found", paramName);
            return null;
        }
        return modelMapper.map(inputParam, InputParamDTO.class);
    }

    @Override
    public List<InputParamDTO> getAllInputParams() {
        List<InputParam> paramList = inputParamRepo.findAll();
        log.info("In getAllInputParams - found {} records", paramList.size());
        return paramList.stream()
                .map(inputParam -> modelMapper.map(inputParam, InputParamDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<InputParamDTO> getInputParamsByCriteria(List<String> criteriaList, int criteriaCount) {
        return null;
    }
}
