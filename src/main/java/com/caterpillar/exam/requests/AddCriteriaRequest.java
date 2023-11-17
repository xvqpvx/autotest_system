package com.caterpillar.exam.requests;

import com.caterpillar.exam.model.Criteria;
import lombok.Data;

import java.util.List;

@Data
public class AddCriteriaRequest {
    private String paramName;
    private List<Criteria> criteriaList;
}
