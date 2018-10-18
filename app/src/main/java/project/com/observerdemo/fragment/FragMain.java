package project.com.observerdemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import project.com.observerdemo.R;
import project.com.observerdemo.interfaces.RepositoryObserver;
import project.com.observerdemo.observers.DataObserver;

public class FragMain extends Fragment implements RepositoryObserver {

    private EditText ed_main;
    private Button save;
    private View rootView;
    private DataObserver observer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragmentlayout, container, false);
        initCoponent();
        return rootView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void initCoponent() {
        ed_main = rootView.findViewById(R.id.ed_data);
        save = rootView.findViewById(R.id.btn_save);

        observer = DataObserver.getInstance();
        observer.registerObserver(this);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!ed_main.getText().toString().isEmpty())
                    observer.setData(ed_main.getText().toString());
            }
        });
    }

    @Override
    public void onDataChanged(String data) {
        ed_main.setText(data);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        observer.removeObserver(this);
    }
}