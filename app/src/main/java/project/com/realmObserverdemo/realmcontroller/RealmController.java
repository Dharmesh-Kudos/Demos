package project.com.realmObserverdemo.realmcontroller;

import android.app.Activity;
import android.app.Application;
import android.support.v4.app.Fragment;

import io.realm.Realm;
import io.realm.RealmResults;

public class RealmController {

    private static RealmController instance;
    private final Realm realm;

    public RealmController(Application application) {
        realm = Realm.getDefaultInstance();
    }

    public static RealmController with(Fragment fragment) {

        if (instance == null) {
            instance = new RealmController(fragment.getActivity().getApplication());
        }
        return instance;
    }

    public static RealmController with(Activity activity) {

        if (instance == null) {
            instance = new RealmController(activity.getApplication());
        }
        return instance;
    }

    public static RealmController with(Application application) {

        if (instance == null) {
            instance = new RealmController(application);
        }
        return instance;
    }

    public static RealmController getInstance() {

        return instance;
    }

    public Realm getRealm() {

        return realm;
    }

    //Refresh the realm istance
    public void refresh() {

        realm.refresh();
    }

    //clear all objects from DataTable.class
    public void clearAll() {
        realm.beginTransaction();
        realm.delete(DataTable.class);
        realm.commitTransaction();
    }

    //find all objects in the DataTable.class
    public RealmResults<DataTable> getDataTables() {
        return realm.where(DataTable.class).findAll();
    }

    //query a single item with the given id
    public DataTable getDataTable(String id) {
        return realm.where(DataTable.class).equalTo("data", id).findFirst();
    }

    //query example
    public RealmResults<DataTable> queryedDataTables() {
        return realm.where(DataTable.class).findAll();
    }

    // Adding data to relam
    public void addData(DataTable dataTable) {

        DataTable tbl = realm.where(DataTable.class).equalTo("data", dataTable.getData()).findFirst();

        if (tbl == null)
            tbl = new DataTable();

        realm.beginTransaction();

        if (dataTable.getData() != null)
            tbl.setData(dataTable.getData());

        realm.copyToRealm(tbl);
        realm.commitTransaction();
    }
}