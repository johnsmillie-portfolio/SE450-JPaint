package logic.paintstrategy;

import view.interfaces.PaintCanvasBase;
// import view.gui.PaintCanvas;
import java.awt.Point;

import model.ShapeColor;
import model.ShapeShadingType;


import java.awt.Graphics2D;

public class TrianglePaintStrategy implements IPaintStrategy {
    //private Point origin;
    //private Point endpoint;
    private ShapeColor fillColor;
    private ShapeColor strokeColor;
    private ShapeShadingType shapeShadingType;
    private int[] xPoints = new int[3];
    private int[] yPoints = new int[3];
        
    public TrianglePaintStrategy(
        ShapeColor fillColor,
        ShapeColor strokeColor,
        ShapeShadingType shapeShadingType)
    {
            //this.origin = origin;
            //this.endpoint = endpoint;
            this.fillColor = fillColor;
            this.strokeColor = strokeColor;
            this.shapeShadingType = shapeShadingType;
    }

    public void paint(PaintCanvasBase c, Point origin, Point endpoint) {
        xPoints[0] = Math.min(origin.x, endpoint.x);
        yPoints[0] = Math.min(origin.y, endpoint.y);
        xPoints[1] = Math.max(origin.x, endpoint.x);
        yPoints[1] = Math.max(origin.y, endpoint.y);
        xPoints[2] = Math.min(origin.x, endpoint.x);
        yPoints[2] = Math.max(origin.y, endpoint.y);

        if (shapeShadingType.equals(ShapeShadingType.FILLED_IN) || 
        shapeShadingType.equals(ShapeShadingType.OUTLINE_AND_FILLED_IN)) {
            Graphics2D graphics2dFill = c.getGraphics2D();
            graphics2dFill.setColor(fillColor.getColor());
            graphics2dFill.fillPolygon(xPoints, yPoints, 3);

        }

        if (shapeShadingType.equals(ShapeShadingType.OUTLINE) || 
        shapeShadingType.equals(ShapeShadingType.OUTLINE_AND_FILLED_IN)) {
            Graphics2D graphics2dOutline = c.getGraphics2D();
            graphics2dOutline.setColor(strokeColor.getColor());
            graphics2dOutline.drawPolygon(xPoints, yPoints, 3);

        }
    }
}
