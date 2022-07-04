package logic.commands;

// import model.ShapeColor;
// import model.ShapeShadingType;
// import model.ShapeType;
import view.gui.PaintCanvas;
import view.gui.PaintShape;
import view.interfaces.IPaintShape;
import java.awt.Point;
import java.util.Collection;


public class AddShapeCommand
        implements ICommand, IUndoable {

    private Point origin;
    private Point endpoint;
    private PaintCanvas canvas;
    private IPaintShape createdShape;
    private Collection<IPaintShape> shapeList;
    // private ShapeColor fillColor;
    // private ShapeColor strokeColor;
    // private ShapeShadingType shapeShadingType;
    // private ShapeType shapeType;

    public AddShapeCommand(Point origin, Point endpoint,
            PaintCanvas canvas,
            Collection<IPaintShape> shapeList
    // ShapeColor fillColor, ShapeColor strokeColor,
    // ShapeShadingType shapeShadingType,
    // ShapeType shapeType
    ) {
        this.origin = origin;
        this.endpoint = endpoint;
        this.canvas = canvas;
        this.shapeList = shapeList;
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

        // store this command in the undo stack
        // TODO: Refactor to visitor pattern
        CommandHistory.add(this);
    }

    private void addShape() {
        // Create a new shape
        if (this.createdShape == null) {
            this.createdShape = new PaintShape(this.origin,
                    this.endpoint);
        }

        this.shapeList.add(this.createdShape);
        this.repaint();
    }

    private void removeShape() {
        // Remove our shape
        this.shapeList.remove(this.createdShape);
        this.repaint();
    }

    private void repaint() {
        canvas.paint(canvas.getGraphics());
    }

}
