package com.caterpillar.exam.service.impl;

import com.caterpillar.exam.dto.AutotestDTO;
import com.caterpillar.exam.model.Autotest;
import com.caterpillar.exam.repo.AutotestRepo;
import com.caterpillar.exam.service.AutotestsService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AutotestsServiceImpl implements AutotestsService {

    private final AutotestRepo autotestRepo;
    private final ModelMapper modelMapper;

    @Autowired
    public AutotestsServiceImpl(AutotestRepo autotestRepo, ModelMapper modelMapper) {
        this.autotestRepo = autotestRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public AutotestDTO getAutotest(String name) {

        Autotest autotest = autotestRepo.findAutotestsByName(name);
        if (autotest == null) {
            log.info("In getAutotest - autotest with name {} not found", name);
            return null;
        }
        return modelMapper.map(autotest, AutotestDTO.class);
    }

    @Override
    public List<AutotestDTO> getAllAutotests() {

        List<Autotest> autotestList = autotestRepo.findAll();
        log.info("In getAllAutotests - found {} records", autotestList.size());
        return autotestList.stream()
                .map(autotest -> modelMapper.map(autotest, AutotestDTO.class))
                .collect(Collectors.toList());
    }
}
