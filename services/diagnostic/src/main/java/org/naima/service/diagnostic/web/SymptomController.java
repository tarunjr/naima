package org.naima.service.diagnostic.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;

import org.naima.service.diagnostic.service.SymptomService;
import org.naima.service.diagnostic.domain.Symptom;

import java.util.List;

@RestController
public class SymptomController {
    @Autowired
    private SymptomService symptomService;

    @RequestMapping("/symptoms")
    public List<Symptom> symptom() {
        return symptomService.findAll();
    }
    @RequestMapping("/symptoms/id/{id}")
    public Symptom symptomById(@PathVariable(value="id") String symId ) {
        return symptomService.findById(symId);
    }
}
