package logic.commands;

import java.util.ArrayList;
import java.util.List;

import logic.observer.IPublisher;
import logic.observer.IStatefulListPublisher;
import view.gui.PaintShapeComposite;
import view.interfaces.IPaintShape;

public class UngroupShapeCompositeCommand implements ICommand, IUndoable {
    private List<IPaintShape> selectedShapes;
    private IStatefulListPublisher<IPaintShape> visibleShapesListPub;
    private IPublisher<List<IPaintShape>> selectedShapesListPub;
    private List<IPaintShape> freeShapes;

    public UngroupShapeCompositeCommand(List<IPaintShape> selectedShapes, 
        IStatefulListPublisher<IPaintShape> visibleShapesListPublisher, 
        IPublisher<List<IPaintShape>> selectedShapesListPublisher) {
            this.selectedShapes = selectedShapes;
            this.visibleShapesListPub = visibleShapesListPublisher;
            this.selectedShapesListPub = selectedShapesListPublisher;
            freeShapes = new ArrayList<IPaintShape>();
        }

    @Override
    public void undo() {
        for (IPaintShape shape : this.selectedShapes) {
            if (shape.isComposite()){
                List<IPaintShape> list = new ArrayList<IPaintShape>(
                    ((PaintShapeComposite) shape).getChildren());
                for (IPaintShape item : list) {
                    item.setSelected(false);
                }
            }
            else
                shape.setSelected(true);
        }
        selectedShapesListPub.announce(this.selectedShapes);
        visibleShapesListPub.removeCollection(this.freeShapes);
        visibleShapesListPub.addCollection(this.selectedShapes);
    }

    @Override
    public void redo() {
        this.ungroup();
    }

    @Override
    public void invoke() {
        this.ungroup();
        CommandHistory.add(this);
    }

    private void ungroup(){
        freeChildren();
        selectedShapesListPub.announce(freeShapes);
        visibleShapesListPub.addCollection(freeShapes);
    }
    
    private void freeChildren() {
        for (IPaintShape shape : this.selectedShapes) {
            if (shape.isComposite()){
                List<IPaintShape> list = new ArrayList<IPaintShape>(
                    ((PaintShapeComposite) shape).getChildren());
                for (IPaintShape item : list) {
                    item.setSelected(true);
                }
                visibleShapesListPub.remove(shape);
                freeShapes.addAll(list);
            } 
            else
                freeShapes.add(shape);           
        }
    }
}
