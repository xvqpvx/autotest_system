package com.caterpillar.exam.dto;

import lombok.Data;
import java.util.List;

@Data
public class InputParamDTO {
    private String parameter;
    private List<CriteriaDTO> criterias;
}
