package logic.commands;

// import model.ShapeColor;
// import model.ShapeShadingType;
// import model.ShapeType;
import view.gui.PaintShape;
import view.interfaces.IPaintShape;
import java.awt.Point;
import logic.observer.IStatefulListPublisher;

public class AddShapeCommand
        implements ICommand, IUndoable {

    private Point origin;
    private Point endpoint;
    private IStatefulListPublisher<IPaintShape> visibleShapesPub;
    private IPaintShape createdShape;
    // private ShapeColor fillColor;
    // private ShapeColor strokeColor;
    // private ShapeShadingType shapeShadingType;
    // private ShapeType shapeType;

    public AddShapeCommand(Point origin, Point endpoint,
            IStatefulListPublisher<IPaintShape> visibleShapesPub
    // , ShapeColor fillColor, ShapeColor strokeColor,
    // ShapeShadingType shapeShadingType,
    // ShapeType shapeType
    ) {
        this.origin = origin;
        this.endpoint = endpoint;
        this.visibleShapesPub = visibleShapesPub;
        // this.fillColor = fillColor;
        // this.strokeColor = strokeColor;
        // this.shapeShadingType = shapeShadingType;
        // this.shapeType = shapeType;
    }

    @Override
    public void undo() {
        this.visibleShapesPub.remove(this.createdShape);
    }

    @Override
    public void redo() {
        this.addShape();
    }

    @Override
    public void invoke() {
        this.addShape();
        CommandHistory.add(this);
    }

    private void addShape() {
        if (this.createdShape == null) {
            this.createdShape = new PaintShape(this.origin,
                    this.endpoint);
        }

        this.visibleShapesPub.add(this.createdShape);
    }
}
