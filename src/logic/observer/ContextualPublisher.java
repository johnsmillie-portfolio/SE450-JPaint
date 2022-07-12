package logic.observer;

import java.util.HashMap;

public class ContextualPublisher<T, C>
        implements IContextualPublisher<T, C> {

    private IPublisher<T> _publisher;
    private HashMap<IContextualSubscriber<T, C>, C> subscriberContexts;

    public ContextualPublisher() {
        this._publisher = new SimplePublisher<>();
        this.subscriberContexts = new HashMap<>();
    }

    @Override
    public void subscribe(IContextualSubscriber<T, C> sub,
            C context) {
        this.subscriberContexts.put(sub, context);
    }

    @Override
    public void announce(T updatedValue) {
        this._publisher.announce(updatedValue);

        for (var s : this.subscriberContexts.entrySet()) {
            s.getKey().notifyUpdate(updatedValue,
                    s.getValue());
        }
    }

    @Override
    public void subscribe(ISubscriber<T> sub) {
        this._publisher.subscribe(sub);
    }
}
