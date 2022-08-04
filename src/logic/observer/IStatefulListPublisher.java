package logic.observer;

import java.util.List;

public interface IStatefulListPublisher<E>
        extends IStatefulPublisher<List<E>> {
    void add(E element);

    void remove(E element);

    void addCollection(List<E> elementList);

    void removeCollection(List<E> elementList);
}
  
