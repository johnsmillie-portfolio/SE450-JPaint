package logic.commands;

// import model.ShapeColor;
// import model.ShapeShadingType;
// import model.ShapeType;
import view.interfaces.IPaintShape;
import java.awt.Point;
import java.util.List;

import logic.observer.IRetriggerable;

public class MoveShapesCommand
        implements ICommand, IUndoable {

    private Point delta;
    private List<IPaintShape> moveShapes;
    IRetriggerable shapeChangeObserver;

    public MoveShapesCommand(Point origin, Point endpoint,
            List<IPaintShape> moveShapes,
            IRetriggerable shapeChangeObserver) {
        this.delta = new Point(endpoint.x - origin.x,
                endpoint.y - origin.y);
        this.moveShapes = moveShapes;
        this.shapeChangeObserver = shapeChangeObserver;
    }

    @Override
    public void undo() {
        for (var s : this.moveShapes) {
            s.move(-delta.x, -delta.y);
        }

        this.shapeChangeObserver.retrigger();
    }

    @Override
    public void redo() {
        this.applyMove();
    }

    @Override
    public void invoke() {
        this.applyMove();
        CommandHistory.add(this);
    }

    private void applyMove() {
        for (var s : this.moveShapes) {
            s.move(delta.x, delta.y);
        }

        this.shapeChangeObserver.retrigger();
    }
}
