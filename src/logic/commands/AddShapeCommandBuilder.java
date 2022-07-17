package logic.commands;

import model.ShapeColor;
import model.ShapeShadingType;
import model.ShapeType;
import view.interfaces.IPaintShape;
import java.awt.Point;
import logic.observer.IStatefulListPublisher;

public class AddShapeCommandBuilder {
    private Point origin;
    private Point endpoint;
    private IStatefulListPublisher<IPaintShape> visibleShapesPub;
    private ShapeColor fillColor;
    private ShapeColor strokeColor;
    private ShapeShadingType shapeShadingType;
    private ShapeType shapeType;

    public AddShapeCommandBuilder(
            IStatefulListPublisher<IPaintShape> visibleShapesPub,
            ShapeType shapeType, ShapeColor fillColor,
            ShapeColor strokeColor,
            ShapeShadingType shapeShadingType) {
        this.shapeType = shapeType;
        this.fillColor = fillColor;
        this.strokeColor = strokeColor;
        this.shapeShadingType = shapeShadingType;
        this.visibleShapesPub = visibleShapesPub;
    }

    public void setOrigin(Point p) {
        this.origin = p;
    }

    public void setEndpoint(Point p) {
        this.endpoint = p;
    }

    public AddShapeCommand build() {
        return new AddShapeCommand(this.origin,
                this.endpoint, this.visibleShapesPub,
                this.shapeType, this.fillColor,
                this.strokeColor, this.shapeShadingType);
    }
}
