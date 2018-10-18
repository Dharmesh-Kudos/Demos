package project.com;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import project.com.eventbusdemo.EventBusActivity;
import project.com.observerdemo.R;
import project.com.observerdemo.activity.MainActivity;
import project.com.qrdemo.QrDashboard;
import project.com.realmObserverdemo.RelamActivity;

public class Dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
    }

    public void OpenObderver(View view) {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }

    public void OpenRealm(View view) {
        startActivity(new Intent(getApplicationContext(), RelamActivity.class));
    }

    public void OpenEventBus(View view) {
        startActivity(new Intent(getApplicationContext(), EventBusActivity.class));
    }

    public void OpenQr(View view) {
        startActivity(new Intent(getApplicationContext(), QrDashboard.class));
    }

    public void OpenToolbar(View view) {
        startActivity(new Intent(getApplicationContext(), QrDashboard.class));
    }
}
