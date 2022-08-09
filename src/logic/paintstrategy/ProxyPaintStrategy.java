package logic.paintstrategy;

import java.awt.Point;

import view.interfaces.PaintCanvasBase;

public class ProxyPaintStrategy implements IPaintStrategy {

    private IPaintStrategy paintStrategy;
    private boolean selected;
    
    public ProxyPaintStrategy(IPaintStrategy paintStrategy) {
        this.paintStrategy = paintStrategy; 
    }

    @Override
    public void paint(PaintCanvasBase c, Point origin,
            Point endpoint) {
        // TODO Auto-generated method stub
        
        if (selected){
            new RectanglePaintProxy(paintStrategy, c, origin, endpoint);
        }
        paintStrategy.paint(c, origin, endpoint);
    }

    public void setSelected(boolean selected){
        this.selected = selected;
    }

    public IPaintStrategy getPaintStrategy(){
        return this.paintStrategy;
    }
    
}
