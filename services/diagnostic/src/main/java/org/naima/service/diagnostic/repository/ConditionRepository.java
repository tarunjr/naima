package org.naima.service.diagnostic.repository;

import org.naima.service.diagnostic.domain.Condition;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;

@Repository
public class ConditionRepository {

  @Autowired
  private StringRedisTemplate redisTemplate;

  public List<Condition> findAll() {
    ObjectMapper mapper = new ObjectMapper();
    List<Condition> conditions = new ArrayList<Condition>();
    ValueOperations<String,String> valueOps = redisTemplate.opsForValue();

    try {
      String keyPattern = KeyNameSpace.ConditionNamespace + ":*";
      Set<String> keys = redisTemplate.keys(keyPattern);

      for(String key: keys) {
         String json  =  valueOps.get(key);
         Condition condition = mapper.readValue(json, Condition.class);
         conditions.add(condition);
      }
    } catch (com.fasterxml.jackson.core.JsonProcessingException jpex) {
    } catch (java.io.IOException iox) {
    }
    return conditions;
  }

  public Condition findById(String id) {
    ObjectMapper mapper = new ObjectMapper();
    ValueOperations<String,String>  valueOps = redisTemplate.opsForValue();
    Condition condition = null;

    String key = KeyNameSpace.ConditionNamespace + ":" + id;

    try {
        String json  = valueOps.get(key);
        if (json == null || json.length() == 0)
          return null;
        condition = mapper.readValue(json, Condition.class);
    } catch (com.fasterxml.jackson.core.JsonProcessingException jpex) {
    } catch (java.io.IOException iox) {
    }
    return condition;
  }
}
