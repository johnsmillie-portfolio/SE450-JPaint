package logic.commands;

import java.util.ArrayList;
import java.util.List;

import logic.observer.IPublisher;
import logic.observer.IStatefulListPublisher;
import view.interfaces.IPaintShape;

public class DeleteShapesCommand implements ICommand, IUndoable {
    public IStatefulListPublisher<IPaintShape> visibleShapesListPub;
    public IPublisher<List<IPaintShape>> selectedShapesListPub;
    public List<IPaintShape> visibleShapes;
    public List<IPaintShape> selectedShapes;
    
    
    public DeleteShapesCommand(List<IPaintShape> selectedShapes,
            List<IPaintShape> visibleShapes, IStatefulListPublisher<IPaintShape> visibleShapesListPublisher,
            IPublisher<List<IPaintShape>> selectedShapesListPublisher) {
        this.visibleShapes = visibleShapes;
        this.selectedShapes = selectedShapes;
        this.visibleShapesListPub = visibleShapesListPublisher;
        this.selectedShapesListPub = selectedShapesListPublisher;
    
    }


    @Override
    public void invoke() {
       this.deleteSelectedShapes();
       this.selectedShapesListPub.announce(new ArrayList<IPaintShape>());
       CommandHistory.add(this);
    }
    public void deleteSelectedShapes() {
        for (IPaintShape paintShape : this.selectedShapes) {
            this.visibleShapesListPub.remove(paintShape);
            
            
        }
    }

    @Override
    public void undo() {
        visibleShapesListPub.announce(this.visibleShapes);
       selectedShapesListPub.announce(this.selectedShapes);
    }


    @Override
    public void redo() {
        this.invoke();
    }
}