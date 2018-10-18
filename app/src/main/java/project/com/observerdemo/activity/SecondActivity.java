package project.com.observerdemo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import project.com.observerdemo.R;
import project.com.observerdemo.interfaces.RepositoryObserver;
import project.com.observerdemo.observers.DataObserver;

public class SecondActivity extends AppCompatActivity implements RepositoryObserver {

    private EditText ed_main;
    private Button save;
    private DataObserver observer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragmentlayout);

        ed_main = findViewById(R.id.ed_data);
        save = findViewById(R.id.btn_save);

        observer = DataObserver.getInstance();
        observer.registerObserver(this);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!ed_main.getText().toString().isEmpty()) {
                    observer.setData(ed_main.getText().toString());
                    SecondActivity.this.finish();
                }
            }
        });

    }

    @Override
    public void onDataChanged(String data) {
        ed_main.setText(data + "");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        observer.removeObserver(this);
    }
}
