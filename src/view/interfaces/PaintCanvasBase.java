package view.interfaces;

import javax.swing.*;

import logic.shapelist.IShapeListSubscriber;

import java.awt.*;

public abstract class PaintCanvasBase extends JComponent implements IShapeListSubscriber {
    public abstract Graphics2D getGraphics2D();
}
