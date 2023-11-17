package com.caterpillar.exam.service.impl;

import com.caterpillar.exam.dto.ValuesDTO;
import com.caterpillar.exam.model.Values;
import com.caterpillar.exam.repo.ValuesRepo;
import com.caterpillar.exam.service.ValuesService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ValuesServiceImpl implements ValuesService {

    private final ValuesRepo valuesRepo;
    private final ModelMapper modelMapper;

    @Autowired
    public ValuesServiceImpl(ValuesRepo valuesRepo, ModelMapper modelMapper) {
        this.valuesRepo = valuesRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<ValuesDTO> getAllValues() {
        List<Values> valuesList = valuesRepo.findAll();
        log.info("In getAllValues - found {} records", valuesList.size());
        return valuesList.stream()
                .map(values -> modelMapper.map(values, ValuesDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public ValuesDTO createValue(String value) {
        Values values = new Values();
        values.setValue(value);
        valuesRepo.save(values);
        return modelMapper.map(values, ValuesDTO.class);
    }

    @Override
    public ValuesDTO updateValue(String oldValue, String newValue) {
        Values value = valuesRepo.findValuesByValue(oldValue);
        if (value == null) {
            log.info("In updateValue - value with name {} not found", oldValue);
            return null;
        }
        value.setValue(newValue);
        valuesRepo.save(value);
        return modelMapper.map(value, ValuesDTO.class);
    }
}