package logic.observer;

public interface IContextualPublisher<T, C> extends IPublisher<T> {
    void subscribe(IContextualSubscriber<T, C> sub, C context);

    void announce(T updatedValue);
}
