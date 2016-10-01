package org.naima.service.diagnostic.domain;

import java.util.Set;

public class AssociatedSymptomsRule {
    private String id;
    private Set<String> ancedents;
    private Set<String> consequent;
    private Double confidence;

    public String getId() {
      return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public Set<String> getAncedents(){
        return ancedents;
    }
    public void setAncedents(Set<String> ancedents){
        this.ancedents = ancedents;
    }
    public Set<String> getConsequent(){
        return consequent;
    }
    public void setConsequent(Set<String> consequent){
        this.consequent = consequent;
    }
    public Double getConfidence(){
        return confidence;
    }
    public void setConfidence(Double confidence){
        this.confidence = confidence;
    }
    @Override
    public String toString() {
          return "Rule:" + getId() + " " + getAncedents() + " -> " + getConsequent() + " , confidence:" + getConfidence();
    }
}
