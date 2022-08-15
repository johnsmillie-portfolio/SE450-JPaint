package logic.commands;

import java.util.ArrayList;
import java.util.List;

import logic.observer.IPublisher;
import logic.observer.IStatefulListPublisher;
import view.gui.PaintShapeComposite;
import view.interfaces.IPaintShape;

public class AddPaintShapeCompositeCommand implements ICommand, IUndoable {
    private IPaintShape paintShapeComposite; 
    private IStatefulListPublisher<IPaintShape> visibleShapesListPub;
    private IPublisher<List<IPaintShape>> selectedShapesListPub;
    
    public AddPaintShapeCompositeCommand(PaintShapeComposite paintShapeComposite, 
        IStatefulListPublisher<IPaintShape> visibleShapesListPublisher, 
        IPublisher<List<IPaintShape>> selectedShapesListPublisher) {
            this.paintShapeComposite = paintShapeComposite;
            this.visibleShapesListPub = visibleShapesListPublisher;
            this.selectedShapesListPub = selectedShapesListPublisher;

        }

    @Override
    public void undo() {
        List<IPaintShape> announceNeedsArray = new ArrayList<IPaintShape>();
        announceNeedsArray.addAll(((PaintShapeComposite) this.paintShapeComposite).getChildren());
        for (IPaintShape shape : announceNeedsArray) {
            shape.setSelected(true);
        }
        this.selectedShapesListPub.announce(announceNeedsArray);
        visibleShapesListPub.remove(this.paintShapeComposite);
        visibleShapesListPub.addCollection(announceNeedsArray);
        
    }

    @Override
    public void redo() {
        this.addShape();
        
    }

    @Override
    public void invoke() {

        this.addShape();
        CommandHistory.add(this);
    }

    private void addShape() {
        List<IPaintShape> announceNeedsArray = new ArrayList<IPaintShape>();
        announceNeedsArray.add(paintShapeComposite);
        this.selectedShapesListPub.announce(announceNeedsArray);

        visibleShapesListPub.removeCollection(((PaintShapeComposite) this.paintShapeComposite).getChildren());
        visibleShapesListPub.add(paintShapeComposite);
    }
    
    
    
}
