package com.example.criteriamicroservice.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "scale_parameter", uniqueConstraints = {@UniqueConstraint(columnNames = {"param_order", "scale_id", "parameter_id" })})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScaleParameter {
    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy =
            GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Long param_order;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "scale_id", nullable = false)
    private EvaluationScale ScaleParam;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "parameter_id", nullable = false)
    private EvaluationParameter ScaleEvParam;
    @JsonManagedReference
    @OneToMany(mappedBy = "selectedParameter", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SurveyResult> surveyResults;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public EvaluationParameter getScaleEvParam() {
        return ScaleEvParam;
    }

    public EvaluationScale getScaleParam() {
        return ScaleParam;
    }

    public void setScaleEvParam(EvaluationParameter scaleEvParam) {
        ScaleEvParam = scaleEvParam;
    }


    public void setScaleParam(EvaluationScale scaleParam) {
        ScaleParam = scaleParam;
    }

    public void setSurveyResults(List<SurveyResult> surveyResults) {
        this.surveyResults = surveyResults;
    }

    public List<SurveyResult> getSurveyResults() {
        return surveyResults;
    }

    public Long getParam_order() {
        return param_order;
    }

    public void setParam_order(Long param_order) {
        this.param_order = param_order;
    }
}
