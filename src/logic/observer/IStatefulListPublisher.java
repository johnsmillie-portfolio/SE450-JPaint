package logic.observer;

public interface IStatefulListPublisher<E>
        extends IStatefulPublisher<E> {
    void add(E element);

    void remove(E element);
}
