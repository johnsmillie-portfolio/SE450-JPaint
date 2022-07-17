package logic.observer;

import java.util.ArrayList;

public class SimplePublisher<T> implements IPublisher<T> {

    private ArrayList<ISubscriber<T>> subscribers;

    public SimplePublisher() {
        this.subscribers = new ArrayList<>();
    }

    @Override
    public void subscribe(ISubscriber<T> sub) {
        this.subscribers.add(sub);
    }

    @Override
    public void announce(T updatedValue) {
        for (var s : this.subscribers) {
            s.notifyUpdate(updatedValue);
        }
    }
}
