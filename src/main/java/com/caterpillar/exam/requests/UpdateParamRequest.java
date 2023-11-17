package com.caterpillar.exam.requests;

import lombok.Data;

@Data
public class UpdateParamRequest {
    private String oldParamName;
    private String newParamName;
}
