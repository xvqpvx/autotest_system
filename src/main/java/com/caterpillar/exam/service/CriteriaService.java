package com.caterpillar.exam.service;

import com.caterpillar.exam.dto.CriteriaDTO;
import com.caterpillar.exam.model.Values;

import java.util.List;

public interface CriteriaService {
    CriteriaDTO createCriteria(String criteriaName);
    CriteriaDTO updateCriteria(String oldCriteria, String newCriteria);
    CriteriaDTO getCriteria(String criteriaName);
    CriteriaDTO addValueToCriteria(String criteriaName, List<Values> valuesList);
    List<CriteriaDTO> getAllCriteria();
    CriteriaDTO changeValueInCriteria(String criteriaName, String oldValue, String newValue);
}
