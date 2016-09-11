package nalanda.com.naima.widgets;

import android.app.Activity;
import android.view.View;

import nalanda.com.naima.models.BaseModel;
import nalanda.com.naima.viewmodel.BaseViewModel;


/**
 * Created by ps1 on 9/10/16.
 */
public interface BaseView {
    View getView(Activity activity);

    BaseModel getClinicalData();

    BaseViewModel getViewModel();
}
