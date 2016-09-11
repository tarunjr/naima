package nalanda.com.doctor.widgets;

import android.app.Activity;
import android.view.View;

import nalanda.com.doctor.R;
import nalanda.com.doctor.models.BaseModel;
import nalanda.com.doctor.viewmodel.BaseViewModel;

/**
 * Created by ps1 on 9/11/16.
 */
public class NextFooterButton implements BaseView {
    @Override
    public View getView(Activity activity) {
        return activity.getLayoutInflater().inflate(R.layout.next_footer_button, null, false);
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
