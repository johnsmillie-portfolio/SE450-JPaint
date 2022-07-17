package logic.observer;

public interface ISubscriber<T> {
    void notifyUpdate(T updatedValue);
}
