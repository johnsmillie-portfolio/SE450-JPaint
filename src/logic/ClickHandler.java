package logic;

import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.event.MouseInputAdapter;

import logic.dragstrategy.AddNewShapeDragStrategy;
import logic.dragstrategy.IDragStrategy;
import logic.dragstrategy.MoveDragStrategy;
import logic.dragstrategy.SelectDragStrategy;
import logic.observer.IPublisher;
import logic.observer.IStatefulListPublisher;
import logic.observer.StatefulListPublisher;
import model.persistence.ApplicationState;
import java.awt.Point;
import view.interfaces.IPaintShape;

public class ClickHandler extends MouseInputAdapter {
    private IStatefulListPublisher<IPaintShape> visibleShapesListPublisher;
    private IPublisher<List<IPaintShape>> selectedShapesListPublisher;
    private ApplicationState applicationState;
    private IDragStrategy dragStrategy;
    private List<IPaintShape> visibleShapes;
    private List<IPaintShape> selectedShapes;

    public ClickHandler(
            IPublisher<List<IPaintShape>> visibleShapesListPublisher,
            IPublisher<List<IPaintShape>> selectedShapesListPublisher,
            ApplicationState applicationState) {
        visibleShapesListPublisher
                .subscribe((v) -> this.visibleShapes = v);
        this.visibleShapesListPublisher = new StatefulListPublisher<IPaintShape>(
                visibleShapesListPublisher);
        this.selectedShapesListPublisher = selectedShapesListPublisher;
        selectedShapesListPublisher
                .subscribe((v) -> this.selectedShapes = v);
        this.applicationState = applicationState;
        Clipboard.setup(this.selectedShapesListPublisher, this.visibleShapesListPublisher);
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        this.dragStrategy = this
                .getDragStrategy(e.getPoint());
    }

    private IDragStrategy getDragStrategy(
            Point dragStartPos) {
        var dragMouseMode = this.applicationState
                .getActiveMouseMode();

        switch (dragMouseMode) {
        case DRAW:
            return new AddNewShapeDragStrategy(dragStartPos,
                    this.visibleShapesListPublisher,
                    this.applicationState
                            .getActiveShapeType(),
                    this.applicationState
                            .getActivePrimaryColor(),
                    this.applicationState
                            .getActiveSecondaryColor(),
                    this.applicationState
                            .getActiveShapeShadingType());
        case SELECT:
            return new SelectDragStrategy(dragStartPos,
                    visibleShapes,
                    this.selectedShapesListPublisher, 
                    this.visibleShapesListPublisher);
        case MOVE:
            return new MoveDragStrategy(dragStartPos,
                    selectedShapes,
                    this.visibleShapesListPublisher);
        default:
            throw new Error(
                    "Not sure how to handle mouse mode "
                            + dragMouseMode);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        this.dragStrategy.endDrag(e.getPoint());
    }
}
