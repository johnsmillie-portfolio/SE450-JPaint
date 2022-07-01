package logic;

import java.awt.event.MouseEvent;
import javax.swing.event.MouseInputAdapter;
import javax.swing.event.MouseInputListener;

import view.gui.PaintCanvas;

import java.awt.Point;
import java.awt.Graphics2D;
import java.awt.Color;
public class ClickHandler extends MouseInputAdapter implements MouseInputListener {
    Point start_point;
    PaintCanvas paintCanvas;

    public ClickHandler (PaintCanvas paintCanvas) {
        this.paintCanvas = paintCanvas;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        System.out.println("mouse clicked called");
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        System.out.println("mouse pressed called");
        this.start_point = e.getPoint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        System.out.println("mouse released called");
        int x = Math.min(this.start_point.x, e.getX());
        int y = Math.min(this.start_point.y, e.getY());
        int height = Math.max(this.start_point.y, e.getY()) - y;
        int width = Math.max(this.start_point.x, e.getX()) - x;

        
        // Filled in rectangle
        Graphics2D graphics2d = this.paintCanvas.getGraphics2D();
        graphics2d.setColor(Color.GREEN);
        graphics2d.fillRect(x, y, width, height);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        System.out.println("mouse entered called");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        System.out.println("mouse exited called");
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // TODO Auto-generated method stub
        System.out.println("mouse dragged called");
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // TODO Auto-generated method stub
        System.out.println("mouse moved called");
    }
}
