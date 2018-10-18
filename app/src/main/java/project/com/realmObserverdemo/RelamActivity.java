package project.com.realmObserverdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import io.realm.OrderedCollectionChangeSet;
import io.realm.OrderedRealmCollectionChangeListener;
import io.realm.RealmResults;
import project.com.observerdemo.R;
import project.com.observerdemo.activity.SecondActivity;
import project.com.realmObserverdemo.fragment.FragMain;
import project.com.realmObserverdemo.realmcontroller.DataTable;
import project.com.realmObserverdemo.realmcontroller.RealmController;

public class RelamActivity extends AppCompatActivity {

    private static final String TAG = "RealmDemo";
    private TextView tvMain;
    private Button next;
    private Button realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InitComponent();
    }

    private void InitComponent() {
        tvMain = findViewById(R.id.tv_txtMain);
        next = findViewById(R.id.btn_next);
        realm = findViewById(R.id.btn_realm);
        realm.setText("Clear Data");

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
                RealmController.with(RelamActivity.this).clearAll();
            }
        });


        RealmResults<DataTable> tbldata = RealmController.with(RelamActivity.this).getDataTables();
        tbldata.addChangeListener(new OrderedRealmCollectionChangeListener<RealmResults<DataTable>>() {
            @Override
            public void onChange(RealmResults<DataTable> dataTables, OrderedCollectionChangeSet changeSet) {

                int[] deletion = changeSet.getDeletions();
                int[] insertion = changeSet.getInsertions();
                int[] change = changeSet.getChanges();

                String status = "";
                if (deletion != null && deletion.length > 0) {
                    status = "Deletion";
                } else if (insertion != null && insertion.length > 0) {
                    status = "Insertion";
                } else if (change != null && change.length > 0) {
                    status = "Update";
                }
                Toast.makeText(RelamActivity.this, status + "", Toast.LENGTH_SHORT).show();

                OrderedCollectionChangeSet.Range[] deletions = changeSet.getDeletionRanges();
                for (int i = deletions.length - 1; i >= 0; i--) {
                    OrderedCollectionChangeSet.Range range = deletions[i];
                    Log.d(TAG, "\nonDelete() called with: range startIndex = [" + range.startIndex + "]");
                    Log.d(TAG, "\nonDelete() called with: range length = [" + range.length + "]");

                    for (int j = range.startIndex; j < (range.startIndex + range.length); j++) {
                        Log.d(TAG, "\nonChange() called with: collection " +
                                "= [" + dataTables.get(j).getData() + "]");
                    }
                }

                OrderedCollectionChangeSet.Range[] insertions = changeSet.getInsertionRanges();
                for (OrderedCollectionChangeSet.Range range : insertions) {
                    Log.d(TAG, "\ninsert called with: range startIndex = [" + range.startIndex + "]");
                    Log.d(TAG, "\ninsert called with: range length = [" + range.length + "]");

                    for (int j = range.startIndex; j < (range.startIndex + range.length); j++) {
                        Log.d(TAG, "\ninsert called with: collection " +
                                "= [" + dataTables.get(j).getData() + "]");
                    }
                }

                OrderedCollectionChangeSet.Range[] modifications = changeSet.getChangeRanges();
                for (OrderedCollectionChangeSet.Range range : modifications) {
                    Log.d(TAG, "\nUpdate called with: range startIndex = [" + range.startIndex + "]");
                    Log.d(TAG, "\nUpdate called with: range length = [" + range.length + "]");

                    for (int j = range.startIndex; j < (range.startIndex + range.length); j++) {
                        Log.d(TAG, "\nUpdate called with: collection " +
                                "= [" + dataTables.get(j).getData() + "]");
                    }
                }
            }
        });

    }
}
