package logic.stategies;

import view.interfaces.PaintCanvasBase;
// import view.gui.PaintCanvas;
import java.awt.Point;
import java.awt.Color;
import java.awt.Graphics2D;

public class TrianglePaintStrategy implements IPaintStrategy {
    public Point origin;
    public Point endpoint;
    int[] xPoints = new int[3];
    int[] yPoints = new int[3];
        
    public TrianglePaintStrategy(Point origin, Point endpoint){
        this.origin = origin;
        this.endpoint = endpoint;
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

        // Filled in rectangle
        Graphics2D graphics2d = c.getGraphics2D();
        graphics2d.setColor(Color.BLUE);
        graphics2d.drawPolygon(xPoints, yPoints, 3);
    }
}
