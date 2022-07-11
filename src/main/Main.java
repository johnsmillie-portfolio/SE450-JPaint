package main;

import controller.IJPaintController;
import controller.JPaintController;
import logic.ClickHandler;
import logic.shapelist.ShapeListPublisher;
import model.persistence.ApplicationState;
import view.gui.Gui;
import view.gui.GuiWindow;
import view.gui.PaintCanvas;
import view.interfaces.IGuiWindow;
import view.interfaces.PaintCanvasBase;
import view.interfaces.IUiModule;

public class Main {
    public static void main(String[] args) {
        ShapeListPublisher visibleShapesListPublisher = new ShapeListPublisher();
        ShapeListPublisher selectedShapesListPublisher = new ShapeListPublisher();

        PaintCanvasBase paintCanvas = new PaintCanvas();
        visibleShapesListPublisher.subscribe(paintCanvas);

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
