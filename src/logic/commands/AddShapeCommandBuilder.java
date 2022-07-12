package logic.commands;

// import model.ShapeColor;
// import model.ShapeShadingType;
// import model.ShapeType;
import java.awt.Point;

import logic.observer.IStatefulListPublisher;
import view.interfaces.IPaintShape;

public class AddShapeCommandBuilder {
    private Point origin;
    private Point endpoint;
    private IStatefulListPublisher<IPaintShape> visibleShapesPub;

    // private ShapeColor fillColor;
    // private ShapeColor strokeColor;
    // private ShapeShadingType shapeShadingType;
    // private ShapeType shapeType;

    public AddShapeCommandBuilder(
            IStatefulListPublisher<IPaintShape> visibleShapesPub
    // , ShapeColor fillColor,
    // ShapeColor strokeColor,
    // ShapeShadingType shapeShadingType,
    // ShapeType shapeType
    ) {
        this.visibleShapesPub = visibleShapesPub;

        // this.fillColor = fillColor;
        // this.strokeColor = strokeColor;
        // this.shapeShadingType = shapeShadingType;
        // this.shapeType = shapeType;
    }

    public void setOrigin(Point p) {
        this.origin = p;
    }

    public void setEndpoint(Point p) {
        this.endpoint = p;
    }

    public AddShapeCommand build() {
        return new AddShapeCommand(this.origin,
                this.endpoint, this.visibleShapesPub
        // , fillColor, strokeColor,
        // shapeShadingType, shapeType
        );
    }

}
