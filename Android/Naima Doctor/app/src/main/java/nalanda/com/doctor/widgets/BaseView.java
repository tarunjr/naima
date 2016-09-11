package nalanda.com.doctor.widgets;

import android.app.Activity;
import android.view.View;

import nalanda.com.doctor.models.BaseModel;
import nalanda.com.doctor.viewmodel.BaseViewModel;


/**
 * Created by ps1 on 9/10/16.
 */
public interface BaseView {
    View getView(Activity activity);

    BaseModel getClinicalData();

    BaseViewModel getViewModel();
}
