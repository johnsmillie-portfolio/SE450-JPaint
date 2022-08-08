package logic.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import logic.observer.IPublisher;
import logic.observer.IStatefulListPublisher;
import view.interfaces.IPaintShape;

public class PasteClipboardCommand implements ICommand, IUndoable {
    private List<IPaintShape> clipboard;
    private List<IPaintShape> pasteList;
    private IStatefulListPublisher<IPaintShape> visibleShapesListPub;
    private IPublisher<List<IPaintShape>> selectedShapesListPub;
    
    public PasteClipboardCommand(List<IPaintShape> clipboard,
            IStatefulListPublisher<IPaintShape> visibleShapesListPublisher,
            IPublisher<List<IPaintShape>> selectedShapesListPublisher) {
        this.clipboard = clipboard;
        this.visibleShapesListPub = visibleShapesListPublisher;
        this.selectedShapesListPub = selectedShapesListPublisher;
        this.pasteList = new ArrayList<IPaintShape>();
    }

    public void invoke() {
        
        this.newShapeClone();
        this.selectExchange();
        this.applyMove();
        this.visibleShapesListPub.addCollection(this.pasteList);
        this.selectedShapesListPub.announce(this.pasteList);
        CommandHistory.add(this);
    }

    public void newShapeClone() {
        for (IPaintShape paintShape : this.clipboard) {
            IPaintShape newPaintShape = paintShape.cloneShape();
            //paintShape.setSelected(false);
            //newPaintShape.setSelected(true);
            this.pasteList.add(newPaintShape);
        }
    }

    public void selectExchange (){
        for (IPaintShape paintShape : this.pasteList) {
            paintShape.setSelected(true);
            paintShape.setPaintStrategy(true);
            
        }
    }
    public void applyMove() {
        int m = ThreadLocalRandom.current().nextInt(-200, 200);
        int n = ThreadLocalRandom.current().nextInt(-200, 200);
        for (IPaintShape paintShape : this.pasteList) {
            paintShape.move(m, n);
        }
    }
    @Override
    public void redo() {
        // TODO Auto-generated method stub
        this.visibleShapesListPub.addCollection(this.pasteList);
    }

    @Override
    public void undo() {
        // TODO Auto-generated method stub
        this.visibleShapesListPub.removeCollection(this.pasteList);
        }
        
    }
    

