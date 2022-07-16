package logic.observer;

import java.util.List;

public interface IStatefulPublisher<E>
        extends IPublisher<List<E>>, IRetriggerable {
}
