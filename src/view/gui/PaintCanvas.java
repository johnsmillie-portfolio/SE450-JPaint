package view.gui;

import view.interfaces.IPaintShape;
import view.interfaces.PaintCanvasBase;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import logic.observer.IPublisher;
import logic.observer.ISubscriber;

public class PaintCanvas extends PaintCanvasBase
        implements ISubscriber<List<IPaintShape>> {
    private List<IPaintShape> visibleShapes;

    public PaintCanvas(
            IPublisher<List<IPaintShape>> visibleShapesListPublisher) {
        this.visibleShapes = new ArrayList<>();
        visibleShapesListPublisher.subscribe(this);
    }

    public Graphics2D getGraphics2D() {
        return (Graphics2D) getGraphics();
    }

    @Override
    public void repaint() {
        this.paint(this.getGraphics2D());
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
    public void notifyUpdate(List<IPaintShape> shapes) {
        this.visibleShapes = shapes;
        this.repaint();
    }
}
