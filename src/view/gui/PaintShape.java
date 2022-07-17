package view.gui;
import logic.stategies.IPaintStrategy;
import view.interfaces.IPaintShape;
import view.interfaces.PaintCanvasBase;
import java.awt.Point;

public class PaintShape implements IPaintShape {
    private IPaintStrategy paintStrategy;
    private Point origin;
    private Point endpoint;

    public PaintShape (Point origin, Point endpoint,IPaintStrategy paintStrategy) {
        this.paintStrategy = paintStrategy;
    }

    @Override
    public void paint(PaintCanvasBase c) {
            paintStrategy.paint(c);
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
