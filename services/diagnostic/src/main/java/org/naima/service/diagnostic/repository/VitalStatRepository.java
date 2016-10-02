package org.naima.service.diagnostic.repository;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;

import org.naima.service.diagnostic.domain.VitalStat;


@Repository
public class VitalStatRepository {
  @Autowired
  private StringRedisTemplate redisTemplate;

  public List<VitalStat> findAll() {
    ObjectMapper mapper = new ObjectMapper();
    List<VitalStat> stats = new ArrayList<VitalStat>();
    ValueOperations<String,String> valueOps = redisTemplate.opsForValue();

    try {
      String keyPattern = KeyNameSpace.StandardNamespace + ":*";
      Set<String> keys = redisTemplate.keys(keyPattern);

      for(String key: keys) {
         System.out.println(key);
         String json  =  valueOps.get(key);
         System.out.println(json);
         VitalStat stat = mapper.readValue(json, VitalStat.class);
         stats.add(stat);
      }
    } catch (com.fasterxml.jackson.core.JsonProcessingException jpex) {
    } catch (java.io.IOException iox) {
    }
    return stats;
  }

  public VitalStat findById(String id) {
    ObjectMapper mapper = new ObjectMapper();
    ValueOperations<String,String>  valueOps = redisTemplate.opsForValue();
    VitalStat stat = null;

    System.out.println(id);
    try {
        String json  = valueOps.get(id);
        stat = mapper.readValue(json, VitalStat.class);
    } catch (com.fasterxml.jackson.core.JsonProcessingException jpex) {
    } catch (java.io.IOException iox) {
    }
    return stat;
  }
}
