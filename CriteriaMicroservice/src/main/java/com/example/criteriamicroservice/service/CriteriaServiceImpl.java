package com.example.criteriamicroservice.service;

import com.example.criteriamicroservice.entity.Criteria;
import com.example.criteriamicroservice.entity.CriteriaDirectory;
import com.example.criteriamicroservice.repository.CriteriaDirectoryRepository;
import com.example.criteriamicroservice.repository.CriteriaRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CriteriaServiceImpl implements CriteriaService {
    @Autowired
    private CriteriaRepository criteriaRepository;

    @Override
    public Criteria saveCriteria(Criteria criteria) {
        return criteriaRepository.save(criteria);
    }

    @Override
    public List<Criteria> fetchCriteriaList() {
        return criteriaRepository.findAll();
    }

    @Override
    public List<Criteria> findByCriteriaDirectory(CriteriaDirectory criteriaDirectory) {
        return criteriaRepository.findAll()
                .stream()
                .filter(criteria -> criteria.getCriterionDirectory().equals(criteriaDirectory))
                .collect(Collectors.toList());
    }

    @Override
    public Criteria findCriteriaById(Long id) {
        Criteria criteriaDB = criteriaRepository.findById(id).orElse(null);
        return criteriaDB;
    }

    @Override
    public Criteria updateCriteria(Criteria criteria, Long id) {
        Criteria criteriaDB = criteriaRepository.findById(id).orElse(null);
        if (Objects.nonNull(criteriaDB)) {
            criteriaDB.setName(criteria.getName());
            criteriaDB.setQuestions(criteria.getQuestions());
            criteriaDB.setValue(criteria.getValue());
            criteriaDB.setCriterionDirectory(criteria.getCriterionDirectory());
            return criteriaRepository.save(criteriaDB);
        } else {
            return null;
        }
    }

    @Override
    public void deleteCriteria(Long id) {
        criteriaRepository.deleteById(id);
    }
    @Override
    public void uploadCriteriaFromCsv(InputStream csvInputStream) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(csvInputStream))) {
            Iterable<CSVRecord> records = CSVFormat.DEFAULT
                    .withHeader("name", "value", "directoryId")
                    .withSkipHeaderRecord()
                    .parse(reader);

            List<Criteria> criteriaList = new ArrayList<>();
            for (CSVRecord record : records) {
                String name = record.get("name");
                String value = record.get("value");
                Long directoryId = Long.parseLong(record.get("directoryId"));

                // Создаем объект CriteriaDirectory без обращения к репозиторию
                CriteriaDirectory directory = new CriteriaDirectory();
                directory.setId(directoryId);

                // Создаем и заполняем объект Criteria
                Criteria criteria = new Criteria();
                criteria.setName(name);
                criteria.setValue(value);
                criteria.setCriterionDirectory(directory);

                criteriaList.add(criteria);
            }

            criteriaRepository.saveAll(criteriaList);
        } catch (IOException | IllegalArgumentException e) {
            throw new RuntimeException("Error uploading criteria from CSV: " + e.getMessage(), e);
        }
    }

    @Override
    public void downloadCriteriaToCsv(Writer writer) {
        try {
            List<Criteria> criteriaList = criteriaRepository.findAll();

            CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
                    .withHeader("id", "name", "value", "directoryId"));

            for (Criteria criteria : criteriaList) {
                csvPrinter.printRecord(
                        criteria.getId(),
                        criteria.getName(),
                        criteria.getValue(),
                        criteria.getCriterionDirectory().getId()
                );
            }

            csvPrinter.flush();
        } catch (IOException e) {
            throw new RuntimeException("Error writing criteria to CSV: " + e.getMessage(), e);
        }
    }
}
