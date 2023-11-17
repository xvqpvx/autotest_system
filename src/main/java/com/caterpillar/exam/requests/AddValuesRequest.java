package com.caterpillar.exam.requests;

import com.caterpillar.exam.model.Values;
import lombok.Data;

import java.util.List;

@Data
public class AddValuesRequest {
    private String criteriaName;
    private List<Values> valuesList;
}
