package logic.shapelist;

import view.interfaces.IPaintShape;

public interface IShapeListSubscriber {
    void notifyUpdatedShapeList(IPaintShape[] shapes);
}
