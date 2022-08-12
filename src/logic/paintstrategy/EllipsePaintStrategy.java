package logic.paintstrategy;

import view.interfaces.PaintCanvasBase;
// import view.gui.PaintCanvas;
import java.awt.Point;

import model.ShapeColor;
import model.ShapeShadingType;

import java.awt.Color;
import java.awt.Graphics2D;


class EllipsePaintStrategy implements IPaintStrategy {
    private ShapeColor fillColor;
    private ShapeColor strokeColor;
    private ShapeShadingType shapeShadingType;
    Color secondaryColor;
    
        
    public EllipsePaintStrategy(
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
        int height = Math.max(origin.y,endpoint.y) - y;
        int width = Math.max(origin.x, endpoint.x) - x;

        if (shapeShadingType.equals(ShapeShadingType.FILLED_IN) || 
            shapeShadingType.equals(ShapeShadingType.OUTLINE_AND_FILLED_IN)) {
            Graphics2D graphics2dFill = c.getGraphics2D();
            graphics2dFill.setColor(fillColor.getColor());
            graphics2dFill.fillOval(x, y, width, height);
        }

        if (shapeShadingType.equals(ShapeShadingType.OUTLINE) || 
        shapeShadingType.equals(ShapeShadingType.OUTLINE_AND_FILLED_IN)) {
            Graphics2D graphics2dOutline = c.getGraphics2D();
            graphics2dOutline.setColor(strokeColor.getColor());
            graphics2dOutline.drawOval(x, y, width, height);
        }

    }
}
