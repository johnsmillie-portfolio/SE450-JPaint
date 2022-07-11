package view.gui;

import java.awt.Point;

import logic.stategies.EllipsePaintStrategy;
import logic.stategies.IPaintStrategy;
import logic.stategies.RectanglePaintStrategy;
import logic.stategies.TrianglePaintStrategy;
import model.ShapeType;
import view.interfaces.IPaintShape;
import view.interfaces.PaintCanvasBase;

import java.awt.Color;
import java.awt.Graphics2D;

public class PaintShape implements IPaintShape {
    // builder object? 
    private Point origin;
    private Point endpoint;
    private ShapeType shapeType;
    private IPaintStrategy paintStrategy;

    public PaintShape (IPaintStrategy paintStrategy) {
        //this.origin = origin;
        //this.endpoint = endpoint;
        //this.shapeType = shapeType;
        this.paintStrategy = paintStrategy;
    }

    @Override
    public void paint(PaintCanvasBase c) {

       
    }
    public IPaintStrategy createTriangle () {
        return new TrianglePaintStrategy ();
    }

    public IPaintStrategy createRectangle () {
        return new RectanglePaintStrategy();
    }



}
