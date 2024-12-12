package com.example.criteriamicroservice.controller;

import com.example.criteriamicroservice.entity.LifecycleStage;
import com.example.criteriamicroservice.service.LifecycleStageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LifecycleStageController {
    @Autowired
    private LifecycleStageService lifecycleStageService;
    @PostMapping("/lifecycleStages")
    public LifecycleStage saveLifecycleStage(@RequestBody LifecycleStage lifecycleStage)
    {
        return lifecycleStageService.saveLifecycleStage(lifecycleStage);
    }
    @GetMapping("/lifecycleStages")
    public List<LifecycleStage> fetchLifecycleStageList()
    {
        return lifecycleStageService.fetchLifecycleStageList();
    }
    // Update operation
    @PutMapping("/lifecycleStages/{id}")
    public LifecycleStage updateLifecycleStage(@RequestBody LifecycleStage lifecycleStage, @PathVariable("id") Long ID)
    {
        return lifecycleStageService.updateLifecycleStage(lifecycleStage, ID);
    }
    // Delete operation
    @DeleteMapping("/lifecycleStages")
    public String deleteLifecycleStage(@PathVariable("id") Long ID)
    {
        LifecycleStage lifecycleStage = lifecycleStageService.findLifecycleStageById(ID);
        lifecycleStageService.deleteLifecycleStage(lifecycleStage);
        return "Deleted Successfully";
    }
}
