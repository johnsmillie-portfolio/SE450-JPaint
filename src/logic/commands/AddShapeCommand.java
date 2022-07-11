package logic.commands;

// import model.ShapeColor;
// import model.ShapeShadingType;
// import model.ShapeType;
import view.gui.PaintShape;
import view.interfaces.IPaintShape;
import java.awt.Point;
import logic.shapelist.IShapeListPublisher;

public class AddShapeCommand
        implements ICommand, IUndoable {

    private Point origin;
    private Point endpoint;
    private IShapeListPublisher visibleShapeListPublisher;
    private IPaintShape createdShape;
    // private ShapeColor fillColor;
    // private ShapeColor strokeColor;
    // private ShapeShadingType shapeShadingType;
    // private ShapeType shapeType;

    public AddShapeCommand(Point origin, Point endpoint,
            IShapeListPublisher visibleShapeListPublisher
    // , ShapeColor fillColor, ShapeColor strokeColor,
    // ShapeShadingType shapeShadingType,
    // ShapeType shapeType
    ) {
        this.origin = origin;
        this.endpoint = endpoint;
        this.visibleShapeListPublisher = visibleShapeListPublisher;
        // this.fillColor = fillColor;
        // this.strokeColor = strokeColor;
        // this.shapeShadingType = shapeShadingType;
        // this.shapeType = shapeType;
    }

    @Override
    public void undo() {
        this.removeShape();
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

        this.visibleShapeListPublisher
                .addShape(this.createdShape);
    }

    private void removeShape() {
        this.visibleShapeListPublisher
                .removeShape(this.createdShape);
    }
}
