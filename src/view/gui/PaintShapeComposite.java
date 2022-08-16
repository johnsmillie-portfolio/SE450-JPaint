package view.gui;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Stroke;


import view.interfaces.IPaintShape;
import view.interfaces.PaintCanvasBase;

public class PaintShapeComposite implements IPaintShape {
    private List<IPaintShape> children;
    private Point origin;
    private Point endpoint;
    private boolean selected;


    public PaintShapeComposite(List<IPaintShape> selectedShapes) {
        this.children = selectedShapes;
        this.origin = this.getOrigin();
        this.endpoint = this.getEndpoint();
        this.setSelected(false);
        this.selected = true;
    }

   

    @Override
    public void paint(PaintCanvasBase c) {
        if (this.selected){
            Stroke s = new BasicStroke  (1.0f, BasicStroke.CAP_SQUARE, 
            BasicStroke.JOIN_MITER, 10.0f, 
            new float[] {16.0f,20.0f}, 0.0f);
            int height = this.getEndpoint().y - this.getOrigin().y;
            int width = this.getEndpoint().x - this.getOrigin().x;
            Graphics2D graphics2dDashedOutline = c.getGraphics2D();
            graphics2dDashedOutline.setStroke(s);
            graphics2dDashedOutline.drawRect(this.getOrigin().x - 2, this.getOrigin().y - 2, width + 6, height + 6);
        }
        for (IPaintShape shape : children) {
            shape.paint(c);
        }
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
        this.origin = new Point(this.origin.x + x, this.origin.y + y);
        this.endpoint = new Point(this.endpoint.x + x, this.endpoint.y + y);
        for (IPaintShape shape : children) {
            shape.move(x, y);
        }
    }

    @Override
    public IPaintShape cloneShape() {
        List<IPaintShape>  clonedShapes = new ArrayList<IPaintShape>();
        for (IPaintShape shape : children) {
           clonedShapes.add(shape.cloneShape());
        }
        return new PaintShapeComposite(clonedShapes);
    }

    @Override
    public void setSelected(boolean selected) {
        this.selected = selected;
        for (IPaintShape shape : children) {
            shape.setSelected(selected);
        }
        
    }

    @Override
    public Point getEndpoint() {
        Point p = new Point(0,0);
        for (IPaintShape shape : children) {
            Point temp = shape.getEndpoint();
        
            if (temp.x > p.x) {
                p.x = temp.x;
            }
            if (temp.y > p.y) {
                p.y = temp.y;
            }
         }
        return p;
    }
    
    @Override
    public Point getOrigin() {
        Point p = new Point(Integer.MAX_VALUE, Integer.MAX_VALUE);
        for (IPaintShape shape : children) {
            Point temp = shape.getOrigin();
        
            if (temp.x < p.x) {
                p.x = temp.x;
            }
            if (temp.y < p.y) {
                p.y = temp.y;
            }
         }
        return p;
    }

    @Override
    public boolean isComposite() {
        
        return true;
    }
    
    public void setThisSelected (boolean selected){
        this.selected = selected;
    }
    public List<IPaintShape> getChildren(){
        return this.children;
    }

}
