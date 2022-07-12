package view.gui;

import java.awt.Point;
import view.interfaces.IPaintShape;
import view.interfaces.PaintCanvasBase;
import java.awt.Color;
import java.awt.Graphics2D;

public class PaintShape implements IPaintShape {
    private Point origin;
    private Point endpoint;

    public PaintShape(Point origin, Point endpoint) {
        this.origin = origin;
        this.endpoint = endpoint;
    }

    @Override
    public void paint(PaintCanvasBase c) {
        int x = Math.min(this.origin.x, this.endpoint.x);
        int y = Math.min(this.origin.y, this.endpoint.y);
        int height = Math.max(this.origin.y,
                this.endpoint.y) - y;
        int width = Math.max(this.origin.x, this.endpoint.x)
                - x;

        // Filled in rectangle
        Graphics2D graphics2d = c.getGraphics2D();
        graphics2d.setColor(Color.GREEN);
        graphics2d.fillRect(x, y, width, height);
    }

    @Override
    public boolean collides(Point from, Point to) {
        // TODO Implement actual Collision Logic
        return true;
    }

    @Override
    public void move(int x, int y) {
        this.origin = new Point(this.origin.x + x,
                this.origin.y + y);

        this.endpoint = new Point(this.endpoint.x + x,
                this.endpoint.y + y);
    }
}
