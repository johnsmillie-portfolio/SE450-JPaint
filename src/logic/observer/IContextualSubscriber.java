package logic.observer;

public interface IContextualSubscriber<T,C> {
    void notifyUpdate(T updatedValue, C context);
}
