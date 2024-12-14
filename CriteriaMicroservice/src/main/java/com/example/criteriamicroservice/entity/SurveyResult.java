package com.example.criteriamicroservice.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "survey_result", uniqueConstraints = {@UniqueConstraint(columnNames = {"respondent_id", "scale_parameter_id", "question_id", "inProgress", "survey_id" })})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SurveyResult {
    @Id
    @Column(unique=true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnoreProperties
    @ManyToOne
    @JoinColumn(name = "respondent_id", nullable = false)
    private Respondent respondent;


    @JsonIgnoreProperties
    @ManyToOne
    @JoinColumn(name = "survey_id", nullable = false)
    private Survey survey;


    @JsonIgnoreProperties
    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false)
    private SurveyQuestion question;

    @JsonIgnoreProperties
    @ManyToOne
    @JoinColumn(name = "scale_parameter_id", nullable = false)
    private ScaleParameter selectedParameter; // Ответ — это параметр из шкалы

    private Boolean inProgress; // Флаг для отслеживания завершения опроса
    private String verdict;

    public Survey getSurvey() {
        return survey;
    }

    public void setSurvey(Survey survey) {
        this.survey = survey;
    }

    public Long getId() {
        return id;
    }

    public String getVerdict() {
        return verdict;
    }

    public void setVerdict(String verdict) {
        this.verdict = verdict;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getInProgress() {
        return inProgress;
    }

    public Respondent getRespondent() {
        return respondent;
    }

    public SurveyQuestion getQuestion() {
        return question;
    }

    public ScaleParameter getSelectedParameter() {
        return selectedParameter;
    }

    public void setQuestion(SurveyQuestion question) {
        this.question = question;
    }

    public void setInProgress(Boolean inProgress) {
        this.inProgress = inProgress;
    }

    public void setRespondent(Respondent respondent) {
        this.respondent = respondent;
    }

    public void setSelectedParameter(ScaleParameter selectedParameter) {
        this.selectedParameter = selectedParameter;
    }
}
