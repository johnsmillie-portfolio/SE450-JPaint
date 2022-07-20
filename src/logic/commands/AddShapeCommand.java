package logic.commands;

import model.ShapeColor;
import model.ShapeShadingType;
import model.ShapeType;
import view.gui.PaintShape;
import view.interfaces.IPaintShape;
import java.awt.Point;
import logic.stategies.EllipsePaintStrategy;
import logic.stategies.IPaintStrategy;
import logic.stategies.RectanglePaintStrategy;
import logic.stategies.TrianglePaintStrategy;
import logic.observer.IStatefulListPublisher;

public class AddShapeCommand
        implements ICommand, IUndoable {

    private Point origin;
    private Point endpoint;
    private IStatefulListPublisher<IPaintShape> visibleShapesPub;
    private IPaintShape createdShape;
    private ShapeType shapeType;
    IPaintStrategy paintStrategy;
    private ShapeColor fillColor;
    private ShapeColor strokeColor;
    private ShapeShadingType shapeShadingType;

    public AddShapeCommand(Point origin, Point endpoint,
            IStatefulListPublisher<IPaintShape> visibleShapesPub,
            ShapeType shapeType, ShapeColor fillColor,
            ShapeColor strokeColor,
            ShapeShadingType shapeShadingType) {
        this.origin = origin;
        this.endpoint = endpoint;
        this.shapeType = shapeType;
        this.fillColor = fillColor;
        this.strokeColor = strokeColor;
        this.shapeShadingType = shapeShadingType;
        this.visibleShapesPub = visibleShapesPub;
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
    private IPaintStrategy getPaintStrategy() {
        switch (shapeType) {
            case RECTANGLE:
                return new RectanglePaintStrategy(fillColor,
                        strokeColor, shapeShadingType);
            
            case ELLIPSE:
                return new EllipsePaintStrategy(fillColor,
                        strokeColor, shapeShadingType);
            
            case TRIANGLE:
                return new TrianglePaintStrategy(fillColor,
                        strokeColor, shapeShadingType);
            
            default:
                throw new Error( "Do not recognize this shape" + shapeType);
        }
    }
    private void addShape() {
    
        if (this.createdShape == null) {
            // TODO probably refactor
            this.paintStrategy = getPaintStrategy();
            this.createdShape = new PaintShape(this.origin,
                    this.endpoint, this.paintStrategy);
        }

        this.visibleShapesPub.add(this.createdShape);
    }
}
