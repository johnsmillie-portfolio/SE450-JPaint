package logic.paintstrategy;

import java.awt.Point;


import view.interfaces.PaintCanvasBase;

public abstract class ProxyPaintStrategy implements IProxyPaintStrategy {
    private IPaintStrategy paintStrategy;
    private boolean selected;
    
    public ProxyPaintStrategy(IPaintStrategy paintStrategy, boolean selected) {
        this.paintStrategy = paintStrategy; 
        this.selected = selected;
    }

    @Override
    public void paint (PaintCanvasBase c, Point origin, Point endpoint){
        if (selected){
            dashedOutlinePaint(c, origin, endpoint);
        }
        paintStrategy.paint(c, origin, endpoint);
    }
    @Override
    public void setSelected (boolean selected){
        this.selected = selected;
    }

    public abstract void dashedOutlinePaint(PaintCanvasBase c, 
            Point origin, Point endpoint);
    
    public IPaintStrategy getPaintStrategy() {
        return this.paintStrategy.getPaintStrategy();
    }

    
    
}
