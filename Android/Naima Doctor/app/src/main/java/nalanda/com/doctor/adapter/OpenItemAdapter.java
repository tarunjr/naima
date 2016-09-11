package nalanda.com.doctor.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.List;

import nalanda.com.doctor.R;
import nalanda.com.doctor.models.CaseClinical;
import nalanda.com.doctor.models.CaseDataModel;

/**
 * Created by saurabh on 11/9/16.
 */
public class OpenItemAdapter extends ArrayAdapter<CaseDataModel> {

    private final Context context;
    private final List<CaseDataModel>  values;

    public OpenItemAdapter(Context context, List<CaseDataModel> values) {
        super(context, -1, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.open_item_list_item, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.textView);
        final CaseDataModel value = values.get(position);
        textView.setText(getViewString(value));
        Button followupButton = (Button) rowView.findViewById(R.id.followupbutton);
        Button presriptionButton = (Button) rowView.findViewById(R.id.medicinebutton);
        followupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Clicked of patiend" + value.status);
//                value.setData(null);
            }
        });
        presriptionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                System.out.println("Clicked of patiend" + value.status);
//                value.setData(null);
            }
        });
        return rowView;
    }

    private String getViewString(CaseDataModel value) {
        StringBuilder builder = new StringBuilder();
        builder.append("Patient Name : ");
        builder.append(value.getPatient().getName());
        builder.append("\n");
        builder.append("Provider Name : ");
        builder.append(value.getProvider().getName());
        builder.append("\n");
        builder.append("Clinical Systems are as follows : \n");
        List<CaseClinical> clinicalDataList = value.getData().getClinical();
        for(CaseClinical clinicalData : clinicalDataList ) {
            builder.append("Symptom Name : ");
            builder.append(clinicalData.getName());
            builder.append("\n");
            builder.append("Symptom Value : ");
            builder.append(clinicalData.getValue());
            builder.append("\n");
            builder.append("\n");

        }
        return builder.toString();
    }
}
