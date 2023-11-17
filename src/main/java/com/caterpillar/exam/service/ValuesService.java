package com.caterpillar.exam.service;

import com.caterpillar.exam.dto.ValuesDTO;

import java.util.List;

public interface ValuesService {
    List<ValuesDTO> getAllValues();
    ValuesDTO createValue(String value);
    ValuesDTO updateValue(String oldValue, String newValue);
}
