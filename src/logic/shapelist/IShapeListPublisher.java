package logic.shapelist;

import view.interfaces.IPaintShape;

public interface IShapeListPublisher {
    void subscribe(IShapeListSubscriber sub);

    void addShape(IPaintShape shape);

    void removeShape(IPaintShape shape);

    void updateShapeList(IPaintShape[] shapes);
}
