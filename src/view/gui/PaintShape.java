package view.gui;

import view.interfaces.IPaintShape;
import view.interfaces.PaintCanvasBase;
import java.awt.Point;

import javax.swing.plaf.synth.SynthPainter;

import logic.paintstrategy.EllipsePaintProxy;
import logic.paintstrategy.IPaintStrategy;
import logic.paintstrategy.IProxyPaintStrategy;
import logic.paintstrategy.ProxyPaintStrategy;
import logic.paintstrategy.RectanglePaintProxy;
import logic.paintstrategy.TrianglePaintProxy;
import model.ShapeType;

// TODO Contemplate a solution to the redundant origin and endpoint fields in PaintStrategy and PaintShape
public class PaintShape implements IPaintShape {
    
    private Point origin;
    private Point endpoint;
    private ShapeType shapeType;
    private IPaintStrategy paintStrategy;
    

    public PaintShape(Point origin, Point endpoint,
            IPaintStrategy paintStrategy, ShapeType shapeType) {
        this.origin = origin;
        this.endpoint = endpoint;
        this.paintStrategy = paintStrategy;
        this.shapeType = shapeType;
        
    }
   
    @Override
    public void paint(PaintCanvasBase c) {
        paintStrategy.paint(c, origin, endpoint);
    }

    @Override
    public boolean collides(Point from, Point to) {
        int s_l_x = Math.min(this.origin.x,
                this.endpoint.x);
        int s_t_y = Math.min(this.origin.y,
                this.endpoint.y);
        int s_r_x = Math.max(this.origin.x,
                this.endpoint.x);
        int s_b_y = Math.max(this.origin.y,
                this.endpoint.y);

        int d_l_x = Math.min(from.x, to.x);
        int d_t_y = Math.min(from.y, to.y);
        int d_r_x = Math.max(from.x, to.x);
        int d_b_y = Math.max(from.y, to.y);

        return ((d_r_x > s_l_x) && (d_b_y > s_t_y)
                && (s_r_x > d_l_x) && (s_b_y > d_t_y));
    }

    @Override
    public void move(int x, int y) {
        this.origin = new Point(this.origin.x + x,
                this.origin.y + y);

        this.endpoint = new Point(this.endpoint.x + x,
                this.endpoint.y + y);
    }
    public IPaintShape cloneShape() {
        IPaintStrategy newPaintStrategy;
        switch (shapeType) {
            case RECTANGLE:
                 newPaintStrategy = new RectanglePaintProxy(
                    ((ProxyPaintStrategy) this.paintStrategy).getPaintStrategy(), false);
                break;
            case ELLIPSE:
                newPaintStrategy = new EllipsePaintProxy(
                    ((ProxyPaintStrategy) this.paintStrategy).getPaintStrategy(), false);
                break;
            case TRIANGLE:
                newPaintStrategy = new TrianglePaintProxy(
                    ((ProxyPaintStrategy) this.paintStrategy).getPaintStrategy(), false);
                break;
            default:
                throw new Error( "Do not recognize this shape" + shapeType);
        }
        PaintShape shapeClone = new PaintShape(
            new Point(this.origin), new Point(this.endpoint), 
            newPaintStrategy, this.shapeType);
            
            
        return (IPaintShape) shapeClone;
    }
    

    public void setPaintStrategy (boolean selected){
        ((IProxyPaintStrategy) this.paintStrategy).setSelected(selected);
    }
}
