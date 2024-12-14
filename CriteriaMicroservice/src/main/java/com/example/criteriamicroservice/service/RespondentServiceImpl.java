package com.example.criteriamicroservice.service;

import com.example.criteriamicroservice.entity.CriteriaType;
import com.example.criteriamicroservice.entity.Respondent;
import com.example.criteriamicroservice.repository.RespondentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class RespondentServiceImpl implements RespondentService {
    @Autowired
    private RespondentRepository respondentRepository;

    @Override
    public Respondent saveRespondentS(Respondent respondent) {
        return respondentRepository.save(respondent);
    }

    @Override
    public List<Respondent> fetchRespondentList() {
        return (List<Respondent>) respondentRepository.findAll();
    }

    @Override
    public Respondent findRespondentById(Long id) {
        Respondent respondentDB = respondentRepository.findById(id).orElse(null);
        return respondentDB;
    }

    @Override
    public Respondent updateRespondent(Respondent respondent, Long id) {
        Respondent respondentDB = respondentRepository.findById(id).orElse(null);
        if (Objects.nonNull(respondentDB)) {
            respondentDB.setDepartment(respondent.getDepartment());
            respondentDB.setFirstName(respondent.getFirstName());
            respondentDB.setPosition(respondent.getPosition());
            respondentDB.setSurName(respondent.getSurName());
            respondentDB.setLastName(respondent.getLastName());
            respondentDB.setSurveyResults(respondent.getSurveyResults());
            return respondentRepository.save(respondentDB);
        } else {
            return null;
        }
    }

    @Override
    public void deleteRespondent(Long id) {
        respondentRepository.deleteById(id);
    }
}
