package com.caterpillar.exam.requests;

import lombok.Data;

@Data
public class ChangeValueInCriteriaRequest {
    private String criteriaName;
    private String oldValue;
    private String newValue;
}