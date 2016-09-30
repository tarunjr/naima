package org.naima.service.diagnostic.service;

import org.naima.service.diagnostic.domain.AssociatedSymptomsRule;
import org.naima.service.diagnostic.domain.Symptom;
import org.naima.service.diagnostic.domain.SymptomValue;
import org.naima.service.diagnostic.repository.AssociatedSymptomsRuleRepository;
import org.naima.service.diagnostic.repository.SymptomRepository;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

@Service
public class AssociatedSymptomsService {
    @Autowired
    private AssociatedSymptomsRuleRepository associatedSymptomsRuleRepository;
    private SymptomRepository symptomRepository;

    public List<AssociatedSymptomsRule> findAll() {
        return associatedSymptomsRuleRepository.findAll();
    }
    public List<Symptom> findAssociatedSymptoms(List<SymptomValue> symptomValues) {
        Set<String> symptomIds = new HashSet<String>();
        for(SymptomValue sv: symptomValues) {
            symptomIds.add(sv.getSymptom().getId());
        }
        List<AssociatedSymptomsRule> rules = findAllCandidateRules(symptomIds);
        AssociatedSymptomsRule rule = findBestRule(rules);

        return null;
    }

    private List<AssociatedSymptomsRule> findAllCandidateRules(Set<String> inputIds) {
        return associatedSymptomsRuleRepository.findCandidates(inputIds);
    }
    private AssociatedSymptomsRule findBestRule(List<AssociatedSymptomsRule> rules) {
        Double maxConfidence = Double.MIN_VALUE;
        AssociatedSymptomsRule selectedRule = null;

        for(AssociatedSymptomsRule rule: rules) {
            if (rule.getConfidence() > maxConfidence) {
                selectedRule = rule;
            }
        }
        return selectedRule;
    }
    private List<Symptom> findExclusiveSymptoms(AssociatedSymptomsRule rule, Set<String> excludeIds) {
      List<Symptom>  symptoms = new ArrayList<Symptom>();
      for(String symptomKey: rule.getConsequent()) {
          if (excludeIds.contains(symptomKey))
            continue;
          Symptom symptom = symptomRepository.findById(symptomKey);
          symptoms.add(symptom);
      }
      return symptoms;
    }
}
