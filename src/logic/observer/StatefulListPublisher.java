package logic.observer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StatefulListPublisher<E>
        implements IStatefulListPublisher<E> {

    private IPublisher<List<E>> parent;
    private ArrayList<E> members;

    public StatefulListPublisher(
            IPublisher<List<E>> parent) {
        this.parent = parent;
        this.members = new ArrayList<>();
    }

    @Override
    public void subscribe(ISubscriber<List<E>> sub) {
        this.parent.subscribe(sub);
    }

    @Override
    public void announce(List<E> members) {
        this.members = new ArrayList<>(members);
        this.parent.announce(members);
    }

    @Override
    public void retrigger() {
        // Clients shouldn't have the ability to modify this list
        this.parent.announce(
                Collections.unmodifiableList(this.members));
    }

    @Override
    public void add(E element) {
        this.members.add(element);
        this.retrigger();
    }
    
    @Override
    public void addCollection(List<E> collection){
        this.members.addAll(collection);
        this.retrigger();
    }

    @Override
    public void remove(E element) {
        this.members.remove(element);
        this.retrigger();
    }

    @Override
    public void removeCollection(List<E> collection){
        this.members.removeAll(collection);
        this.retrigger();
    }
}
