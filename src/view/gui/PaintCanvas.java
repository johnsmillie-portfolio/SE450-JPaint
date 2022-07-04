package view.gui;

import view.interfaces.IPaintShape;
import view.interfaces.PaintCanvasBase;

import javax.swing.JComponent;
import java.awt.*;
import java.util.Collection;

public class PaintCanvas extends PaintCanvasBase {
    private Collection<IPaintShape> shapeList;

    public PaintCanvas(Collection<IPaintShape> shapeList) {
        this.shapeList = shapeList;
    }

    public Graphics2D getGraphics2D() {
        return (Graphics2D) getGraphics();
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D graphics2d = (Graphics2D) g;
        graphics2d.setColor(Color.WHITE);
        graphics2d.fillRect(0, 0, 5000, 5000);

        // Draw all shapes here
        for (var s : this.shapeList) {
            s.paint(this);
        }
    }
}
