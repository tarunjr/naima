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
    @Autowired
    private SymptomRepository symptomRepository;

    public List<AssociatedSymptomsRule> findAll() {
        return associatedSymptomsRuleRepository.findAll();
    }
    public List<Symptom> findAssociatedSymptoms(List<SymptomValue> symptomValues) {
        Set<String> hasSymptomIds = new HashSet<String>();
        for(SymptomValue sv: symptomValues) {
            hasSymptomIds.add(sv.getSymptom().getId());
        }
        List<AssociatedSymptomsRule> rules = findAllCandidateRules(hasSymptomIds);
        AssociatedSymptomsRule rule = findBestRule(rules, hasSymptomIds);
        return findExclusiveSymptoms(rule, hasSymptomIds);
    }

    private List<AssociatedSymptomsRule> findAllCandidateRules(Set<String> inputIds) {
        return associatedSymptomsRuleRepository.findCandidates(inputIds);
    }
    private AssociatedSymptomsRule findBestRule(List<AssociatedSymptomsRule> rules, Set<String> excludeIds) {
        Double maxConfidence = Double.MIN_VALUE;
        AssociatedSymptomsRule selectedRule = null;

        for(AssociatedSymptomsRule rule: rules) {
            if (findExclusiveSymptoms(rule,excludeIds).size() > 0 && rule.getConfidence() > maxConfidence) {
                selectedRule = rule;
                maxConfidence = rule.getConfidence();
            }
        }
        if (selectedRule != null)
          System.out.println("Best Candidate:" + selectedRule.toString());
        else
          System.out.println("Best Candidate: NONE");

        return selectedRule;
    }
    private List<Symptom> findExclusiveSymptoms(AssociatedSymptomsRule rule, Set<String> excludeIds) {
      List<Symptom>  symptoms = new ArrayList<Symptom>();
      if (rule == null)
        return symptoms;
      for(String symptomKey: rule.getConsequent()) {
          if (excludeIds.contains(symptomKey))
            continue;
          Symptom symptom = symptomRepository.findById(symptomKey);
          symptoms.add(symptom);
      }
      return symptoms;
    }
}
