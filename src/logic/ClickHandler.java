package logic;

import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.event.MouseInputAdapter;
import logic.commands.AddShapeCommandBuilder;
import model.persistence.ApplicationState;
import view.gui.PaintCanvas;
import view.interfaces.IPaintShape;

public class ClickHandler extends MouseInputAdapter {
    PaintCanvas paintCanvas;
    ArrayList<IPaintShape> shapeList;
    ApplicationState applicationState;
    private AddShapeCommandBuilder addShapeCommandBuilder;

    public ClickHandler(PaintCanvas paintCanvas,
            ArrayList<IPaintShape> shapeList
    , ApplicationState applicationState
    ) {
        this.paintCanvas = paintCanvas;
        this.shapeList = shapeList;
        this.applicationState = applicationState;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("mouse pressed called");
        this.addShapeCommandBuilder = new AddShapeCommandBuilder(
                this.paintCanvas, this.shapeList, 
                applicationState.getActiveShapeType(),
                applicationState.getActivePrimaryColor(), 
                applicationState.getActiveSecondaryColor(), 
                applicationState.getActiveShapeShadingType());
        this.addShapeCommandBuilder.setOrigin(e.getPoint());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("mouse released called");

        // Set endpoint at position of mouse up
        this.addShapeCommandBuilder
                .setEndpoint(e.getPoint());

        // Finalize NewShapeCommand
        // Invoke command
        this.addShapeCommandBuilder.build().invoke();
    }
}
