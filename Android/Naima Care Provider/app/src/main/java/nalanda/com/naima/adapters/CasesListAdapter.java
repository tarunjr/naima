package nalanda.com.naima.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import nalanda.com.naima.R;
import nalanda.com.naima.models.CasesDataModel;

/**
 * Created by ps1 on 10/12/16.
 */
public class CasesListAdapter extends ArrayAdapter<CasesDataModel>{
    int resource;
    public CasesListAdapter(Context context, int resource, CasesDataModel[] casesDataModels) {
        super(context, resource, casesDataModels);
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(resource, parent, false);
        }

        ((TextView) convertView.findViewById(R.id.patient_name)).setText(getItem(position).getPatient().getName());

        ((TextView) convertView.findViewById(R.id.doctor_name)).setText(getItem(position).getDoctor().getName());

        return convertView;
    }
}
