package nalanda.com.naima.models;

/**
 * Created by ps1 on 9/11/16.
 */
public class CasesDataModel extends BaseModel{
    private CasesData data;

    private Provider provider;

    private Patient patient;

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

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
