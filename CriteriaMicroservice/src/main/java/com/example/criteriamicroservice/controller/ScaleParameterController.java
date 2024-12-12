package com.example.criteriamicroservice.controller;

import com.example.criteriamicroservice.entity.EvaluationParameter;
import com.example.criteriamicroservice.entity.EvaluationScale;
import com.example.criteriamicroservice.entity.ScaleParameter;
import com.example.criteriamicroservice.service.ScaleParameterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ScaleParameterController {
    @Autowired
    private ScaleParameterService scaleParameterService;
    @PostMapping("/scaleParameters")
    public ScaleParameter saveScaleParameter(@RequestBody ScaleParameter scaleParameter)
    {
        return scaleParameterService.saveScaleParameter(scaleParameter);
    }
    @GetMapping("/scaleParameters")
    public List<ScaleParameter> fetchScaleParameterList()
    {
        return scaleParameterService.fetchScaleParameterList();
    }
    @GetMapping("/scaleParameters/{id}")
    public ScaleParameter findScaleParameterById(@PathVariable("id") Long id) {
        return scaleParameterService.findScaleParameterById(id);
    }
    @GetMapping("/scaleParametersByScale/{scaleId}")
    public List<ScaleParameter> findByEvaluationScale(@PathVariable Long scaleId) {
        EvaluationScale scale = new EvaluationScale();
        scale.setId(scaleId);
        return scaleParameterService.findByEvaluationScale(scale);
    }
    @GetMapping("/scaleParametersByParam/{parameterId}")
    public List<ScaleParameter> getByParameter(@PathVariable Long parameterId) {
        EvaluationParameter parameter = new EvaluationParameter();
        parameter.setId(parameterId);
        return scaleParameterService.findByEvaluationParameter(parameter);
    }
    @GetMapping("/scaleParametersByScaleAndParameter")
    public List<ScaleParameter> getByScaleAndParameter(
            @RequestParam Long scaleId,
            @RequestParam Long parameterId) {
        EvaluationScale scale = new EvaluationScale();
        scale.setId(scaleId);
        EvaluationParameter parameter = new EvaluationParameter();
        parameter.setId(parameterId);
        return scaleParameterService.findByScaleAndParameter(scale, parameter);
    }
    // Update operation
    @PutMapping("/scaleParameters/{id}")
    public ScaleParameter updateScaleParameter(@RequestBody ScaleParameter scaleParameter, @PathVariable("id") Long ID)
    {
        return scaleParameterService.updateScaleParameter(scaleParameter, ID);
    }
    // Delete operation
    @DeleteMapping("/scaleParameters")
    public String deleteScaleParameter(@PathVariable("id") Long ID)
    {
        ScaleParameter scaleParameter = scaleParameterService.findScaleParameterById(ID);
        scaleParameterService.deleteScaleParameter(scaleParameter);
        return "Deleted Successfully";
    }
}
