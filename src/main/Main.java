package main;

import java.util.List;

import controller.IJPaintController;
import controller.JPaintController;
import logic.ClickHandler;
import logic.Clipboard;
import logic.observer.SimplePublisher;
import model.persistence.ApplicationState;
import view.gui.Gui;
import view.gui.GuiWindow;
import view.gui.PaintCanvas;
import view.interfaces.IGuiWindow;
import view.interfaces.IPaintShape;
import view.interfaces.PaintCanvasBase;
import view.interfaces.IUiModule;

public class Main {
    public static void main(String[] args) {
        var visibleShapesListPublisher = new SimplePublisher<List<IPaintShape>>();
        var selectedShapesListPublisher = new SimplePublisher<List<IPaintShape>>();

        PaintCanvasBase paintCanvas = new PaintCanvas(
                visibleShapesListPublisher);

        IGuiWindow guiWindow = new GuiWindow(paintCanvas);
        IUiModule uiModule = new Gui(guiWindow);

        ApplicationState appState = new ApplicationState(
                uiModule);

        ClickHandler handler = new ClickHandler(
                visibleShapesListPublisher,
                selectedShapesListPublisher, appState);

        paintCanvas.addMouseListener(handler);
        
        IJPaintController controller = new JPaintController(
                uiModule, appState);
       
        
        controller.setup();
        
        // For example purposes only; remove all lines below from your final project.

        try {
            Thread.sleep(500);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
