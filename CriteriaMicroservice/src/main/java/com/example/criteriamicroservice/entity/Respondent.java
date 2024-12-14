package com.example.criteriamicroservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Respondent {
    @Id
    @Column(unique=true, nullable = false)
    @GeneratedValue(strategy =
            GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String surName;
    private String lastName;
    @Column(nullable = false)
    private String department;
    @Column(nullable = false)
    private String position;
    @JsonIgnore
    @JsonManagedReference
    @OneToMany(mappedBy = "respondent", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SurveyResult> surveyResults;

    public void setId(Long id) {
        this.id = id;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public Long getId() {
        return id;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }

    public List<SurveyResult> getSurveyResults() {
        return surveyResults;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPosition() {
        return position;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setSurveyResults(List<SurveyResult> surveyResults) {
        this.surveyResults = surveyResults;
    }
}
