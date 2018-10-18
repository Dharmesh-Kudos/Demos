package project.com.eventbusdemo;

public class Events {

    public static class AllEvents<T> {
        private T value;

        public AllEvents(T value) {
            this.value = value;
        }

        public T getData() {
            return value;
        }
    }
}
