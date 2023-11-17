package com.caterpillar.exam.service.impl;

import com.caterpillar.exam.dto.CriteriaDTO;
import com.caterpillar.exam.model.Criteria;
import com.caterpillar.exam.model.Values;
import com.caterpillar.exam.repo.CriteriaRepo;
import com.caterpillar.exam.repo.ValuesRepo;
import com.caterpillar.exam.service.CriteriaService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CriteriaServiceImpl implements CriteriaService {

    private final CriteriaRepo criteriaRepo;
    private final ModelMapper modelMapper;
    private final ValuesRepo valuesRepo;

    @Autowired
    public CriteriaServiceImpl(CriteriaRepo criteriaRepo, ValuesRepo valuesRepo, ModelMapper modelMapper) {
        this.criteriaRepo = criteriaRepo;
        this.modelMapper = modelMapper;
        this.valuesRepo = valuesRepo;
    }

    @Override
    public CriteriaDTO createCriteria(String criteriaName) {
        Criteria criteria = new Criteria();
        criteria.setCriteria(criteriaName);
        criteriaRepo.save(criteria);
        return modelMapper.map(criteria, CriteriaDTO.class);
    }

    @Override
    public CriteriaDTO updateCriteria(String oldCriteria, String newCriteria) {
        Criteria criteria = criteriaRepo.findByCriteria(oldCriteria);
        if (criteria == null) {
            log.info("In updateCriteria - criteria with name {} not found", oldCriteria);
            return null;
        }
        criteria.setCriteria(newCriteria);
        criteriaRepo.save(criteria);
        return modelMapper.map(criteria, CriteriaDTO.class);
    }

    @Override
    public CriteriaDTO getCriteria(String criteriaName) {
        Criteria criteria = criteriaRepo.findByCriteria(criteriaName);
        if (criteria == null) {
            log.info("In getCriteria - criteria with name {} not found", criteriaName);
            return null;
        }
        return modelMapper.map(criteria, CriteriaDTO.class);
    }

    @Override
    public CriteriaDTO addValueToCriteria(String criteriaName, List<Values> valuesList) {
        Criteria criteria = criteriaRepo.findByCriteria(criteriaName);
        if (criteria == null) {
            log.info("In addValuesToCriteria - criteria with name {} not found", criteriaName);
            return null;
        }

        for (Values values : valuesList) {
            Values existingValues = valuesRepo.findValuesByValue(values.getValue());
            if (existingValues != null) {
                if (!criteria.getValues().contains(existingValues)) {
                    criteria.getValues().add(existingValues);
                }
            } else {
                valuesRepo.save(values);
                criteria.getValues().add(values);
            }
        }

        criteriaRepo.save(criteria);
        return modelMapper.map(criteria, CriteriaDTO.class);
    }

    @Override
    public List<CriteriaDTO> getAllCriteria() {
        List<Criteria> criteriaList = criteriaRepo.findAll();
        log.info("In getAllCriteria - found {} records", criteriaList.size());
        return criteriaList.stream()
                .map(criteria -> modelMapper.map(criteria, CriteriaDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public CriteriaDTO changeValueInCriteria(String criteriaName, String oldValue, String newValue) {
        Criteria criteria = criteriaRepo.findByCriteria(criteriaName);
        if (criteria == null) {
            log.info("In changeValueInCriteria - criteria with name {} not found", criteriaName);
            return null;
        }

        List<Values> values = criteria.getValues();
        Values valueToUpdate = values.stream()
                .filter(value -> value.getValue().equals(oldValue))
                .findFirst()
                .orElse(null);

        if (valueToUpdate == null) {
            log.info("In changeValueInCriteria - value with name {} not found for criteria {}", oldValue, criteriaName);
            return null;
        }

        valueToUpdate.setValue(newValue);
        criteriaRepo.save(criteria);
        return modelMapper.map(criteria, CriteriaDTO.class);
    }
}
