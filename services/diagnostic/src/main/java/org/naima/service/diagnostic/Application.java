package org.naima.service.diagnostic;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import java.util.Set;


import java.lang.StringBuilder;

@SpringBootApplication
public class Application implements CommandLineRunner {
   @Autowired
   private StringRedisTemplate redisTemplate;


   public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
   }
   @Override
	 public void run(String... args) {
     StringBuilder sb = new StringBuilder();
       for (String option : args) {
           sb.append(" ").append(option);
       }
       sb = sb.length() == 0 ? sb.append("No Options Specified") : sb;
       System.out.println(String.format("App launched with following options: %s", sb.toString()));

       if (args.length > 0 && args[0].equals("dbinit")) {
			     loadSymptoms();
	     }
	}
  private  void loadSymptoms() {
     ObjectMapper mapper = new ObjectMapper();
     Symptom obj = new Symptom();
     obj.setId("SYM-04");
     obj.setName("Dengue");
     obj.setFormat("Number");
     obj.setTitle("Did the patient experienc fever");

     try {

       String json  = mapper.writeValueAsString(obj);
       ValueOperations<String,String> ops = redisTemplate.opsForValue();
       ops.set(obj.getId(), json);

       Set<String> keys = redisTemplate.keys("SYM-*");
       for(String key: keys) {
         String jsonInString  = ops.get(key);
         Symptom obj2 = mapper.readValue(jsonInString, Symptom.class);
          System.out.println(obj2.getTitle());
          System.out.println(jsonInString);
       }

   } catch (com.fasterxml.jackson.core.JsonProcessingException jpex) {

   } catch (java.io.IOException iox) {

   }

   }
}
