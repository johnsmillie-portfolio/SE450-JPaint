package view.interfaces;

import java.awt.Point;

public interface IPaintShape {
    
    public void paint(PaintCanvasBase c);
    public boolean collides(Point from, Point to);
    public void move(int x, int y);
    IPaintShape cloneShape();
}
