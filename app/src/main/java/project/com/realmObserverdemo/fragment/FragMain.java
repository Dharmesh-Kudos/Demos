package project.com.realmObserverdemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import project.com.observerdemo.R;
import project.com.realmObserverdemo.realmcontroller.DataTable;
import project.com.realmObserverdemo.realmcontroller.RealmController;

public class FragMain extends Fragment {

    private EditText ed_main;
    private Button save;
    private View rootView;

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

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!ed_main.getText().toString().isEmpty()) {
                    DataTable dataTable = new DataTable(ed_main.getText().toString());
                    RealmController.with(getActivity()).addData(dataTable);
                }
            }
        });
    }

}