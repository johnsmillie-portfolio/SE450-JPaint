package logic.paintstrategy;

import view.interfaces.PaintCanvasBase;
import java.awt.Point;
import java.awt.BasicStroke;
import java.awt.Stroke;
import java.awt.Graphics2D;

public class RectanglePaintProxy extends ProxyPaintStrategy {
    Stroke s = new BasicStroke(4.0f, BasicStroke.CAP_SQUARE, 
    BasicStroke.JOIN_MITER, 10.0f, new float[] {16.0f,20.0f}, 0.0f); 

    public RectanglePaintProxy(IPaintStrategy paintStrategy, 
            PaintCanvasBase c, Point origin, Point endpoint) {
        super(paintStrategy);
        //TODO Auto-generated constructor stub
        int x = Math.min(origin.x, endpoint.x) - 5;
        int y = Math.min(origin.y, endpoint.y) - 5;
        int height = (Math.max(origin.y, endpoint.y) - y)  + 6;
        int width = (Math.max(origin.x, endpoint.x) - x) + 6;
        Graphics2D graphics2dDashedOutline = c.getGraphics2D();
        graphics2dDashedOutline.setStroke(s);
        graphics2dDashedOutline.drawRect(x, y, width, height);
    }
    
}
