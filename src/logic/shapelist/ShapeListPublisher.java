package logic.shapelist;

import java.util.ArrayList;
import java.util.Arrays;

import view.interfaces.IPaintShape;

public class ShapeListPublisher
        implements IShapeListPublisher {

    private ArrayList<IPaintShape> shapes;
    private ArrayList<IShapeListSubscriber> subscribers;

    public ShapeListPublisher() {
        this.subscribers = new ArrayList<>();
        this.shapes = new ArrayList<>();
    }

    public void subscribe(IShapeListSubscriber sub) {
        this.subscribers.add(sub);
    }

    public void updateSubs() {
        IPaintShape[] sl = new IPaintShape[this.shapes
                .size()];
        this.shapes.toArray(sl);

        for (IShapeListSubscriber subscriber : this.subscribers) {
            subscriber.notifyUpdatedShapeList(sl);
        }
    }

    public void updateShapeList(IPaintShape[] shapes) {
        this.shapes = new ArrayList<>(Arrays.asList(shapes));
        this.updateSubs();
    }

    public void addShape(IPaintShape shape) {
        this.shapes.add(shape);
        this.updateSubs();
    }

    public void removeShape(IPaintShape shape) {
        this.shapes.remove(shape);
        this.updateSubs();
    }
}
