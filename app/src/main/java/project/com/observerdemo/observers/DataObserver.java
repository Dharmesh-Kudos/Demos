package project.com.observerdemo.observers;

import java.util.ArrayList;

import project.com.observerdemo.interfaces.First;
import project.com.observerdemo.interfaces.RepositoryObserver;

public class DataObserver implements First.DataInterface {

    private String data;
    private static DataObserver INSTANCE = null;

    private ArrayList<RepositoryObserver> mObservers;

    public DataObserver() {
        mObservers = new ArrayList<>();
    }

    // Singelton Class
    public static DataObserver getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DataObserver();
        }
        return INSTANCE;
    }

    @Override
    public void registerObserver(RepositoryObserver repositoryObserver) {
        if(!mObservers.contains(repositoryObserver)) {
            mObservers.add(repositoryObserver);
        }
    }

    @Override
    public void removeObserver(RepositoryObserver repositoryObserver) {
        if(mObservers.contains(repositoryObserver)) {
            mObservers.remove(repositoryObserver);
        }
    }

    @Override
    public void notifyObservers() {
        if(mObservers!=null) {
            for (RepositoryObserver observer : mObservers) {
                observer.onDataChanged(data);
            }
        }
    }

    public void setData(String data) {
        this.data = data;
        notifyObservers();
    }

}
