package logic.commands;

import view.interfaces.IPaintShape;
import java.awt.Point;
import java.util.List;

import logic.observer.IPublisher;
import logic.observer.IStatefulListPublisher;

public class SelectCommand implements ICommand {

    private Point origin;
    private Point endpoint;
    private List<IPaintShape> visibleShapes;
    private IPublisher<List<IPaintShape>> selectedShapesPublisher;
    private IStatefulListPublisher<IPaintShape> visibleShapesPublisher;

    public SelectCommand(Point origin, Point endpoint,
            IPublisher<List<IPaintShape>> selectedShapesPublisher,
            IStatefulListPublisher<IPaintShape> visibleShapesPublisher,
            List<IPaintShape> visibleShapes) {
        this.origin = origin;
        this.endpoint = endpoint;
        this.visibleShapes = visibleShapes;
        this.selectedShapesPublisher = selectedShapesPublisher;
        this.visibleShapesPublisher = visibleShapesPublisher;
    }

    @Override
    public void invoke() {
        if (this.visibleShapes != null) {
            List<IPaintShape> selectedShapes = this.visibleShapes
                    .stream()
                    .filter(s -> s.collides(this.origin,
                            this.endpoint))
                    .toList();
            for (IPaintShape paintShape : visibleShapes) {
                paintShape.setSelected(false);
            }
            for (IPaintShape paintShape : selectedShapes) {
                paintShape.setSelected(true);
            }
            this.selectedShapesPublisher
                    .announce(selectedShapes);
            this.visibleShapesPublisher.announce(visibleShapes);
        }
    }
}
