package com.caterpillar.exam.service;

import com.caterpillar.exam.dto.AutotestDTO;

import java.util.List;

public interface AutotestsService {
    AutotestDTO getAutotest(String name);
    List<AutotestDTO> getAllAutotests();
}
