package nalanda.com.naima.widgets;

import android.app.Activity;
import android.support.v7.widget.AppCompatRadioButton;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import nalanda.com.naima.R;
import nalanda.com.naima.models.BaseModel;
import nalanda.com.naima.models.CaseClinical;
import nalanda.com.naima.models.ChoiceModel;
import nalanda.com.naima.viewmodel.BaseViewModel;
import nalanda.com.naima.viewmodel.SymptomItemModel;

/**
 * Created by ps1 on 9/10/16.
 */
public class ChoiceView implements BaseView{
    private final SymptomItemModel choiceViewModel;
    private ChoiceModel choiceModel;
    private View view;

    public ChoiceView(BaseViewModel dataViewModel) {
        choiceModel = new ChoiceModel();
        choiceViewModel = (SymptomItemModel) dataViewModel;
    }

    @Override
    public View getView(Activity activity) {
        view = activity.getLayoutInflater().inflate(R.layout.choice_input_layout, null, false);

        ((TextView)view.findViewById(R.id.title)).setText(choiceViewModel.getTitle());

        RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.radio_group);
        AppCompatRadioButton appCompatRadioButton =
                (AppCompatRadioButton) activity.getLayoutInflater().inflate(R.layout.radio_button, null, false);
        appCompatRadioButton.setId(R.id.yes);
        appCompatRadioButton.setText(R.string.yes);
        appCompatRadioButton.setTextSize(24);
        radioGroup.addView(appCompatRadioButton);

        appCompatRadioButton =
                (AppCompatRadioButton) activity.getLayoutInflater().inflate(R.layout.radio_button, null, false);
        appCompatRadioButton.setId(R.id.no);
        appCompatRadioButton.setText(R.string.no);
        appCompatRadioButton.setTextSize(24);
        radioGroup.addView(appCompatRadioButton);

        return view;
    }

    @Override
    public BaseModel getClinicalData() {
        RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.radio_group);
        int selectedId = radioGroup.getCheckedRadioButtonId();
        if(selectedId > 0) {
            List<String> values = new ArrayList<String>();
            RadioButton radioButton = (RadioButton) view.findViewById(selectedId);
            if ("Yes".equals(radioButton.getText())) {
                values.add("Yes");
            } else {
                values.add("No");
            }

            CaseClinical caseClinical = new CaseClinical();
            caseClinical.setSymptom(new SymptomItemModel());
            caseClinical.getSymptom().setId(choiceViewModel.getId());
            caseClinical.getSymptom().setName(choiceViewModel.getName());
            caseClinical.setValue(values);

            return caseClinical;
        } else {
            return null;
        }
    }

    @Override
    public BaseViewModel getViewModel() {
        return choiceViewModel;
    }
}
