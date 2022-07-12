package logic.stategies;

import view.interfaces.PaintCanvasBase;
// import view.gui.PaintCanvas;
import java.awt.Point;

import model.ShapeColor;
import model.ShapeShadingType;

import java.awt.Color;
import java.awt.Graphics2D;

public class TrianglePaintStrategy implements IPaintStrategy {
    private Point origin;
    private Point endpoint;
    private ShapeColor fillColor;
    private ShapeColor strokeColor;
    private ShapeShadingType shapeShadingType;
    Color secondaryColor;
    int[] xPoints = new int[3];
    int[] yPoints = new int[3];
        
    public TrianglePaintStrategy(Point origin, Point endpoint,
        ShapeColor fillColor,
        ShapeColor strokeColor,
        ShapeShadingType shapeShadingType)
    {
            this.origin = origin;
            this.endpoint = endpoint;
            this.fillColor = fillColor;
            this.strokeColor = strokeColor;
            this.shapeShadingType = shapeShadingType;
    }

    public void paint(PaintCanvasBase c) {
        // TODO
        // Implement paint rectagle algorithm
        xPoints[0] = Math.min(this.origin.x, this.endpoint.x);
        yPoints[0] = Math.min(this.origin.y, this.endpoint.y);
        xPoints[1] = Math.max(this.origin.x, this.endpoint.x);
        yPoints[1] = Math.max(this.origin.y, this.endpoint.y);
        xPoints[2] = Math.min(this.origin.x, this.endpoint.x);
        yPoints[2] = Math.max(this.origin.y, this.endpoint.y);

        if (shapeShadingType.equals(ShapeShadingType.FILLED_IN) || 
        shapeShadingType.equals(shapeShadingType.OUTLINE_AND_FILLED_IN)) {
            Graphics2D graphics2dFill = c.getGraphics2D();
            graphics2dFill.setColor(secondaryColor = Color.PINK);
            graphics2dFill.fillPolygon(xPoints, yPoints, 3);

        }

        if (shapeShadingType.equals(ShapeShadingType.OUTLINE) || 
        shapeShadingType.equals(shapeShadingType.OUTLINE_AND_FILLED_IN)) {
            Graphics2D graphics2dOutline = c.getGraphics2D();
            graphics2dOutline.setColor(Color.BLACK);
            graphics2dOutline.drawPolygon(xPoints, yPoints, 3);

        }
    }
}
