package nalanda.com.naima.activites;

import android.annotation.TargetApi;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import nalanda.com.naima.R;
import nalanda.com.naima.fragment.BaseDataFragment;
import nalanda.com.naima.widgets.BaseView;

public class DataInputActivity extends AppCompatActivity implements BaseDataFragment.OnFragmentInteractionListener{
    private BaseDataFragment dataFragment;
    private String mCurrentFlow;
    private BaseView mView;

    public static final String INPUT_EXTRA_FLOW = "Flow";
    public static final String FLOW_CREATE_CASE = "CreateCase";
    public static final String FLOW_PENDING_CASE = "PendingCase";

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data_input);

        // Find the toolbar view inside the activity layout
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // Sets the Toolbar to act as the ActionBar for this Activity window.
        // Make sure the toolbar exists in the activity and is not null
        setSupportActionBar(toolbar);

        Window window = getWindow();

        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        // finally change the color
        window.setStatusBarColor(getResources().getColor(R.color.toolbar_color));

        initFragment();
    }

    private void initFragment() {
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.data_input_parent);
        layout.removeAllViews();

        dataFragment = new BaseDataFragment();
        dataFragment.setArguments(getIntent().getExtras());

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.data_input_parent, dataFragment).commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
