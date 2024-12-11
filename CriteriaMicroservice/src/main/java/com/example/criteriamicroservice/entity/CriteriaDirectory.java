package com.example.criteriamicroservice.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "criteria_directory", uniqueConstraints = {@UniqueConstraint(columnNames = {"formulation", "shortname", "type_id", "lifecycle_stage_id", "scale_id" })})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CriteriaDirectory {
    @Id
    @Column(unique=true, nullable = false)
    @GeneratedValue(strategy =
            GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String formulation;
    @Column(nullable = false)
    private Double weight;
    @Column(nullable = false)
    private String shortname;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "type_id", nullable = false)
    private CriteriaType CriterionType;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "lifecycle_stage_id", nullable = false)
    private LifecycleStage CriterionStage;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "scale_id", nullable = false)
    private EvaluationScale CriterionScale;
    @JsonManagedReference
    @OneToMany(mappedBy = "CriterionDirectory", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Criteria> criteria;

    public List<Criteria> getCriteria() {
        return criteria;
    }

    public void setCriteria(List<Criteria> criteria) {
        this.criteria = criteria;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CriteriaType getCriterionType() {
        return CriterionType;
    }

    public Double getWeight() {
        return weight;
    }

    public EvaluationScale getCriterionScale() {
        return CriterionScale;
    }

    public LifecycleStage getCriterionStage() {
        return CriterionStage;
    }

    public String getFormulation() {
        return formulation;
    }

    public String getShortname() {
        return shortname;
    }

    public void setCriterionScale(EvaluationScale criterionScale) {
        CriterionScale = criterionScale;
    }

    public void setCriterionStage(LifecycleStage criterionStage) {
        CriterionStage = criterionStage;
    }

    public void setCriterionType(CriteriaType criterionType) {
        CriterionType = criterionType;
    }

    public void setFormulation(String formulation) {
        this.formulation = formulation;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }
}
