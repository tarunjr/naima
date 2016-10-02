package org.naima.service.diagnostic.domain;

import java.util.List;

public class SymptomValue {
    private Symptom symptom;
    private List<String> value;

    public Symptom getSymptom() {
      return this.symptom;
    }
    public void setSymptom(Symptom symptom) {
      this.symptom = symptom;
    }
    public List<String> getValue() {
      return this.value;
    }
    public void setValue(List<String> value) {
      this.value = value;
    }
}
