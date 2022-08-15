package logic.commands;

import java.util.ArrayList;
import java.util.List;

import logic.observer.IPublisher;
import logic.observer.IStatefulListPublisher;
import view.gui.PaintShapeComposite;
import view.interfaces.IPaintShape;

public class UngroupPaintShapeCompositeCommand implements ICommand, IUndoable {
    private List<IPaintShape> selectedShapes;
    private IStatefulListPublisher<IPaintShape> visibleShapesListPub;
    private IPublisher<List<IPaintShape>> selectedShapesListPub;

    public UngroupPaintShapeCompositeCommand(List<IPaintShape> selectedShapes, 
        IStatefulListPublisher<IPaintShape> visibleShapesListPublisher, 
        IPublisher<List<IPaintShape>> selectedShapesListPublisher) {
            this.selectedShapes = selectedShapes;
            this.visibleShapesListPub = visibleShapesListPublisher;
            this.selectedShapesListPub = selectedShapesListPublisher;
        }

    @Override
    public void undo() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void redo() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void invoke() {
        List<IPaintShape> freeShapes = freeChildren();
        selectedShapesListPub.announce(freeShapes);
        visibleShapesListPub.addCollection(freeShapes);
        CommandHistory.add(this);
    }

    
    private List<IPaintShape> freeChildren() {
        List<IPaintShape> freeShapes = new ArrayList<IPaintShape>();
        for (IPaintShape shape : this.selectedShapes) {
            if (shape.isComposite()){
                List<IPaintShape> list = new ArrayList<IPaintShape>();
                list.addAll(((PaintShapeComposite) shape).getChildren());
                for (IPaintShape item : list) {
                    if(item.isComposite())
                        ((PaintShapeComposite) item).setThisSelected(true);
                    else
                        item.setSelected(true);
                }
                visibleShapesListPub.remove(shape);
                freeShapes.addAll(list);
            } 
            else
                freeShapes.add(shape);           
        }
        return freeShapes;
    }
}
