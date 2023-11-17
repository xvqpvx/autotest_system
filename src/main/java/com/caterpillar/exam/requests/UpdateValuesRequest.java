package com.caterpillar.exam.requests;

import lombok.Data;

@Data
public class UpdateValuesRequest {
    private String oldValue;
    private String newValue;
}
