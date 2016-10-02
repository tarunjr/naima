package org.naima.service.diagnostic.repository;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;

import org.naima.service.diagnostic.domain.Outbreak;
import org.naima.service.diagnostic.domain.Speciality;
import org.naima.service.diagnostic.domain.Location;
import org.naima.service.diagnostic.domain.Condition;

@Repository
public class OutbreakRepository {
  @Autowired
  private StringRedisTemplate redisTemplate;
  private List<Outbreak> outbreaks;

  public  OutbreakRepository() {


      Condition c1 = new Condition();
      c1.setId("1");
      c1.setName("Acid Reflux");
      c1.setSpeciality("1");

      Condition c2 = new Condition();
      c2.setId("2");
      c2.setName("Diarhea");
      c2.setSpeciality("1");

      Condition c3 = new Condition();
      c3.setId("3");
      c3.setName("Sinus");
      c3.setSpeciality("3");

      Location l1 = new Location();
      l1.setLocality("KOPARGAON");
      l1.setSubDistrict("KHED");
      l1.setDistrict("PUNE");

      Location l2 = new Location();
      l2.setLocality("SARJAPUR");
      l2.setSubDistrict("TUMKUR");
      l2.setDistrict("BANGALORE");

      Outbreak ob1 = new Outbreak(l1,c1);
      Outbreak ob2 = new Outbreak(l1,c2);
      Outbreak ob3 = new Outbreak(l2,c3);

      outbreaks = new ArrayList<Outbreak>();
      outbreaks.add(ob1);
      outbreaks.add(ob2);
      outbreaks.add(ob3);
  }
  public List<Outbreak> findAll() {
      return outbreaks;
  }
  public List<Outbreak> findByConditions(List<Condition> conditions ) {

    List<Outbreak> outbreaks = new ArrayList<Outbreak>();
    for(Condition c: conditions) {
      for(Outbreak ob: outbreaks) {
          if(ob.getCondition().getId().equals(c.getId())) {
              outbreaks.add(ob);
          }
      }
    }
    return outbreaks;
  }
  public List<Outbreak> findByLocation(Location location) {
      List<Outbreak> result = new ArrayList<Outbreak>();
      System.out.println(location);

      for(Outbreak ob: outbreaks) {
          if(ob.getLocation().getLocality().equals(location.getLocality())) {
              result.add(ob);
          }
      }

    return result;
  }

}
