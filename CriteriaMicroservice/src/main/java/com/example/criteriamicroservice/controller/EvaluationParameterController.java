package com.example.criteriamicroservice.controller;

import com.example.criteriamicroservice.entity.EvaluationParameter;
import com.example.criteriamicroservice.service.EvaluationParameterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EvaluationParameterController {
    @Autowired
    private EvaluationParameterService evaluationParameterService;
    @PostMapping(value ="/evaluationParameters")
    public EvaluationParameter saveEvaluationParameter(@RequestBody EvaluationParameter evaluationParameter)
    {
        return evaluationParameterService.saveEvaluationParameter(evaluationParameter);
    }
    @GetMapping("/evaluationParameters")
    public List<EvaluationParameter> fetchEvaluationParameterList()
    {
        return evaluationParameterService.fetchEvaluationParameterList();
    }
    @GetMapping("/evaluationParameters/{id}")
    public EvaluationParameter findEvaluationParameterById(@PathVariable("id") Long id) {
        return evaluationParameterService.findEvaluationParameterById(id);
    }
    // Update operation
    @PutMapping("/evaluationParameters/{id}")
    public EvaluationParameter updateEvaluationParameter(@RequestBody EvaluationParameter evaluationParameter, @PathVariable("id") Long ID)
    {
        return evaluationParameterService.updateEvaluationParameter(evaluationParameter, ID);
    }
    // Delete operation
    @DeleteMapping("/evaluationParameters/{id}")
    public String deleteEvaluationParameter(@PathVariable("id") Long ID)
    {
        evaluationParameterService.deleteEvaluationParameter(ID);
        return "Deleted Successfully";
    }
}
