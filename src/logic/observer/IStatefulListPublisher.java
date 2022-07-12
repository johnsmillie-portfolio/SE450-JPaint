package logic.observer;

import java.util.List;

public interface IStatefulListPublisher<E>
        extends IPublisher<List<E>>, IRetriggerable {
    void add(E element);

    void remove(E element);
}
