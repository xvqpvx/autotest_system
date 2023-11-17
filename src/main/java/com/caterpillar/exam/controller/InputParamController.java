package com.caterpillar.exam.controller;

import com.caterpillar.exam.dto.InputParamDTO;
import com.caterpillar.exam.requests.AddCriteriaRequest;
import com.caterpillar.exam.requests.CreateParamRequest;
import com.caterpillar.exam.requests.UpdateParamRequest;
import com.caterpillar.exam.service.impl.InputParamServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inputParam")
public class InputParamController {

    private final InputParamServiceImpl inputParamServiceImpl;

    @Autowired
    public InputParamController(InputParamServiceImpl inputParamServiceImpl) {
        this.inputParamServiceImpl = inputParamServiceImpl;
    }

    @PostMapping("/create")
    public ResponseEntity<InputParamDTO> createParam(@RequestBody CreateParamRequest request){
        return ResponseEntity.ok(inputParamServiceImpl.createParam(request.getParamName()));
    }

    @PostMapping("/update")
    public ResponseEntity<InputParamDTO> updateParam (@RequestBody UpdateParamRequest request){
        return ResponseEntity.ok(inputParamServiceImpl.updateParam(request.getOldParamName(), request.getNewParamName()));
    }

    @GetMapping("/getParam")
    public ResponseEntity<InputParamDTO> getInputParam(@RequestParam("name") String name){
        return ResponseEntity.ok(inputParamServiceImpl.getInputParam(name));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<InputParamDTO>> getAllParams(){
        return ResponseEntity.ok(inputParamServiceImpl.getAllInputParams());
    }

    @PostMapping("/addCriteria")
    public ResponseEntity<InputParamDTO> addCriteriaToParam(@RequestBody AddCriteriaRequest request) {
        InputParamDTO inputParamDTO = inputParamServiceImpl.addCriteriaToParam(request.getParamName(), request.getCriteriaList());
        if (inputParamDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(inputParamDTO);
    }

}
