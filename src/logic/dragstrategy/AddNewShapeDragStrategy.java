package logic.dragstrategy;

import java.awt.Point;

import logic.commands.AddShapeCommandBuilder;
import logic.observer.IStatefulListPublisher;
import view.interfaces.IPaintShape;

public class AddNewShapeDragStrategy
        extends StatefulDragStrategy {
    private IStatefulListPublisher<IPaintShape> visibleShapesListPublisher;

    public AddNewShapeDragStrategy(Point startPoint,
            IStatefulListPublisher<IPaintShape> visibleShapesListPublisher) {
        super(startPoint);
        this.visibleShapesListPublisher = visibleShapesListPublisher;
    }

    @Override
    public void endDrag(Point p) {
        var addShapeCommandBuilder = new AddShapeCommandBuilder(
                this.visibleShapesListPublisher);
        addShapeCommandBuilder.setOrigin(this.startPoint);

        addShapeCommandBuilder.setEndpoint(p);

        addShapeCommandBuilder.build().invoke();
    }

}
