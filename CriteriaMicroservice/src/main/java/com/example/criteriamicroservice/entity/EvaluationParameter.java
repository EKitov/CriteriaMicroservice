package com.example.criteriamicroservice.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EvaluationParameter {
    @Id
    @Column(unique=true, nullable = false)
    @GeneratedValue(strategy =
            GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String value;
    private String description;
    @JsonManagedReference
    @OneToMany(mappedBy = "ScaleEvParam", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ScaleParameter> scaleParameters;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setScaleParameters(List<ScaleParameter> scaleParameters) {
        this.scaleParameters = scaleParameters;
    }

    public List<ScaleParameter> getScaleParameters() {
        return scaleParameters;
    }

    public String getDescription() {
        return description;
    }

    public String getValue() {
        return value;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
