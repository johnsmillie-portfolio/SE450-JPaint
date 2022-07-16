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
        this.origin = new Point(this.origin.x + x,
                this.origin.y + y);

        this.endpoint = new Point(this.endpoint.x + x,
                this.endpoint.y + y);
    }
}
