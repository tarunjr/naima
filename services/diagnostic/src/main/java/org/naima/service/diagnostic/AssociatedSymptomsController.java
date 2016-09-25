package org.naima.service.diagnostic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.SetOperations;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import org.springframework.http.MediaType;

@RestController
public class AssociatedSymptomsController {
    @Autowired
    private StringRedisTemplate redisTemplate;
    private final String kAntecedent = "ANTECEDENT";
    private final String kConsequent = "CONSEQUENT";
    private final String kConfidence = "CONFIDENCE";

    @RequestMapping(value="/associatedsymptoms", method=RequestMethod.POST, consumes="application/json")
    public List<Symptom> associatedsymptoms(@RequestBody List<Symptom> hasSymptoms) {

      SetOperations<String,String> setOps = redisTemplate.opsForSet();
      Set<String> symptoms = new HashSet<String>();
      String tempInputsetKey = "TEMP:CSM:" + Long.toString(System.currentTimeMillis());

      for(Symptom s: hasSymptoms) {
        symptoms.add(s.getId());
        setOps.add(tempInputsetKey, s.getId());
      }
      List<Symptom> associatedSymptoms = new ArrayList<Symptom>();

      try {
        ValueOperations<String,String>  valueOps = redisTemplate.opsForValue();

        double maxConfidence = Double.MIN_VALUE;
        String selectedKey = "";

        Set<String> ids = setOps.members("RULES");
        for(String id: ids) {
           Set<String> intersect  =  setOps.intersect(KeyNameSpace.AssociationRuleNamespace +
                                                      ":" + id + ":" + kAntecedent, tempInputsetKey);

           Set<String> ancedents =  setOps.members(KeyNameSpace.AssociationRuleNamespace +
                                                     ":" + id + ":" + kAntecedent);
           System.out.println(id + " : " + ancedents);
           // Check if the rule is selected
           if (intersect.size() == ancedents.size()) {
              String strConfidence = valueOps.get(KeyNameSpace.AssociationRuleNamespace +
                                                    ":" + id + ":" + kConfidence);
              double confidence = Double.valueOf(strConfidence);
              System.out.println(id + " matches with confidence : " + strConfidence);
              if (confidence > maxConfidence) {
                  maxConfidence = confidence;
                  selectedKey = id;
              }
           }
        }
        System.out.println(selectedKey);
        if (selectedKey.length() > 0) {
             Set<String> associatedSymptomKeys  =  setOps.members( KeyNameSpace.AssociationRuleNamespace +
                                                        ":" +  selectedKey + ":" + kConsequent);
             System.out.println(associatedSymptomKeys);
             ObjectMapper mapper = new ObjectMapper();

             for(String symptomKey: associatedSymptomKeys) {
                if ( symptoms.contains(symptomKey))
                  continue;

                //System.out.println(KeyNameSpace.SymptomNamespace + ":" + symptomKey);
                String json  =  valueOps.get(KeyNameSpace.SymptomNamespace + ":" + symptomKey);
                //System.out.println(json);
                Symptom symptom = mapper.readValue(json, Symptom.class);
                //System.out.println(symptom.getTitle());
                //System.out.println(jsonInString);
                associatedSymptoms.add(symptom);
             }
        }
      } catch (java.io.IOException iox) {
      }
      return associatedSymptoms;
    }
}
