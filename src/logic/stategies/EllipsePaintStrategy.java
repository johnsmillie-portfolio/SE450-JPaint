package logic.stategies;

import view.interfaces.PaintCanvasBase;
// import view.gui.PaintCanvas;
import java.awt.Point;
import java.awt.Color;
import java.awt.Graphics2D;


public class EllipsePaintStrategy implements IPaintStrategy {
    public Point origin;
    public Point endpoint;
        
    public EllipsePaintStrategy(Point origin, Point endpoint){
        this.origin = origin;
        this.endpoint = endpoint;
    }
    public void paint(PaintCanvasBase c) {
        // TODO
       
        int x = Math.min(this.origin.x, this.endpoint.x);
        int y = Math.min(this.origin.y, this.endpoint.y);
        int height = Math.max(this.origin.y,
                this.endpoint.y) - y;
        int width = Math.max(this.origin.x, this.endpoint.x) - x;

        Graphics2D graphics2d = c.getGraphics2D();
        graphics2d.setColor(Color.RED);
        graphics2d.drawOval(x, y, width, height);
    }
}
