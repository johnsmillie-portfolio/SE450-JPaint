package logic.dragstrategy;

import java.awt.Point;

import logic.commands.AddShapeCommand;
//import logic.commands.AddShapeCommandBuilder;
import logic.observer.IStatefulListPublisher;
import model.ShapeColor;
import model.ShapeShadingType;
import model.ShapeType;
import view.interfaces.IPaintShape;

public class AddNewShapeDragStrategy
        extends StatefulDragStrategy {
    private IStatefulListPublisher<IPaintShape> visibleShapesListPublisher;
    private ShapeType shapeType;
    private ShapeColor fillColor;
    private ShapeColor strokeColor;
    private ShapeShadingType shapeShadingType;

    public AddNewShapeDragStrategy(Point startPoint,
            IStatefulListPublisher<IPaintShape> visibleShapesListPublisher,
            ShapeType shapeType, ShapeColor fillColor,
            ShapeColor strokeColor,
            ShapeShadingType shapeShadingType) {
        super(startPoint);
        this.visibleShapesListPublisher = visibleShapesListPublisher;
        this.shapeType = shapeType;
        this.fillColor = fillColor;
        this.strokeColor = strokeColor;
        this.shapeShadingType = shapeShadingType;
    }

    @Override
    public void endDrag(Point p) {
        var addShapeCommand = new AddShapeCommand(startPoint, p,
                this.visibleShapesListPublisher, shapeType,
                fillColor, strokeColor, shapeShadingType
        );
       
        addShapeCommand.invoke();
    }
   
}
