package logic.stategies;
import java.awt.Point;

import view.interfaces.PaintCanvasBase;

public interface IPaintStrategy {
    void paint(PaintCanvasBase c, Point origin, Point endpoint);
}
