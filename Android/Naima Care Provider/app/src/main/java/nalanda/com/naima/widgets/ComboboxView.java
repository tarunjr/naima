package nalanda.com.naima.widgets;

import android.app.Activity;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import nalanda.com.naima.R;
import nalanda.com.naima.models.BaseModel;
import nalanda.com.naima.models.ComboboxModel;
import nalanda.com.naima.viewmodel.BaseViewModel;
import nalanda.com.naima.viewmodel.SymptomItemModel;

/**
 * Created by ps1 on 9/10/16.
 */
public class ComboboxView implements BaseView{
    private ComboboxModel comboboxModel;
    private SymptomItemModel dataViewItemModel[];
    private View view;

    public ComboboxView(BaseViewModel dataViewItemModel[]) {
        comboboxModel = new ComboboxModel();
        this.dataViewItemModel = (SymptomItemModel[]) dataViewItemModel;
    }

    @Override
    public View getView(Activity activity) {
        view = activity.getLayoutInflater().inflate(R.layout.symptom_grid, null, false);

        List<String> items = new ArrayList<String>();

        for(int i = 0; i < dataViewItemModel.length; i++) {
            items.add(dataViewItemModel[i].getName());
        }

        final ArrayAdapter<String> symptomListAdapter =
                new ArrayAdapter<String>(activity, R.layout.symptom_item, R.id.symptom_text, items);
        ((GridView) view.findViewById(R.id.symptom_grid)).setAdapter(symptomListAdapter);
        ((GridView) view.findViewById(R.id.symptom_grid)).setNumColumns(2);
        ((GridView) view.findViewById(R.id.symptom_grid)).setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        ((GridView) view.findViewById(R.id.symptom_grid)).setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ((GridView) ComboboxView.this.view.findViewById(R.id.symptom_grid)).setItemChecked(position, true);
                symptomListAdapter.notifyDataSetChanged();
            }
        });

        return view;
    }

    @Override
    public BaseModel getClinicalData() {
        return null;
    }

    public int getSelectedIndex() {
        return ((GridView) view.findViewById(R.id.symptom_grid)).getCheckedItemPosition();
    }

    @Override
    public BaseViewModel getViewModel() {
        return null;
    }
}
