package nalanda.com.doctor.flow;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Response;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import nalanda.com.doctor.R;
import nalanda.com.doctor.fragment.BaseDataFragment;
import nalanda.com.doctor.models.BaseModel;
import nalanda.com.doctor.models.CaseClinical;
import nalanda.com.doctor.models.CaseData;
import nalanda.com.doctor.models.CaseDataModel;
import nalanda.com.doctor.models.Patient;
import nalanda.com.doctor.models.Provider;
import nalanda.com.doctor.network.VolleyUtil;
import nalanda.com.doctor.viewmodel.SymptomItemModel;
import nalanda.com.doctor.widgets.BaseView;
import nalanda.com.doctor.widgets.ComboboxView;
import nalanda.com.doctor.widgets.NextFooterButton;
import nalanda.com.doctor.widgets.WidgetFactory;

/**
 * Created by ps1 on 9/10/16.
 */
public class SymptomFlowManager {
    public static final String symptomStandardUrl = "http://ec2-52-91-156-218.compute-1.amazonaws.com:3000/symptoms/standard";
    public static final String symptomClinicalUrl = "http://ec2-52-91-156-218.compute-1.amazonaws.com:3000/symptoms/clinical";
    public static final String symptomDiagnosticUrl = "http://ec2-52-91-156-218.compute-1.amazonaws.com:3000/symptoms/diagnostic";
    public static final String caseNewUrl = "http://ec2-52-91-156-218.compute-1.amazonaws.com:3000/cases/new";
    public static final String JSON_CONTENT_TYPE = "application/json";

    private BaseDataFragment mDataFragment;
    Gson gson;
    private WidgetFactory widgetFactory;
    public static final int SYMPTOM_STATE_STANDARD = 1;
    public static final int SYMPTOM_STATE_COMBO = 2;
    public static final int SYMPTOM_STATE_SINGLE_CLINICAL = 3;
    public static final int SYMPTOM_STATE_CLINICAL = 4;
    public static final int SYMPTOM_STATE_DIAGNOSTIC = 5;
    public static final int SYMPTOM_STATE_SUBMIT = 6;
    private int mCurrentState;

    private SymptomItemModel[] symptomModel;

    private CaseDataModel caseDataModel;

    private List<BaseView> mViews;

    public SymptomFlowManager(BaseDataFragment dataFragment) {
        mDataFragment = dataFragment;

        gson = new Gson();

        widgetFactory = new WidgetFactory();

        caseDataModel = new CaseDataModel();
    }

    public void startFlow() {
        populatePatientData();
        populateProviderData();
        goToSymptomStandardState();
//        goToSymptomClinicalState();
    }

    private void populatePatientData() {
        Patient patient = new Patient();
        patient.setId("PT-01");
        patient.setName("Patient-01");
        caseDataModel.setPatient(patient);
    }

    private void populateProviderData() {
        Provider provider = new Provider();
        provider.setId("PR-01");
        provider.setName("Provider-01");
        caseDataModel.setProvider(provider);
    }

    private void populateDataForStandardState() {
    }

    public void goToSymptomStandardState() {
        mCurrentState = SYMPTOM_STATE_STANDARD;
        VolleyUtil.getInstance().doGet(mDataFragment.getContext(), symptomStandardUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                symptomModel = gson.fromJson(response, SymptomItemModel[].class);
                List<View> views = new ArrayList<View>();
                for (SymptomItemModel itemModel :symptomModel) {
                    BaseView view = widgetFactory.getViewWidget(itemModel.getInfo().getFormat(), itemModel);
                    views.add(view.getView(mDataFragment.getActivity()));
                }

                Button footerButton = (Button) (new NextFooterButton()).getView(mDataFragment.getActivity());
                footerButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        populateDataForStandardState();
                        goToSymptomClinicalState();
                    }
                });

                views.add(footerButton);

                mDataFragment.updateView(views);
            }
        });
    }

    private void populateDataForClinicalState() {
        caseDataModel.setData(new CaseData());
        caseDataModel.getData().setClinical(new ArrayList<CaseClinical>());
        for(BaseView view: mViews) {
            BaseModel model = view.getClinicalData();
            if(model != null) {
                caseDataModel.getData().getClinical().add((CaseClinical) model);
            }
        }
    }

    public void goToSymptomComboState() {
        mCurrentState = SYMPTOM_STATE_COMBO;
        final ComboboxView comboboxView = new ComboboxView(symptomModel);

        List<View> views = new ArrayList<View>();
        views.add(comboboxView.getView(mDataFragment.getActivity()));

        Button footerButton = (Button) (new NextFooterButton()).getView(mDataFragment.getActivity());
        footerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToSingleSymptomState(comboboxView.getSelectedIndex());
            }
        });

        views.add(footerButton);

        mDataFragment.updateView(views);
    }

    public void goToSingleSymptomState(int index) {
    }

    public void goToSymptomClinicalState() {
        mCurrentState = SYMPTOM_STATE_CLINICAL;
        VolleyUtil.getInstance().doGet(mDataFragment.getContext(), symptomClinicalUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//              response = "[{\"_id\":\"57d43de9aad0ed452f5d530f\",\"id\":\"CS-01\",\"format\":\"clinical\",\"info\":{\"title\":\"Where is the abodomen pain?\",\"name\":\"Abdomen Pain\",\"format\":\"List\",\"options\":[]}},{\"_id\":\"57d43de9aad0ed452f5d5310\",\"id\":\"CS-02\",\"format\":\"clinical\",\"info\":{\"title\":\"Did you have nausea?\",\"name\":\"Nausea\",\"format\":\"Boolean\",\"options\":[]}},{\"_id\":\"57d43de9aad0ed452f5d5311\",\"id\":\"CS-03\",\"format\":\"clinical\",\"info\":{\"title\":\"Did you vomit?\",\"name\":\"Vommiting\",\"format\":\"Boolean\",\"options\":[]}},{\"_id\":\"57d43de9aad0ed452f5d5312\",\"id\":\"CS-04\",\"format\":\"clinical\",\"info\":{\"title\":\"Nature of stool?\",\"name\":\"Stool\",\"format\":\"List\",\"options\":[\"Hard\",\"Soft\",\"Liquid\"]}}]";
                symptomModel = gson.fromJson(response, SymptomItemModel[].class);
                List<View> views = new ArrayList<View>();
                mViews = new ArrayList<BaseView>();
                for (SymptomItemModel itemModel : symptomModel) {
                    BaseView view = widgetFactory.getViewWidget(itemModel.getInfo().getFormat(), itemModel);
                    if (view != null) {
                        views.add(view.getView(mDataFragment.getActivity()));
                        mViews.add(view);
                    }
                }

                Button footerButton = (Button) (new NextFooterButton()).getView(mDataFragment.getActivity());
                footerButton.setText(R.string.submit);
                footerButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        populateDataForClinicalState();
                        goToSubmitState();
                    }
                });

                views.add(footerButton);

                mDataFragment.updateView(views);
            }
        });
    }

    public void goToSymptomDiagnosticState() {
        mCurrentState = SYMPTOM_STATE_DIAGNOSTIC;
        VolleyUtil.getInstance().doGet(mDataFragment.getContext(), symptomDiagnosticUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//              response = "[{\"_id\":\"57d43de9aad0ed452f5d530f\",\"id\":\"CS-01\",\"format\":\"clinical\",\"info\":{\"title\":\"Where is the abodomen pain?\",\"name\":\"Abdomen Pain\",\"format\":\"List\",\"options\":[]}},{\"_id\":\"57d43de9aad0ed452f5d5310\",\"id\":\"CS-02\",\"format\":\"clinical\",\"info\":{\"title\":\"Did you have nausea?\",\"name\":\"Nausea\",\"format\":\"Boolean\",\"options\":[]}},{\"_id\":\"57d43de9aad0ed452f5d5311\",\"id\":\"CS-03\",\"format\":\"clinical\",\"info\":{\"title\":\"Did you vomit?\",\"name\":\"Vommiting\",\"format\":\"Boolean\",\"options\":[]}},{\"_id\":\"57d43de9aad0ed452f5d5312\",\"id\":\"CS-04\",\"format\":\"clinical\",\"info\":{\"title\":\"Nature of stool?\",\"name\":\"Stool\",\"format\":\"List\",\"options\":[\"Hard\",\"Soft\",\"Liquid\"]}}]";
                symptomModel = gson.fromJson(response, SymptomItemModel[].class);
                List<View> views = new ArrayList<View>();
                for (SymptomItemModel itemModel : symptomModel) {
                    BaseView view = widgetFactory.getViewWidget(itemModel.getInfo().getFormat(), itemModel);
                    if (view != null)
                        views.add(view.getView(mDataFragment.getActivity()));
                }

                Button footerButton = (Button) (new NextFooterButton()).getView(mDataFragment.getActivity());
                footerButton.setText(R.string.submit);
                footerButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        goToSubmitState();
                    }
                });

                views.add(footerButton);

                mDataFragment.updateView(views);
            }
        });
    }

    public void goToSubmitState() {
        mCurrentState = SYMPTOM_STATE_DIAGNOSTIC;

//        String body = "{ \"data\":  { \"clinical\": [ {  \"id\": \"ID-01\", \"name\": \"Abdomain  Pain\",  \"value\": [ \"UpperLeft\",  \"UpperRigth\" ] } ]  }, \"provider\": { \"id\":  \"PR-02\", \"name\": \"Test02\" },  \"patient\": { \"id\": \"PT-01\", \"name\": \"Ramu\" } }";
        String body = gson.toJson(caseDataModel, CaseDataModel.class);
        Log.i("JSON", "JSON: " + body);
        VolleyUtil.getInstance().doPost(mDataFragment.getContext(), caseNewUrl, body, JSON_CONTENT_TYPE, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(mDataFragment.getActivity(), response, Toast.LENGTH_SHORT).show();
            }
        });
    }
}