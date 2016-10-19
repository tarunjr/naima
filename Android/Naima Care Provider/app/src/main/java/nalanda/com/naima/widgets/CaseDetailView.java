package nalanda.com.naima.widgets;

import android.app.Activity;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import nalanda.com.naima.R;
import nalanda.com.naima.models.BaseModel;
import nalanda.com.naima.models.CasesClinical;
import nalanda.com.naima.models.CasesDataModel;
import nalanda.com.naima.models.Medicine;
import nalanda.com.naima.models.TestData;
import nalanda.com.naima.viewmodel.BaseViewModel;
import nalanda.com.naima.viewmodel.CaseViewModel;

/**
 * Created by ps1 on 10/14/16.
 */
public class CaseDetailView implements BaseView {
    private CasesDataModel casesDataModel;
    private LinearLayout detailView;

    public CaseDetailView(CasesDataModel casesDataModel) {
        this.casesDataModel = casesDataModel;
    }

    @Override
    public View getView(Activity activity) {
        View view = LayoutInflater.from(activity).inflate(R.layout.case_detail, null, false);
        detailView = (LinearLayout) (view.findViewById(R.id.detail_layout_parent));

        populateDoctorPatientView(activity);
        populateSymptoms(activity);
        populatePrescriptionView(activity);
        populateTestsReport(activity);

        return view;
    }

    private void addCaseDetailItem(String label, String name, LinearLayout parentView, int childViewLayout, Activity activity) {
        View view = LayoutInflater.from(activity).inflate(childViewLayout, null, false);
        ((TextView) view.findViewById(R.id.item_label)).setText(label);
        ((TextView) view.findViewById(R.id.item_name)).setText(name);

        parentView.addView(view);
    }

    private void populateSymptoms(Activity activity) {
        View view = LayoutInflater.from(activity).inflate(R.layout.case_detail_item_parent, null, false);
        LinearLayout symptomView = (LinearLayout) view.findViewById(R.id.case_detail_item_parent);
        if(casesDataModel.getData() != null && casesDataModel.getData().getClinical() != null) {
            for (CasesClinical symptom : casesDataModel.getData().getClinical()) {
                if(symptom.getSymptom() != null) {
                    String symptomStr = symptom.getSymptom().getName();
                    String[] values = symptom.getValue();
                    if(values != null && values.length > 0) {
                        String symptomValue = "";
                        for(String value : values) {
                            if(!TextUtils.isEmpty(symptomValue)) {
                                symptomValue += ", ";
                            }
                            symptomValue += value;
                        }

                        if(!TextUtils.isEmpty(symptomStr) && !TextUtils.isEmpty(symptomValue)) {
                            addCaseDetailItem(symptomStr, symptomValue, symptomView, R.layout.symptom_detail_item_child, activity);
                        }
                    }
                }
            }
        }

        if(symptomView.getChildCount() > 0) {
            TextView title = (TextView) view.findViewById(R.id.case_detail_item_parent_title);
            title.setVisibility(View.VISIBLE);
            title.setText("Symptoms");
            detailView.addView(view);
        }
    }

    private void populatePrescriptionView(Activity activity) {
        View view = LayoutInflater.from(activity).inflate(R.layout.case_detail_item_parent, null, false);
        LinearLayout prescriptionView = (LinearLayout) view.findViewById(R.id.case_detail_item_parent);

        if(casesDataModel.getPrescription() != null && casesDataModel.getPrescription().getMedicines() != null) {
            for (Medicine medicine : casesDataModel.getPrescription().getMedicines()) {
                if(medicine != null && !TextUtils.isEmpty(medicine.getName()) &&
                        !TextUtils.isEmpty(medicine.getIntake()) && !TextUtils.isEmpty(medicine.getDuration())) {
                    String intake = medicine.getIntake() + " - " + medicine.getDuration();
                    addCaseDetailItem(medicine.getName() + " - ", intake, prescriptionView, R.layout.prescription_detail_item_child, activity);
                }
            }
        }

        if(casesDataModel.getPrescription() != null && !TextUtils.isEmpty(casesDataModel.getPrescription().getNotes())) {
            addCaseDetailItem("Notes -", casesDataModel.getPrescription().getNotes(), prescriptionView, R.layout.prescription_detail_item_child, activity);
        }

        if(casesDataModel.getPrescription() != null && !TextUtils.isEmpty(casesDataModel.getPrescription().getReview())) {
            addCaseDetailItem("Review -", casesDataModel.getPrescription().getReview(), prescriptionView, R.layout.prescription_detail_item_child, activity);
        }

        if(prescriptionView.getChildCount() > 0) {
            TextView title = (TextView) view.findViewById(R.id.case_detail_item_parent_title);
            title.setVisibility(View.VISIBLE);
            String date = (casesDataModel.getPrescription() != null && !TextUtils.isEmpty(casesDataModel.getPrescription().getDate())) ?
                    casesDataModel.getPrescription().getDate() : "";
            title.setText("Prescription - " + date);
            detailView.addView(view);
        }
    }

    private void populateTestsReport(Activity activity) {
        View view = LayoutInflater.from(activity).inflate(R.layout.case_detail_item_parent, null, false);
        LinearLayout testReportView = (LinearLayout) view.findViewById(R.id.case_detail_item_parent);

        if(casesDataModel.getData() != null && casesDataModel.getData().getTest() != null) {
            for (TestData testData : casesDataModel.getData().getTest()) {
                if(testData != null && !TextUtils.isEmpty(testData.getName()) &&
                        !TextUtils.isEmpty(testData.getValue())) {
                    addCaseDetailItem(testData.getName() + " - ", testData.getValue(), testReportView, R.layout.tests_detail_item_child, activity);
                }
            }
        }

        if(testReportView.getChildCount() > 0) {
            TextView title = (TextView) view.findViewById(R.id.case_detail_item_parent_title);
            title.setVisibility(View.VISIBLE);
            title.setText("Tests");
            detailView.addView(view);
        }
    }

    private void populateDoctorPatientView(Activity activity) {
        View view = LayoutInflater.from(activity).inflate(R.layout.case_detail_item_parent, null, false);
        LinearLayout doctorPatientView = (LinearLayout) view.findViewById(R.id.case_detail_item_parent);
        if(casesDataModel.getPatient() != null && !TextUtils.isEmpty(casesDataModel.getPatient().getName())) {
            addCaseDetailItem("Patient", casesDataModel.getPatient().getName(), doctorPatientView, R.layout.case_detail_item_child, activity);
        }

        if(casesDataModel.getDoctor() != null && !TextUtils.isEmpty(casesDataModel.getDoctor().getName())) {
            addCaseDetailItem("Doctor", casesDataModel.getDoctor().getName(), doctorPatientView, R.layout.case_detail_item_child, activity);
        }

        if(doctorPatientView.getChildCount() > 0) {
            detailView.addView(view);
        }
    }

    @Override
    public BaseModel getClinicalData() {
        return null;
    }

    @Override
    public BaseViewModel getViewModel() {
        return null;
    }
}
