package nalanda.com.naima.models;

/**
 * Created by ps1 on 9/11/16.
 */
public class CasesDataModel extends BaseModel{
    private String status;

    private String owner;

    private Prescription prescription;

    private PendingData pendingdata;

    private CasesData data;

    private Provider provider;

    private Doctor doctor;

    private Patient patient;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Prescription getPrescription() {
        return prescription;
    }

    public void setPrescription(Prescription prescription) {
        this.prescription = prescription;
    }

    public PendingData getPendingData() {
        return pendingdata;
    }

    public void setPendingData(PendingData pendingData) {
        this.pendingdata = pendingData;
    }

    public CasesData getData() {
        return data;
    }

    public void setData(CasesData data) {
        this.data = data;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
