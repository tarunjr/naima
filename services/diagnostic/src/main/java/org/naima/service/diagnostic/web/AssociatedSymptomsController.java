package org.naima.service.diagnostic.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import org.springframework.http.MediaType;

import org.naima.service.diagnostic.domain.Symptom;
import org.naima.service.diagnostic.domain.SymptomValue;
import org.naima.service.diagnostic.domain.AssociatedSymptomsRule;
import org.naima.service.diagnostic.service.AssociatedSymptomsService;

@RestController
public class AssociatedSymptomsController {
    @Autowired
    private AssociatedSymptomsService associatedSymptomsService;

    @RequestMapping(value="/associatedsymptoms", method=RequestMethod.POST, consumes="application/json")
    public List<Symptom> associatedsymptoms(@RequestBody List<SymptomValue> hasSymptoms) {
        return associatedSymptomsService.findAssociatedSymptoms(hasSymptoms);
    }
    @RequestMapping(value="/associatedsymptoms", method=RequestMethod.GET)
    public List<AssociatedSymptomsRule> findAllRules() {
        return associatedSymptomsService.findAll();
    }
}
