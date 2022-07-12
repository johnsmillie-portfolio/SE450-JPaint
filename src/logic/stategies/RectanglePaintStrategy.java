package logic.stategies;

// import view.gui.PaintCanvas;
import view.interfaces.PaintCanvasBase;
import java.awt.Point;
import java.awt.Color;
import java.awt.Graphics2D;


public class RectanglePaintStrategy implements IPaintStrategy {
    private Point origin;
    private Point endpoint;
    
    public RectanglePaintStrategy (Point origin, Point endpoint)
     {
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
        graphics2d.setColor(Color.BLACK);
        graphics2d.drawRect(x, y, width, height);
    }
}
