package project.com.observerdemo.interfaces;

public class First {


    public interface DataInterface {
        void registerObserver(RepositoryObserver repositoryObserver);

        void removeObserver(RepositoryObserver repositoryObserver);

        void notifyObservers();
    }

}
