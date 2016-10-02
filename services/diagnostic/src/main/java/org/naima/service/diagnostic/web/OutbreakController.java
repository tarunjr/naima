package org.naima.service.diagnostic.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.naima.service.diagnostic.service.OutbreakService;
import org.naima.service.diagnostic.domain.Outbreak;
import org.naima.service.diagnostic.domain.Speciality;
import org.naima.service.diagnostic.domain.Location;
import org.naima.service.diagnostic.domain.Condition;

@RestController
public class OutbreakController {
    @Autowired
    private OutbreakService outbreakService;


    @RequestMapping(value="/outbreak", method=RequestMethod.GET)
    public List<Outbreak> outbreaks() {
        return outbreakService.findAll();
    }
    @RequestMapping(value="/outbreak/location",method=RequestMethod.POST, consumes="application/json")
    public List<Outbreak> outbreakByLocation(@RequestBody Location location) {
        return outbreakService.findByLocation(location);
    }
    @RequestMapping(value="/outbreak/condition", method=RequestMethod.POST, consumes="application/json")
    public List<Outbreak> outbreakByCondition(@RequestBody List<Condition> conditions) {
        return outbreakService.findByConditions(conditions);
    }
}
