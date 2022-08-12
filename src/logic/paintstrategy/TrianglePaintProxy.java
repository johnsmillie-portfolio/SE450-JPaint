package logic.paintstrategy;

import view.interfaces.PaintCanvasBase;
import java.awt.Point;
import java.awt.BasicStroke;
import java.awt.Stroke;

import model.ShapeColor;
import model.ShapeShadingType;

import java.awt.Graphics2D;

public class TrianglePaintProxy extends ProxyPaintStrategy {
    boolean mySelected;
    private int[] xPoints = new int[3];
    private int[] yPoints = new int[3];
    Stroke s = new BasicStroke(1.0f, BasicStroke.CAP_SQUARE, 
        BasicStroke.JOIN_MITER, 10.0f, new float[] {16.0f,20.0f}, 0.0f);

    public TrianglePaintProxy(
        ShapeColor fillColor, ShapeColor strokeColor, 
        ShapeShadingType shapeShadingType, boolean selected) {
        super(new TrianglePaintStrategy(fillColor, strokeColor, shapeShadingType), selected);
    }
     
    public void dashedOutlinePaint (PaintCanvasBase c, 
            Point origin, Point endpoint){
        xPoints[0] = Math.min(origin.x, endpoint.x) - 3;
        yPoints[0] = Math.min(origin.y, endpoint.y) - 6;
        xPoints[1] = Math.max(origin.x, endpoint.x) + 6;
        yPoints[1] = Math.max(origin.y, endpoint.y) + 3;
        xPoints[2] = Math.min(origin.x, endpoint.x) - 3;
        yPoints[2] = Math.max(origin.y, endpoint.y) + 3; 
        Graphics2D graphics2dDashedOutline = c.getGraphics2D();
        graphics2dDashedOutline.setStroke(s);
        graphics2dDashedOutline.drawPolygon(xPoints, yPoints, 3);
    }
}
