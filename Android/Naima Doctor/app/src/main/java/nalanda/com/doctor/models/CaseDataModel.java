package nalanda.com.doctor.models;

/**
 * Created by ps1 on 9/11/16.
 */
public class CaseDataModel extends BaseModel{
    private CaseData data;

    private Provider provider;

    private Patient patient;
    public String status;
    public String owner;
    private Doctor doctor;

    public CaseData getData() {
        return data;
    }

    public void setData(CaseData data) {
        this.data = data;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Doctor getDoctor() {
        return doctor;
    }
}
