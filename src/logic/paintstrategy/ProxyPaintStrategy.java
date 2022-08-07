package logic.paintstrategy;

import java.awt.Point;

import view.interfaces.PaintCanvasBase;

public class ProxyPaintStrategy implements IPaintStrategy {

    private IPaintStrategy paintStrategy;
    private boolean isSelected;
    
    public ProxyPaintStrategy(IPaintStrategy paintStrategy) {
        this.paintStrategy = paintStrategy; 
    }

    @Override
    public void paint(PaintCanvasBase c, Point origin,
            Point endpoint) {
        // TODO Auto-generated method stub
        
        if (isSelected){
            new RectanglePaintProxy(paintStrategy, c, origin, endpoint);
        }
        paintStrategy.paint(c, origin, endpoint);
    }

    public void setSelected(boolean isSelected){
        this.isSelected = isSelected;
    }
    
}
