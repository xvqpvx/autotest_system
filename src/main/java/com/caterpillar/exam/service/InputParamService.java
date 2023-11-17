package com.caterpillar.exam.service;

import com.caterpillar.exam.dto.InputParamDTO;
import com.caterpillar.exam.model.Criteria;

import java.util.List;

public interface InputParamService {
    InputParamDTO createParam(String paramName);
    InputParamDTO updateParam(String oldParam, String newParam);
    InputParamDTO getInputParam(String name);
    InputParamDTO addCriteriaToParam(String paramName, List<Criteria> criteriaList);
    List<InputParamDTO> getAllInputParams();
    List<InputParamDTO> getInputParamsByCriteria(List<String> criteriaList, int criteriaCount);
}
