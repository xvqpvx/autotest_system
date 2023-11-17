package com.caterpillar.exam.controller;

import com.caterpillar.exam.dto.AutotestDTO;
import com.caterpillar.exam.service.impl.AutotestsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/autotest")
public class AutotestController {

    private final AutotestsServiceImpl autotestsServiceImpl;

    @Autowired
    public AutotestController(AutotestsServiceImpl autotestsServiceImpl) {
        this.autotestsServiceImpl = autotestsServiceImpl;
    }

    @GetMapping("/getAutotest")
    public ResponseEntity<AutotestDTO> getAutotest(@RequestParam("name") String name) {
        return ResponseEntity.ok(autotestsServiceImpl.getAutotest(name));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<AutotestDTO>> getAll() {
        return ResponseEntity.ok(autotestsServiceImpl.getAllAutotests());
    }

}
