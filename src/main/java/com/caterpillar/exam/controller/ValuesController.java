package com.caterpillar.exam.controller;

import com.caterpillar.exam.dto.ValuesDTO;
import com.caterpillar.exam.requests.CreateValueRequest;
import com.caterpillar.exam.requests.UpdateValuesRequest;
import com.caterpillar.exam.service.impl.ValuesServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/values")
public class ValuesController {

    private final ValuesServiceImpl valuesService;

    @Autowired
    public ValuesController(ValuesServiceImpl valuesService) {
        this.valuesService = valuesService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<ValuesDTO>> getAllValues() {
        return ResponseEntity.ok(valuesService.getAllValues());
    }

    @PostMapping("/create")
    public ResponseEntity<ValuesDTO> createValue(@RequestBody CreateValueRequest request) {
        return ResponseEntity.ok(valuesService.createValue(request.getValue()));
    }

    @PostMapping("/update")
    public ResponseEntity<ValuesDTO> updateValue(@RequestBody UpdateValuesRequest request) {
        return ResponseEntity.ok(valuesService.updateValue(request.getOldValue(), request.getNewValue()));
    }
}
