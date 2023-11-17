package com.caterpillar.exam.requests;

import lombok.Data;

@Data
public class UpdateCriteriaRequest {
    private String oldCriteriaName;
    private String newCriteriaName;
}
