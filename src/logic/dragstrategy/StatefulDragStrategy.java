package logic.dragstrategy;

import java.awt.Point;

public abstract class StatefulDragStrategy
        implements IDragStrategy {
    protected Point startPoint;

    public StatefulDragStrategy(Point startPoint) {
        this.startPoint = startPoint;
    }
}
