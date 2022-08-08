package view.interfaces;

import java.awt.Point;

public interface IPaintShape {
    
    void paint(PaintCanvasBase c);
    boolean collides(Point from, Point to);
    void move(int x, int y);
    IPaintShape cloneShape();
    void setSelected(boolean b);
    void setPaintStrategy(boolean b);

}
