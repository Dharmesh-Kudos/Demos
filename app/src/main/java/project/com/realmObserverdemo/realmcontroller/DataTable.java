package project.com.realmObserverdemo.realmcontroller;

import io.realm.RealmObject;

public class DataTable extends RealmObject {

    String data;

    public DataTable() {
    }

    public DataTable(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
