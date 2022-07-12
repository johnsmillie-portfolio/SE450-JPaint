package logic.observer;

public interface IPublisher<T> {
    void subscribe(ISubscriber<T> sub);

    void announce(T updatedValue);
}
