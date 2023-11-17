package com.caterpillar.exam.dto;

import lombok.Data;
import java.util.List;

@Data
public class AutotestDTO {
    private String name;
    private String description;
    private List<InputParamDTO> parameters;
}