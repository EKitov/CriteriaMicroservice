package com.example.criteriamicroservice.service;

import com.example.criteriamicroservice.entity.*;
import com.example.criteriamicroservice.repository.CriteriaDirectoryRepository;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CriteriaDirectoryServiceImpl implements CriteriaDirectoryService {
    @Autowired
    private CriteriaDirectoryRepository criteriaDirectoryRepository;

    @Override
    public CriteriaDirectory saveCriteriaDirectory(CriteriaDirectory criteriaDirectory) {
        return  criteriaDirectoryRepository.save(criteriaDirectory);
    }

    @Override
    public List<CriteriaDirectory> fetchCriteriaDirectoryList() {
        return (List<CriteriaDirectory>) criteriaDirectoryRepository.findAll();
    }

    @Override
    public List<CriteriaDirectory> findByEvaluationScale(EvaluationScale scaleParam) {
        return criteriaDirectoryRepository.findAll()
                .stream()
                .filter(criteriaDirectory -> criteriaDirectory.getCriterionScale().equals(scaleParam))
                .collect(Collectors.toList());
    }

    @Override
    public List<CriteriaDirectory> findByCriteriaType(CriteriaType criteriaType) {
        return criteriaDirectoryRepository.findAll()
                .stream()
                .filter(criteriaDirectory -> criteriaDirectory.getCriterionType().equals(criteriaType))
                .collect(Collectors.toList());
    }

    @Override
    public List<CriteriaDirectory> findByLifecycleStage(LifecycleStage lifecycleStage) {
        return criteriaDirectoryRepository.findAll()
                .stream()
                .filter(criteriaDirectory -> criteriaDirectory.getCriterionStage().equals(lifecycleStage))
                .collect(Collectors.toList());
    }

    @Override
    public List<CriteriaDirectory> findByScaleAndType(EvaluationScale scaleParam, CriteriaType criteriaType) {
        return criteriaDirectoryRepository.findAll()
                .stream()
                .filter(criteriaDirectory -> criteriaDirectory.getCriterionScale().equals(scaleParam) &&
                        criteriaDirectory.getCriterionType().equals(criteriaType))
                .collect(Collectors.toList());
    }

    @Override
    public List<CriteriaDirectory> findByScaleAndStage(EvaluationScale scale, LifecycleStage lifecycleStage) {
        return criteriaDirectoryRepository.findAll()
                .stream()
                .filter(criteriaDirectory -> criteriaDirectory.getCriterionScale().equals(scale) &&
                        criteriaDirectory.getCriterionStage().equals(lifecycleStage))
                .collect(Collectors.toList());
    }

    @Override
    public List<CriteriaDirectory> findByTypeAndStage(CriteriaType criteriaType, LifecycleStage lifecycleStage) {
        return criteriaDirectoryRepository.findAll()
                .stream()
                .filter(criteriaDirectory -> criteriaDirectory.getCriterionStage().equals(lifecycleStage) &&
                        criteriaDirectory.getCriterionType().equals(criteriaType))
                .collect(Collectors.toList());
    }

    @Override
    public List<CriteriaDirectory> findByCombined(EvaluationScale evaluationScale, LifecycleStage lifecycleStage, CriteriaType criteriaType) {
        return criteriaDirectoryRepository.findAll()
                .stream()
                .filter(criteriaDirectory -> criteriaDirectory.getCriterionScale().equals(evaluationScale) &&
                        criteriaDirectory.getCriterionType().equals(criteriaType) &&
                        criteriaDirectory.getCriterionStage().equals(lifecycleStage))
                .collect(Collectors.toList());
    }

    @Override
    public CriteriaDirectory findCriteriaDirectoryById(Long id) {
        CriteriaDirectory criteriaDirectoryDB = criteriaDirectoryRepository.findById(id).orElse(null);
        return criteriaDirectoryDB;
    }

    @Override
    public CriteriaDirectory updateCriteriaDirectory(CriteriaDirectory criteriaDirectory, Long id) {
        CriteriaDirectory criteriaDirectoryDB = criteriaDirectoryRepository.findById(id).orElse(null);
        if (Objects.nonNull(criteriaDirectoryDB)) {
            criteriaDirectoryDB.setCriteria(criteriaDirectory.getCriteria());
            criteriaDirectoryDB.setCriterionScale(criteriaDirectory.getCriterionScale());
            criteriaDirectoryDB.setCriterionStage(criteriaDirectory.getCriterionStage());
            criteriaDirectoryDB.setFormulation(criteriaDirectory.getFormulation());
            criteriaDirectoryDB.setShortname(criteriaDirectory.getShortname());
            criteriaDirectoryDB.setWeight(criteriaDirectory.getWeight());
            criteriaDirectoryDB.setCriterionType(criteriaDirectory.getCriterionType());
            return criteriaDirectoryRepository.save(criteriaDirectoryDB);
        } else {
            return null;
        }
    }

    @Override
    public void deleteCriteriaDirectory(CriteriaDirectory criteriaDirectory) {
        criteriaDirectoryRepository.delete(criteriaDirectory);
    }
    @Override
    public void loadFromCsv(MultipartFile file) throws Exception {
        try (CSVReader reader = new CSVReader(new InputStreamReader(file.getInputStream()))) {
            List<String[]> rows = reader.readAll();
            for (String[] row : rows) {
                if (row.length != 6) {
                    throw new IllegalArgumentException("Неверный формат CSV");
                }
                CriteriaDirectory criteriaDirectory = new CriteriaDirectory();
                criteriaDirectory.setFormulation(row[0]);
                criteriaDirectory.setWeight(Double.parseDouble(row[1]));
                criteriaDirectory.setShortname(row[2]);
                // Установка CriterionType
                CriteriaType criteriaType = new CriteriaType();
                criteriaType.setId(Long.parseLong(row[3]));
                criteriaDirectory.setCriterionType(criteriaType);

                // Установка CriterionStage
                LifecycleStage lifecycleStage = new LifecycleStage();
                lifecycleStage.setId(Long.parseLong(row[4]));
                criteriaDirectory.setCriterionStage(lifecycleStage);

                // Установка CriterionScale
                EvaluationScale evaluationScale = new EvaluationScale();
                evaluationScale.setId(Long.parseLong(row[5]));
                criteriaDirectory.setCriterionScale(evaluationScale);
                criteriaDirectoryRepository.save(criteriaDirectory);
            }
        }
    }

    @Override
    public byte[] exportToCsv() throws Exception {
        List<CriteriaDirectory> criteriaDirectories = criteriaDirectoryRepository.findAll();
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             CSVWriter writer = new CSVWriter(new OutputStreamWriter(baos))) {
            writer.writeNext(new String[]{"Formulation", "Weight", "Shortname", "TypeId", "StageId", "ScaleId"});
            for (CriteriaDirectory directory : criteriaDirectories) {
                writer.writeNext(new String[]{
                        directory.getFormulation(),
                        directory.getWeight().toString(),
                        directory.getShortname(),
                        directory.getCriterionType().getId().toString(),
                        directory.getCriterionStage().getId().toString(),
                        directory.getCriterionScale().getId().toString()
                });
            }
            writer.flush();
            return baos.toByteArray();
        }
    }
}
