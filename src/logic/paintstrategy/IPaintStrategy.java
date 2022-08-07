package logic.paintstrategy;
import java.awt.Point;

import view.interfaces.PaintCanvasBase;

public interface IPaintStrategy {
    void paint(PaintCanvasBase c, Point origin, Point endpoint);

    void setSelected(boolean isSelected);
}
