package nalanda.com.doctor.activites;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.android.volley.Response;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import nalanda.com.doctor.R;
import nalanda.com.doctor.adapter.OpenItemAdapter;
import nalanda.com.doctor.models.CaseClinical;
import nalanda.com.doctor.models.CaseData;
import nalanda.com.doctor.models.CaseDataModel;
import nalanda.com.doctor.models.Doctor;
import nalanda.com.doctor.models.Patient;
import nalanda.com.doctor.models.Provider;
import nalanda.com.doctor.network.VolleyUtil;

/**
 * Created by saurabh on 11/9/16.
 */
public class OpenItemActivity extends AppCompatActivity {
    private static final String DOCTOR_OPEN_ITEMS_URL = "http://ec2-54-175-135-100.compute-1.amazonaws.com:3000/cases/open/doc/DT-01";

    class Soemthing{
        CaseDataModel[] caseDataModel;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.open_item);

        VolleyUtil.getInstance().doGet(this, DOCTOR_OPEN_ITEMS_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                response = "[{\"_id\":\"57d4ec60d0e6ea1206ad8940\",\"status\":\"open\",\"owner\":\"doctor\",\"__v\":0,\"prescription\":{\"medicines\":[]},\"pendingdata\":{\"test\":[],\"clinical\":[]},\"data\":{\"test\":[],\"clinical\":[{\"id\":\"CS-02\",\"name\":\"Nausea\",\"value\":\"Yes\",\"_id\":\"57d4ec60d0e6ea1206ad8942\"},{\"id\":\"CS-03\",\"name\":\"Vommiting\",\"value\":\"Once\",\"_id\":\"57d4ec60d0e6ea1206ad8941\"}]},\"provider\":{\"id\":\"PR-01\",\"name\":\"Provider-01\"},\"doctor\":{\"id\":\"DT-01\",\"name\":\"Vikram Mehta\"},\"patient\":{\"id\":\"PT-01\",\"name\":\"Patient-01\"}}]";
                ListView listView = ((ListView)findViewById(R.id.listView));
                ArrayList<CaseDataModel> caseDataModels = new ArrayList<CaseDataModel>();
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for(int iCount =0; iCount < jsonArray.length(); iCount++) {
                        JSONObject obj = jsonArray.getJSONObject(iCount);
                        CaseDataModel caseDataModel = new CaseDataModel();
                        caseDataModel.status = "open";
                        caseDataModel.owner = obj.getString("owner");
                        CaseData data = new CaseData();
                        List<CaseClinical> clinical = new ArrayList<CaseClinical>();
                        JSONArray clinicalJSONArray = obj.getJSONObject("data").getJSONArray("clinical");
                        for (int jCount = 0; jCount < clinicalJSONArray.length(); jCount++) {
                            JSONObject clinicalJSON = clinicalJSONArray.getJSONObject(jCount);
                            CaseClinical caseClinical = new CaseClinical();
                            caseClinical.setId(clinicalJSON.getString("id"));
                            caseClinical.setName(clinicalJSON.getString("name"));
                            caseClinical.setValue(Arrays.asList(clinicalJSON.getString("value").split(",")));
                            clinical.add(caseClinical);
                        }
                        data.setClinical(clinical);
                        caseDataModel.setData(data);

                        Provider provider = new Provider();
                        provider.setId(obj.getJSONObject("provider").getString("id"));
                        provider.setName(obj.getJSONObject("provider").getString("name"));
                        caseDataModel.setProvider(provider);

                        Patient patient = new Patient();
                        patient.setId(obj.getJSONObject("patient").getString("id"));
                        patient.setName(obj.getJSONObject("patient").getString("name"));
                        caseDataModel.setPatient(patient);

                        Doctor doctor = new Doctor();
                        doctor.setId(obj.getJSONObject("doctor").getString("id"));
                        doctor.setName(obj.getJSONObject("doctor").getString("name"));
                        caseDataModel.setDoctor(doctor);

                        caseDataModels.add(caseDataModel);
                    }
                    listView.setAdapter(new OpenItemAdapter(OpenItemActivity.this, caseDataModels));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

    }

}
