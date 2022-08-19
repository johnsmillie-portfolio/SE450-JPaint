package logic.commands;

import java.util.ArrayList;
import java.util.List;

import logic.observer.IPublisher;
import logic.observer.IStatefulListPublisher;
import view.gui.PaintShapeComposite;
import view.interfaces.IPaintShape;

public class AddShapeCompositeCommand implements ICommand, IUndoable {
    private IPaintShape paintShapeComposite; 
    private IStatefulListPublisher<IPaintShape> visibleShapesListPub;
    private IPublisher<List<IPaintShape>> selectedShapesListPub;
    List<IPaintShape> children;
    
    public AddShapeCompositeCommand(PaintShapeComposite paintShapeComposite, 
        IStatefulListPublisher<IPaintShape> visibleShapesListPublisher, 
        IPublisher<List<IPaintShape>> selectedShapesListPublisher) {
            this.paintShapeComposite = paintShapeComposite;
            this.visibleShapesListPub = visibleShapesListPublisher;
            this.selectedShapesListPub = selectedShapesListPublisher;
            this.children = new ArrayList<IPaintShape>(
                ((PaintShapeComposite) this.paintShapeComposite).getChildren());
        }

    @Override
    public void undo() {
       
        for (IPaintShape shape : children) {
            shape.setSelected(true);
        }
        this.selectedShapesListPub.announce(children);
        visibleShapesListPub.remove(this.paintShapeComposite);
        visibleShapesListPub.addCollection(children);
        
    }

    @Override
    public void redo() {
        for (IPaintShape shape : children) {
            shape.setSelected(false);
        }
        this.addShape();
        
    }

    @Override
    public void invoke() {

        this.addShape();
        CommandHistory.add(this);
    }

    private void addShape() {
        for (IPaintShape shape : children) {
            shape.setSelected(false);
        }
        List<IPaintShape> announceArray = new ArrayList<IPaintShape>();
        announceArray.add(this.paintShapeComposite);
        this.selectedShapesListPub.announce(announceArray);

        visibleShapesListPub.removeCollection(children);
        visibleShapesListPub.add(paintShapeComposite);
    }
    
    
    
}
