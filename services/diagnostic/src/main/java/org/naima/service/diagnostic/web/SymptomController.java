package org.naima.service.diagnostic.web;

import java.util.List;
import org.naima.service.diagnostic.domain.Symptom;
import org.naima.service.diagnostic.service.SymptomService;
import org.omg.CORBA.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SymptomController {
    @Autowired
    private SymptomService symptomService;

    @RequestMapping("/symptoms")
    public List<Symptom> symptom(@RequestParam(value="conditionid", defaultValue="none") String conditionId) {
        System.out.println(conditionId);

        if (conditionId.equals("none"))
          return symptomService.findAll();
        else
          return symptomService.findByConditionId(conditionId);
    }
    @RequestMapping("/symptoms/{id}")
    public Symptom symptomById(@PathVariable(value="id") String symId ) {
        return symptomService.findById(symId);
    }
}
