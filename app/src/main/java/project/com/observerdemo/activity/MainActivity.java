package project.com.observerdemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import project.com.eventbusdemo.EventBusActivity;
import project.com.observerdemo.R;
import project.com.observerdemo.fragment.FragMain;
import project.com.observerdemo.interfaces.RepositoryObserver;
import project.com.observerdemo.observers.DataObserver;

public class MainActivity extends AppCompatActivity implements RepositoryObserver {

    private TextView tvMain;
    private DataObserver observer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InitComponent();
    }

    private void InitComponent() {
        tvMain = findViewById(R.id.tv_txtMain);
        Button next = findViewById(R.id.btn_next);
        Button realm = findViewById(R.id.btn_realm);

        observer = DataObserver.getInstance();
        observer.registerObserver(this);

        FragMain fragMain = new FragMain();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_main, fragMain);
        transaction.commit();

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), SecondActivity.class));
            }
        });
        realm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(getApplicationContext(), RelamActivity.class));
                startActivity(new Intent(getApplicationContext(), EventBusActivity.class));
            }
        });
    }

    @Override
    public void onDataChanged(String data) {
        tvMain.setText(data + "");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        observer.removeObserver(this);
    }
}
