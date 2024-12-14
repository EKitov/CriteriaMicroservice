package com.example.criteriamicroservice.controller;

import com.example.criteriamicroservice.entity.EvaluationScale;
import com.example.criteriamicroservice.entity.LifecycleStage;
import com.example.criteriamicroservice.service.EvaluationScaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EvaluationScaleController {
    @Autowired
    private EvaluationScaleService evaluationScaleService;
    @PostMapping("/evaluationScales")
    public EvaluationScale saveEvaluationScale(@RequestBody EvaluationScale evaluationScale)
    {
        return evaluationScaleService.saveEvaluationScale(evaluationScale);
    }
    @GetMapping("/evaluationScales")
    public List<EvaluationScale> fetchEvaluationScaleList()
    {
        return evaluationScaleService.fetchEvaluationScaleList();
    }
    @GetMapping("/evaluationScales/{id}")
    public EvaluationScale findEvaluationScaleById(@PathVariable("id") Long id) {
        return evaluationScaleService.findEvaluationScaleById(id);
    }
    // Update operation
    @PutMapping("/evaluationScales/{id}")
    public EvaluationScale updateEvaluationScale(@RequestBody EvaluationScale evaluationScale, @PathVariable("id") Long ID)
    {
        return evaluationScaleService.updateEvaluationScale(evaluationScale, ID);
    }
    // Delete operation
    @DeleteMapping("/evaluationScales/{id}")
    public String deleteEvaluationScale(@PathVariable("id") Long ID)
    {
        evaluationScaleService.deleteEvaluationScale(ID);
        return "Deleted Successfully";
    }
}
