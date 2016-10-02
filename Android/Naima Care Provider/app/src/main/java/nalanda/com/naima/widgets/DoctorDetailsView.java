package nalanda.com.naima.widgets;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import nalanda.com.naima.R;
import nalanda.com.naima.models.BaseModel;
import nalanda.com.naima.models.DoctorDetails;
import nalanda.com.naima.viewmodel.BaseViewModel;

/**
 * Created by ps1 on 9/11/16.
 */
public class DoctorDetailsView implements BaseView {
    DoctorDetails doctorDetails;
    public DoctorDetailsView(DoctorDetails doctorDetails) {
        this.doctorDetails = doctorDetails;
    }

    @Override
    public View getView(Activity activity) {
        View view = activity.getLayoutInflater().inflate(R.layout.doctor_details, null, false);

        ((TextView)view.findViewById(R.id.doctor_name)).setText(doctorDetails.getName());
        ((TextView)view.findViewById(R.id.speciality)).setText(doctorDetails.getSpeciality().getName());
        ((TextView)view.findViewById(R.id.place)).setText(doctorDetails.getAddress().getDistrict());

        return view;
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
