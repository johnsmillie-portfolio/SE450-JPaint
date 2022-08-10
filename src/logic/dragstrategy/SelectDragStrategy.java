package logic.dragstrategy;

import java.awt.Point;
import java.util.List;

import logic.commands.SelectCommand;
import logic.observer.IPublisher;
import logic.observer.IStatefulListPublisher;
import view.interfaces.IPaintShape;

public class SelectDragStrategy
        extends StatefulDragStrategy {

    private List<IPaintShape> visibleShapes;
    private IPublisher<List<IPaintShape>> selectedShapesPublisher;
    private IStatefulListPublisher<IPaintShape> visibleShapesPublisher;

    public SelectDragStrategy(Point startPoint,
            List<IPaintShape> visibleShapes,
            IPublisher<List<IPaintShape>> selectedShapesPublisher,
            IStatefulListPublisher<IPaintShape> visibleShapesPublisher) {
        super(startPoint);
        this.visibleShapes = visibleShapes;
        this.selectedShapesPublisher = selectedShapesPublisher;
        this.visibleShapesPublisher = visibleShapesPublisher;
        
    }

    @Override
    public void endDrag(Point p) {
        (new SelectCommand(this.startPoint, p,
                this.selectedShapesPublisher, this.visibleShapesPublisher,
                this.visibleShapes)).invoke();
    }
}
