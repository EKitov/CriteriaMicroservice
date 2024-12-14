package com.example.criteriamicroservice.controller;

import com.example.criteriamicroservice.entity.CriteriaType;
import com.example.criteriamicroservice.entity.Respondent;
import com.example.criteriamicroservice.service.RespondentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RespondentController {
    @Autowired
    private RespondentService respondentService;
    @PostMapping("/respondents")
    public Respondent saveRespondent(@RequestBody Respondent respondent)
    {
        return respondentService.saveRespondentS(respondent);
    }
    @GetMapping("/respondents")
    public List<Respondent> fetchRespondent()
    {
        return respondentService.fetchRespondentList();
    }
    @GetMapping("/respondents/{id}")
    public Respondent findRespondentById(@PathVariable("id") Long id) {
        return respondentService.findRespondentById(id);
    }
    // Update operation
    @PutMapping("/respondents/{id}")
    public Respondent updateRespondent(@RequestBody Respondent respondent, @PathVariable("id") Long ID)
    {
        return respondentService.updateRespondent(respondent, ID);
    }
    // Delete operation
    @DeleteMapping("/respondents/{id}")
    public String deleteRespondent(@PathVariable("id") Long ID)
    {
        respondentService.deleteRespondent(ID);
        return "Deleted Successfully";
    }
}
