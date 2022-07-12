package logic.stategies;

import view.interfaces.PaintCanvasBase;
// import view.gui.PaintCanvas;
import java.awt.Point;

import model.ShapeColor;
import model.ShapeShadingType;

import java.awt.Color;
import java.awt.Graphics2D;


public class EllipsePaintStrategy implements IPaintStrategy {
    private Point origin;
    private Point endpoint;
    private ShapeColor fillColor;
    private ShapeColor strokeColor;
    private ShapeShadingType shapeShadingType;
    Color secondaryColor;
        
    public EllipsePaintStrategy(Point origin, Point endpoint,
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
       
        int x = Math.min(this.origin.x, this.endpoint.x);
        int y = Math.min(this.origin.y, this.endpoint.y);
        int height = Math.max(this.origin.y,
                this.endpoint.y) - y;
        int width = Math.max(this.origin.x, this.endpoint.x) - x;

        if (shapeShadingType.equals(ShapeShadingType.FILLED_IN) || 
        shapeShadingType.equals(shapeShadingType.OUTLINE_AND_FILLED_IN)) {
            Graphics2D graphics2dFill = c.getGraphics2D();
            graphics2dFill.setColor(secondaryColor = Color.PINK);
            graphics2dFill.fillOval(x, y, width, height);

        }

        if (shapeShadingType.equals(ShapeShadingType.OUTLINE) || 
        shapeShadingType.equals(shapeShadingType.OUTLINE_AND_FILLED_IN)) {
            Graphics2D graphics2dOutline = c.getGraphics2D();
            graphics2dOutline.setColor(Color.BLACK);
            graphics2dOutline.drawOval(x, y, width, height);
        }

    }
}
