package view.interfaces;

import java.awt.Point;
import java.util.List;

public interface IPaintShape {
    
    void paint(PaintCanvasBase c);
    boolean collides(Point from, Point to);
    void move(int x, int y);
    IPaintShape cloneShape();
    void setSelected (boolean selected);
    Point getEndpoint();
    Point getOrigin();
    //List<IPaintShape> getChildren();

}
