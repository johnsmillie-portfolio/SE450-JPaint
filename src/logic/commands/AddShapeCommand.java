package logic.commands;

//import model.ShapeColor;
//import model.ShapeShadingType;
import model.ShapeType;
import view.gui.PaintCanvas;
import view.gui.PaintShape;
import view.interfaces.IPaintShape;
import java.awt.Point;
import java.util.Collection;

import logic.stategies.EllipsePaintStrategy;
import logic.stategies.IPaintStrategy;
import logic.stategies.RectanglePaintStrategy;
import logic.stategies.TrianglePaintStrategy;
import model.ShapeType;


public class AddShapeCommand
        implements ICommand, IUndoable {

    private Point origin;
    private Point endpoint;
    private PaintCanvas canvas;
    private IPaintShape createdShape;
    private Collection<IPaintShape> shapeList;
    private ShapeType shapeType;
    IPaintStrategy paintStrategy;
    //private ShapeColor fillColor;
    //private ShapeColor strokeColor;
   // private ShapeShadingType shapeShadingType;
    

    public AddShapeCommand(Point origin, Point endpoint,
            PaintCanvas canvas,
            Collection<IPaintShape> shapeList,  ShapeType shapeType
    //ShapeColor fillColor, ShapeColor strokeColor,
    //ShapeShadingType shapeShadingType,
   
    ) {
        this.origin = origin;
        this.endpoint = endpoint;
        this.canvas = canvas;
        this.shapeList = shapeList;
        this.shapeType = shapeType; 
        //this.fillColor = fillColor;
        //this.strokeColor = strokeColor;
       // this.shapeShadingType = shapeShadingType;
        
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
            //TODO
            //create the paintStrategy here
            if (shapeType.compareTo(ShapeType.RECTANGLE) == 0) {
                paintStrategy = new RectanglePaintStrategy(origin, endpoint);
            }
            else if (shapeType.compareTo(ShapeType.ELLIPSE) == 0) {
                paintStrategy = new EllipsePaintStrategy();
            }
            else if (shapeType.compareTo(ShapeType.TRIANGLE) == 0) {
                paintStrategy = new TrianglePaintStrategy();
            }   
           
            this.createdShape = new PaintShape(paintStrategy);
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
