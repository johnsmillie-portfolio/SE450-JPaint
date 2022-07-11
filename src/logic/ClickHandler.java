package logic;

import java.awt.event.MouseEvent;
import java.util.Arrays;

import javax.swing.event.MouseInputAdapter;
import logic.commands.AddShapeCommandBuilder;
import logic.shapelist.IShapeListPublisher;
import logic.shapelist.IShapeListSubscriber;
import model.MouseMode;
import model.persistence.ApplicationState;
import java.awt.Point;
import view.interfaces.IPaintShape;

public class ClickHandler extends MouseInputAdapter
        implements IShapeListSubscriber {
    private IShapeListPublisher visibleShapesListPublisher;
    private IShapeListPublisher selectedShapesListPublisher;
    private IPaintShape[] visibleShapes;
    private ApplicationState applicationState;
    private Point dragStartPos;

    public ClickHandler(
            IShapeListPublisher visibleShapesListPublisher,
            IShapeListPublisher selectedShapesListPublisher,
            ApplicationState applicationState) {
        this.visibleShapesListPublisher = visibleShapesListPublisher;
        this.selectedShapesListPublisher = selectedShapesListPublisher;
        this.visibleShapes = new IPaintShape[0];
        this.applicationState = applicationState;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("mouse pressed called");
        this.dragStartPos = e.getPoint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("mouse released called");

        var dragMouseMode = this.applicationState
                .getActiveMouseMode();

        // TODO: Implement move drag strategy
        // TODO: break out into strategy classes
        if (dragMouseMode == MouseMode.DRAW) {
            var addShapeCommandBuilder = new AddShapeCommandBuilder(
                    this.visibleShapesListPublisher);
            addShapeCommandBuilder
                    .setOrigin(this.dragStartPos);

            addShapeCommandBuilder
                    .setEndpoint(e.getPoint());

            addShapeCommandBuilder.build().invoke();
        }
        else if (dragMouseMode == MouseMode.SELECT) {
            IPaintShape[] selectedShapes = Arrays
                    .stream(this.visibleShapes)
                    .filter(s -> s.collides(
                            this.dragStartPos,
                            e.getPoint()))
                    .toArray(IPaintShape[]::new);

            this.selectedShapesListPublisher
                    .updateShapeList(selectedShapes);
        }
        else {
            throw new Error(
                    "Not sure how to handle mouse mode "
                            + dragMouseMode);
        }
    }

    @Override
    public void notifyUpdatedShapeList(
            IPaintShape[] shapes) {
        this.visibleShapes = shapes;
    }
}
