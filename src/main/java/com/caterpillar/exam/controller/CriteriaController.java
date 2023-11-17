package com.caterpillar.exam.controller;

import com.caterpillar.exam.dto.CriteriaDTO;
import com.caterpillar.exam.requests.*;
import com.caterpillar.exam.service.impl.CriteriaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/criteria")
public class CriteriaController {

    private final CriteriaServiceImpl criteriaService;

    @Autowired
    public CriteriaController(CriteriaServiceImpl criteriaService) {
        this.criteriaService = criteriaService;
    }

    @GetMapping("/getCriteria")
    public ResponseEntity<CriteriaDTO> getCriteria(@RequestParam("name") String name) {
        return ResponseEntity.ok(criteriaService.getCriteria(name));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<CriteriaDTO>> getAllCriteria() {
        return ResponseEntity.ok(criteriaService.getAllCriteria());
    }

    @PostMapping("/create")
    public ResponseEntity<CriteriaDTO> createCriteria(@RequestBody CreateCriteriaRequest request) {
        return ResponseEntity.ok(criteriaService.createCriteria(request.getCriteriaName()));
    }

    @PostMapping("/update")
    public ResponseEntity<CriteriaDTO> updateCriteria(@RequestBody UpdateCriteriaRequest request) {
        return ResponseEntity.ok(criteriaService.updateCriteria(request.getOldCriteriaName(),
                request.getNewCriteriaName()));
    }

    @PostMapping("/addValues")
    public ResponseEntity<CriteriaDTO> addValuesToCriteria(@RequestBody AddValuesRequest request) {
        CriteriaDTO criteriaDTO = criteriaService.addValueToCriteria(request.getCriteriaName(), request.getValuesList());
        if (criteriaDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(criteriaDTO);
    }

    @PostMapping("/changeValue")
    public ResponseEntity<CriteriaDTO> changeValueInCriteria(@RequestBody ChangeValueInCriteriaRequest request) {
        return ResponseEntity.ok(criteriaService.changeValueInCriteria(request.getCriteriaName(),
                request.getOldValue(), request.getNewValue()));
    }

}
