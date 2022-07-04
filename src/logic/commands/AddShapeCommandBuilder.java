package logic.commands;

// import model.ShapeColor;
// import model.ShapeShadingType;
// import model.ShapeType;
import view.gui.PaintCanvas;
import view.interfaces.IPaintShape;

import java.awt.Point;
import java.util.Collection;

public class AddShapeCommandBuilder {
    private Point origin;
    private Point endpoint;
    private PaintCanvas canvas;
    private Collection<IPaintShape> shapeList;

    // private ShapeColor fillColor;
    // private ShapeColor strokeColor;
    // private ShapeShadingType shapeShadingType;
    // private ShapeType shapeType;

    public AddShapeCommandBuilder(PaintCanvas canvas,
            Collection<IPaintShape> shapeList
    // , ShapeColor fillColor,
    // ShapeColor strokeColor,
    // ShapeShadingType shapeShadingType,
    // ShapeType shapeType
    ) {
        this.canvas = canvas;
        this.shapeList = shapeList;
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
        return new AddShapeCommand(origin, endpoint, canvas,
                this.shapeList
        // , fillColor, strokeColor,
        // shapeShadingType, shapeType
        );
    }

}
