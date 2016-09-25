package nalanda.com.doctor.activites;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import nalanda.com.doctor.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initEventHandlers();
    }


    private void initEventHandlers() {
        findViewById(R.id.open_item).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, OpenItemActivity.class);
//                intent.putExtra(DataInputActivity.INPUT_EXTRA_FLOW, DataInputActivity.FLOW_CREATE_CASE);
                startActivity(intent);
            }
        });
    }
}
