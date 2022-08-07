package logic.paintstrategy;

// import view.gui.PaintCanvas;
import view.interfaces.PaintCanvasBase;
import java.awt.Point;
import model.ShapeColor;
import model.ShapeShadingType;
import java.awt.Color;
import java.awt.Graphics2D;


public class RectanglePaintStrategy implements IPaintStrategy { 
    private ShapeColor fillColor;
    private ShapeColor strokeColor;
    private ShapeShadingType shapeShadingType;
    Color secondaryColor;
   
    public RectanglePaintStrategy (  
        ShapeColor fillColor,
        ShapeColor strokeColor,
        ShapeShadingType shapeShadingType)
   {
        this.fillColor = fillColor;
        this.strokeColor = strokeColor;
        this.shapeShadingType = shapeShadingType;
    }
    
    public void paint(PaintCanvasBase c, Point origin, Point endpoint) {
        int x = Math.min(origin.x, endpoint.x);
        int y = Math.min(origin.y, endpoint.y);
        int height = Math.max(origin.y, endpoint.y) - y;
        int width = Math.max(origin.x, endpoint.x) - x;
    
        if (shapeShadingType.equals(ShapeShadingType.FILLED_IN) || 
        shapeShadingType.equals(ShapeShadingType.OUTLINE_AND_FILLED_IN)) {
            Graphics2D graphics2dFill = c.getGraphics2D();
            graphics2dFill.setColor(fillColor.getColor());
            graphics2dFill.fillRect(x, y, width, height);

        }

        if (shapeShadingType.equals(ShapeShadingType.OUTLINE) || 
        shapeShadingType.equals(ShapeShadingType.OUTLINE_AND_FILLED_IN)) {
            Graphics2D graphics2dOutline = c.getGraphics2D();
            graphics2dOutline.setColor(strokeColor.getColor());
            graphics2dOutline.drawRect(x, y, width, height);
        }

        

        
        
        
    }

    @Override
    public void setSelected(boolean isSelected) {
        // TODO Auto-generated method stub
        
    }
}
