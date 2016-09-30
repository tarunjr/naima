package org.naima.service.diagnostic.domain;

public class Location {
    private String locality;
    private String subDistrict;
    private String district;

    public String getLocality() {
      return this.locality;
    }
    public void setLocality(String locality) {
      this.locality = locality;
    }
    public String getSubDistrict() {
      return this.subDistrict;
    }
    public void setSubDistrict(String subDistrict) {
      this.subDistrict = subDistrict;
    }
    public String getDistrict() {
      return this.district;
    }
    public void setDistrict(String district) {
      this.district = district;
    }
}
