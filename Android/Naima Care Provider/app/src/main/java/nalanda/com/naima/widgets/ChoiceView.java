package nalanda.com.naima.widgets;

import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
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
    private ArrayAdapter<String> choiceAdapter;
    private View view;

    public ChoiceView(BaseViewModel dataViewModel) {
        choiceModel = new ChoiceModel();
        choiceViewModel = (SymptomItemModel) dataViewModel;
    }

    @Override
    public View getView(Activity activity) {
        view = activity.getLayoutInflater().inflate(R.layout.choice_input_layout, null, false);

        ((TextView)view.findViewById(R.id.title)).setText(choiceViewModel.getTitle());


        List<String> items = new ArrayList<String>();
        items.add(activity.getString(R.string.yes));
        items.add(activity.getString(R.string.no));

        choiceAdapter =
                new ArrayAdapter<String>(activity, R.layout.symptom_question_choice, R.id.choice_text, items);
        ((GridView) view.findViewById(R.id.choice_grid)).setAdapter(choiceAdapter);
        ((GridView) view.findViewById(R.id.choice_grid)).setNumColumns(2);
        ((GridView) view.findViewById(R.id.choice_grid)).setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        ((GridView) view.findViewById(R.id.choice_grid)).setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ((GridView) ChoiceView.this.view.findViewById(R.id.choice_grid)).setItemChecked(position, true);
                choiceAdapter.notifyDataSetChanged();
            }
        });

        return view;
    }

    @Override
    public BaseModel getClinicalData() {
        int selectedIndex = ((GridView) view.findViewById(R.id.choice_grid)).getCheckedItemPosition();;
        if(selectedIndex >= 0) {
            List<String> values = new ArrayList<String>();
            String value = choiceAdapter.getItem(selectedIndex);
            if ("Yes".equals(value)) {
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
