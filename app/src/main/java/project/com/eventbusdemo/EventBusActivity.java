package project.com.eventbusdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.greenrobot.eventbus.Subscribe;

import project.com.observerdemo.R;
import project.com.realmObserverdemo.realmcontroller.DataTable;
import project.com.realmObserverdemo.realmcontroller.RealmController;

public class EventBusActivity extends AppCompatActivity {

    Button submit;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus);

        submit = findViewById(R.id.submit);
        editText = findViewById(R.id.editText);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataTable dataTable = RealmController.with(EventBusActivity.this).getDataTable("d1");
                //Events.AllEvents<DataTable> modelEvent = new Events.AllEvents<DataTable>(dataTable);
                Events.AllEvents<String> modelEvent = new Events.AllEvents<String>(editText.getText().toString() + "");
                GlobalBus.getBus().post(modelEvent);
            }
        });
    }

    // Event Bus
    @Subscribe
    public void getMessage(Events.AllEvents<String> table) {
        // DataTable tbl = table.getData();
        Toast.makeText(this, table.getData() + " ", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        GlobalBus.getBus().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        GlobalBus.getBus().unregister(this);
    }
}
