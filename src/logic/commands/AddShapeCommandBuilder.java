package logic.commands;

// import model.ShapeColor;
// import model.ShapeShadingType;
// import model.ShapeType;
import java.awt.Point;
import logic.shapelist.IShapeListPublisher;

public class AddShapeCommandBuilder {
    private Point origin;
    private Point endpoint;
    private IShapeListPublisher visibleShapesListPublisher;

    // private ShapeColor fillColor;
    // private ShapeColor strokeColor;
    // private ShapeShadingType shapeShadingType;
    // private ShapeType shapeType;

    public AddShapeCommandBuilder(
            IShapeListPublisher visibleShapesListPublisher
    // , ShapeColor fillColor,
    // ShapeColor strokeColor,
    // ShapeShadingType shapeShadingType,
    // ShapeType shapeType
    ) {
        this.visibleShapesListPublisher = visibleShapesListPublisher;

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
        return new AddShapeCommand(origin, endpoint,
                visibleShapesListPublisher
        // , fillColor, strokeColor,
        // shapeShadingType, shapeType
        );
    }

}
