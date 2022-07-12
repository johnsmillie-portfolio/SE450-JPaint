package logic.dragstrategy;

import java.awt.Point;
import java.util.List;
import logic.observer.IPublisher;
import view.interfaces.IPaintShape;

public class SelectDragStrategy
        extends StatefulDragStrategy {

    private List<IPaintShape> visibleShapes;
    private IPublisher<List<IPaintShape>> selectedShapesPublisher;

    public SelectDragStrategy(Point startPoint,
            List<IPaintShape> visibleShapes,
            IPublisher<List<IPaintShape>> selectedShapesPublisher) {
        super(startPoint);
        this.visibleShapes = visibleShapes;
        this.selectedShapesPublisher = selectedShapesPublisher;
    }

    @Override
    public void endDrag(Point p) {
        if (this.visibleShapes != null) {
            List<IPaintShape> selectedShapes = this.visibleShapes
                    .stream().filter(s -> s
                            .collides(this.startPoint, p))
                    .toList();

            this.selectedShapesPublisher
                    .announce(selectedShapes);
        }
    }
}
