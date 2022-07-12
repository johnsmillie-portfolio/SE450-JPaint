package logic.dragstrategy;

import java.awt.Point;
import java.util.List;

import logic.commands.MoveShapesCommand;
import logic.observer.IRetriggerable;
import view.interfaces.IPaintShape;

public class MoveDragStrategy extends StatefulDragStrategy {
    private List<IPaintShape> moveShapes;
    private IRetriggerable shapeChangeListener;

    public MoveDragStrategy(Point startPoint,
            List<IPaintShape> moveShapes,
            IRetriggerable shapeChangeListener) {
        super(startPoint);
        this.shapeChangeListener = shapeChangeListener;
        this.moveShapes = moveShapes;
    }

    @Override
    public void endDrag(Point p) {
        if (this.moveShapes != null) {
            (new MoveShapesCommand(this.startPoint, p,
                    moveShapes, this.shapeChangeListener))
                            .invoke();
        }
    }

}
