package com.caterpillar.exam.dto;

import lombok.Data;
import java.util.List;

@Data
public class CriteriaDTO {
    private String criteria;
    private List<ValuesDTO> values;
}
