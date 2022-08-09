package logic.paintstrategy;

import view.interfaces.PaintCanvasBase;
import java.awt.Point;
import java.awt.BasicStroke;
import java.awt.Stroke;
import java.awt.Graphics2D;

public class RectanglePaintProxy extends ProxyPaintStrategy {
    boolean mySelected;

    Stroke s = new BasicStroke(1.0f, BasicStroke.CAP_SQUARE, 
    BasicStroke.JOIN_MITER, 10.0f, new float[] {16.0f,20.0f}, 0.0f); 

    public RectanglePaintProxy(IPaintStrategy paintStrategy, boolean selected) {
        super(paintStrategy, selected);
        
        
    }
        public void paint (PaintCanvasBase c, Point origin, Point endpoint){
            if (mySelected)
                dashedOutlinePaint(c, origin, endpoint);
            super.paint(c, origin, endpoint);
        }
        public void dashedOutlinePaint (PaintCanvasBase c, Point origin, Point endpoint){
            int x = Math.min(origin.x, endpoint.x) - 4;
            int y = Math.min(origin.y, endpoint.y) - 4;
            int height = (Math.max(origin.y, endpoint.y) - y)  + 4;
            int width = (Math.max(origin.x, endpoint.x) - x) + 4;
            Graphics2D graphics2dDashedOutline = c.getGraphics2D();
            graphics2dDashedOutline.setStroke(s);
            graphics2dDashedOutline.drawRect(x, y, width, height);
        }
    
       public void setSelected(boolean selected){
            this.mySelected = selected;
        }
}
