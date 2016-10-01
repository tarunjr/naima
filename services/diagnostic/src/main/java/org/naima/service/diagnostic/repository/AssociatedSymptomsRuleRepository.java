package org.naima.service.diagnostic.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.naima.service.diagnostic.domain.AssociatedSymptomsRule;
import org.omg.CORBA.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

@Repository
public class AssociatedSymptomsRuleRepository {
  @Autowired
  private StringRedisTemplate redisTemplate;

  private final String kAntecedent = "ANTECEDENT";
  private final String kConsequent = "CONSEQUENT";
  private final String kConfidence = "CONFIDENCE";

  public List<AssociatedSymptomsRule> findAll() {
      List<AssociatedSymptomsRule> rules = new ArrayList<AssociatedSymptomsRule>();
      ValueOperations<String,String>  valueOps = redisTemplate.opsForValue();
      SetOperations<String,String> setOps = redisTemplate.opsForSet();

      Set<String> rulesIds = setOps.members("RULES");
      for(String ruleId: rulesIds) {
        Set<String> ancedents =  setOps.members(KeyNameSpace.AssociationRuleNamespace +
                                                 ":" + ruleId + ":" + kAntecedent);
        Set<String> consequents =  setOps.members(KeyNameSpace.AssociationRuleNamespace +
                                                 ":" + ruleId + ":" + kConsequent);
        String strConfidence = valueOps.get(KeyNameSpace.AssociationRuleNamespace +
                                                 ":" + ruleId + ":" + kConfidence);

        AssociatedSymptomsRule rule = new AssociatedSymptomsRule();
        rule.setId(ruleId);
        rule.setAncedents(ancedents);
        rule.setConsequent(consequents);
        rule.setConfidence(Double.valueOf(strConfidence));
        rules.add(rule);
    }
    return rules;
  }
  public List<AssociatedSymptomsRule> findCandidates(Set<String> symptomIds) {
      List<AssociatedSymptomsRule> rules = new ArrayList<AssociatedSymptomsRule>();

      SetOperations<String,String> setOps = redisTemplate.opsForSet();
      ValueOperations<String,String>  valueOps = redisTemplate.opsForValue();

      Set<String> symptoms = new HashSet<String>();
      String tempInputsetKey = "TEMP:CSM:" + Long.toString(System.currentTimeMillis());
      System.out.println("Has Symptoms:" + symptomIds);
      for(String id: symptomIds) {
        setOps.add(tempInputsetKey, id);
      }

      Set<String> rulesIds = setOps.members("RULES");
      for(String ruleId: rulesIds) {
         Set<String> intersect  =  setOps.intersect(KeyNameSpace.AssociationRuleNamespace +
                                                    ":" + ruleId + ":" + kAntecedent, tempInputsetKey);

         Set<String> ancedents =  setOps.members(KeyNameSpace.AssociationRuleNamespace +
                                                   ":" + ruleId + ":" + kAntecedent);
         System.out.println("Initial Candidate:" + ruleId + " : " + ancedents);
         // Check if the rule is selected
         if (intersect.size() == ancedents.size()) {
            Set<String> consequents =  setOps.members(KeyNameSpace.AssociationRuleNamespace +
                                                    ":" + ruleId + ":" + kConsequent);
            String strConfidence = valueOps.get(KeyNameSpace.AssociationRuleNamespace +
                                                  ":" + ruleId + ":" + kConfidence);
            double confidence = Double.valueOf(strConfidence);

            AssociatedSymptomsRule rule = new AssociatedSymptomsRule();
            rule.setId(ruleId);
            rule.setAncedents(ancedents);
            rule.setConsequent(consequents);
            rule.setConfidence(Double.valueOf(strConfidence));
            rules.add(rule);
            System.out.println("Selected Candidate:" + rule.toString());
         }
      }

      return rules;
    }
}
