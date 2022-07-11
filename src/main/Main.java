package main;

import controller.IJPaintController;
import controller.JPaintController;
import logic.ClickHandler;
import model.persistence.ApplicationState;
import view.gui.Gui;
import view.gui.GuiWindow;
import view.gui.PaintCanvas;
import view.interfaces.IGuiWindow;
import view.interfaces.IPaintShape;
import view.interfaces.PaintCanvasBase;
import view.interfaces.IUiModule;

import java.awt.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<IPaintShape> shapeList = new ArrayList<IPaintShape>();
        PaintCanvasBase paintCanvas = new PaintCanvas(
                shapeList);

          //TODO      
        //application state in constructor
        
        IGuiWindow guiWindow = new GuiWindow(paintCanvas);
        IUiModule uiModule = new Gui(guiWindow);
        ApplicationState appState = new ApplicationState(
                uiModule);
        IJPaintController controller = new JPaintController(
                uiModule, appState);
        controller.setup();
        ClickHandler handler = new ClickHandler(
                (PaintCanvas) paintCanvas, shapeList, appState);
        paintCanvas.addMouseListener(handler);
        // ClickHandler handler = new ClickHandler();
        // For example purposes only; remove all lines below from your final project.

        try {
            Thread.sleep(500);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
