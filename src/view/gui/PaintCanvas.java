package view.gui;

import view.interfaces.IPaintShape;
import view.interfaces.PaintCanvasBase;
import java.awt.*;

public class PaintCanvas extends PaintCanvasBase {
    private IPaintShape[] visibleShapes;

    public PaintCanvas() {
        this.visibleShapes = new IPaintShape[0];
    }

    public Graphics2D getGraphics2D() {
        return (Graphics2D) getGraphics();
    }

    @Override
    public void paint(Graphics g) {
        this.clearAndRedraw(g);
    }

    private void clearAndRedraw(Graphics g) {
        this.clear(g);
        this.drawAll();
    }

    private void drawAll() {
        // Draw all shapes here
        for (var s : this.visibleShapes) {
            s.paint(this);
        }
    }

    private void clear(Graphics g) {
        Graphics2D graphics2d = (Graphics2D) g;
        graphics2d.setColor(Color.WHITE);
        graphics2d.fillRect(0, 0, this.getSize().width,
                this.getSize().height);
    }

    @Override
    public void notifyUpdatedShapeList(
            IPaintShape[] shapes) {
        this.visibleShapes = shapes;
        this.paint(this.getGraphics());
    }
}
