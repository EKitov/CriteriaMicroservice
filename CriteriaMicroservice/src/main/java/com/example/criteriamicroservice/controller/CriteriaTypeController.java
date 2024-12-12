package com.example.criteriamicroservice.controller;

import com.example.criteriamicroservice.entity.CriteriaType;
import com.example.criteriamicroservice.service.CriteriaTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CriteriaTypeController {
    @Autowired
    private CriteriaTypeService criteriaTypeService;
    @PostMapping("/criteriaTypes")
    public CriteriaType saveCriteriaType(@RequestBody CriteriaType criteriaType)
    {
        return criteriaTypeService.saveCriteriaType(criteriaType);
    }
    @GetMapping("/criteriaTypes")
    public List<CriteriaType> fetchCriteriaTypeList()
    {
        return criteriaTypeService.fetchCriteriaTypeList();
    }
    @GetMapping("/criteriaTypes/{id}")
    public CriteriaType findCriteriaTypeById(@PathVariable("id") Long id) {
        return criteriaTypeService.findCriteriaTypeById(id);
    }
    // Update operation
    @PutMapping("/criteriaTypes/{id}")
    public CriteriaType updateCriteriaType(@RequestBody CriteriaType criteriaType, @PathVariable("id") Long ID)
    {
        return criteriaTypeService.updateCriteriaType(criteriaType, ID);
    }
    // Delete operation
    @DeleteMapping("/criteriaTypes")
    public String deleteCriteriaTypeById(@PathVariable("id") Long ID)
    {
        CriteriaType criteriaType = criteriaTypeService.findCriteriaTypeById(ID);
        criteriaTypeService.deleteCriteriaType(criteriaType);
        return "Deleted Successfully";
    }
}
