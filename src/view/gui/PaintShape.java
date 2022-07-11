package view.gui;


import logic.stategies.IPaintStrategy;
import view.interfaces.IPaintShape;
import view.interfaces.PaintCanvasBase;


public class PaintShape implements IPaintShape {
    
   private IPaintStrategy paintStrategy;

    public PaintShape (IPaintStrategy paintStrategy) {

        this.paintStrategy = paintStrategy;
    }

    @Override
    public void paint(PaintCanvasBase c) {
            // TODO
            //call paint on strategy
            paintStrategy.paint(c);
       
    }
    
    



}
