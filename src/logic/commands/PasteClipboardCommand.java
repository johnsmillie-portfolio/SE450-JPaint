package logic.commands;

import java.util.List;

import logic.observer.IPublisher;
import logic.observer.IStatefulListPublisher;
import view.interfaces.IPaintShape;

public class PasteClipboardCommand implements ICommand, IUndoable {
    private List<IPaintShape> clipboard;
    private IStatefulListPublisher<IPaintShape> visibleShapesListPub;
    
    public PasteClipboardCommand(List<IPaintShape> clipboard,
            IStatefulListPublisher<IPaintShape> visibleShapesListPub) {
        this.clipboard = clipboard;
        this.visibleShapesListPub = visibleShapesListPub;

    }

    public void invoke() {
        this.applyMove();
        this.visibleShapesListPub.addCollection(this.clipboard);
        CommandHistory.add(this);
    }

    public void applyMove() {
        for (IPaintShape paintShape : clipboard) {
            paintShape.move(50,50);
        }

    }

    @Override
    public void redo() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void undo() {
        // TODO Auto-generated method stub
        
    }
    
}
