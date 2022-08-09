package logic.paintstrategy;

import java.awt.Point;

import view.interfaces.PaintCanvasBase;

public abstract class ProxyPaintStrategy implements IProxyPaintStrategy {

    private IPaintStrategy paintStrategy;
    private boolean selected;
    
    public ProxyPaintStrategy(IPaintStrategy paintStrategy, boolean selected) {
        this.paintStrategy = paintStrategy; 
    }

    @Override
    public void paint(PaintCanvasBase c, Point origin,
            Point endpoint) {        
        this.paintStrategy.paint(c, origin, endpoint);
    }
    @Override
    public void setSelected(boolean selected){
        this.selected = selected;
    }

    public IPaintStrategy getPaintStrategy(){
        return this.paintStrategy;
    }
    
}
