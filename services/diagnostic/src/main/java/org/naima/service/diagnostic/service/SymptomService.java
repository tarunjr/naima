package org.naima.service.diagnostic.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import org.naima.service.diagnostic.domain.Symptom;
import org.naima.service.diagnostic.domain.Condition;
import org.naima.service.diagnostic.repository.SymptomRepository;
import org.naima.service.diagnostic.repository.ConditionRepository;

import java.util.List;
import java.util.ArrayList;

@Service
public class SymptomService {
    @Autowired
    private SymptomRepository symptomRespository;

    @Autowired
    private ConditionRepository conditionRespository;

    public List<Symptom> findAll() {
        return symptomRespository.findAll();
    }
    public Symptom findById(String id) {
        return symptomRespository.findById(id);
    }
    public List<Symptom> findByConditionId(String conditionId) {
        List<Symptom> symptoms = new ArrayList<Symptom>();
        Condition condition = conditionRespository.findById(conditionId);
        if (condition == null)
          return symptoms;

        for(String symptomId: condition.getSymptoms()) {
            Symptom sym = symptomRespository.findById(symptomId);
            if (sym != null)
                symptoms.add(sym);
        }
        return symptoms;
    }
}
