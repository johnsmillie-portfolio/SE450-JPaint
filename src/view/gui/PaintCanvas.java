package view.gui;

import view.interfaces.IPaintShape;
import view.interfaces.PaintCanvasBase;

import javax.swing.JComponent;

import logic.shapelist.IShapeListSubscriber;

import java.awt.*;
import java.util.Collection;

public class PaintCanvas extends PaintCanvasBase
        implements IShapeListSubscriber {
    private Collection<IPaintShape> shapeList;

    public PaintCanvas(Collection<IPaintShape> shapeList) {
        this.shapeList = shapeList;
    }

    public Graphics2D getGraphics2D() {
        return (Graphics2D) getGraphics();
    }

    @Override
    public void paint(Graphics g) {
        var size = this.getSize();

        Graphics2D graphics2d = (Graphics2D) g;
        graphics2d.setColor(Color.WHITE);
        graphics2d.fillRect(0, 0, this.getSize().width, this.getSize().width);

        // Draw all shapes here
        for (var s : this.shapeList) {
            s.paint(this);
        }
    }

    @Override
    public void notifyUpdatedShapeList(
            IPaintShape[] shapes) {
        // TODO Auto-generated method stub
        
    }
}
