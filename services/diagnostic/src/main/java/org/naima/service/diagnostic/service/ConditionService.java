package org.naima.service.diagnostic.service;

import org.naima.service.diagnostic.domain.Condition;
import org.naima.service.diagnostic.repository.ConditionRepository;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class ConditionService {
    @Autowired
    private ConditionRepository conditionRespository;

    public List<Condition> findAll() {
        return conditionRespository.findAll();
    }
    public Condition findById(String id) {
        return conditionRespository.findById(id);
    }
}
