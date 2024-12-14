package com.example.criteriamicroservice.service;

import com.example.criteriamicroservice.entity.EvaluationParameter;
import com.example.criteriamicroservice.entity.Respondent;

import java.util.List;

public interface RespondentService {
    Respondent saveRespondentS(Respondent respondent);
    List<Respondent> fetchRespondentList();
    Respondent findRespondentById(Long id);
    Respondent updateRespondent(Respondent respondent, Long id);
    void deleteRespondent(Long id);
}
