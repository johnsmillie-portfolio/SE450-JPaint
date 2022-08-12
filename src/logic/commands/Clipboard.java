package logic.commands;

import java.util.ArrayList;
import java.util.List;
import logic.observer.IPublisher;
import logic.observer.IStatefulListPublisher;
import view.gui.PaintShapeComposite;
import view.interfaces.IPaintShape;

public class Clipboard {
    public static List<IPaintShape> selectedShapes;
    public static List<IPaintShape> visibleShapes;
    public static List<IPaintShape> clipboard;
    public static IStatefulListPublisher<IPaintShape> visibleShapesListPub;
    public static IPublisher<List<IPaintShape>> selectedShapesListPub;

    public static void setup (
            IPublisher<List<IPaintShape>> selectedShapesListPublisher,
            IStatefulListPublisher<IPaintShape> visibleShapesListPublisher) {
        selectedShapesListPublisher.subscribe((v) -> selectedShapes = v);
        visibleShapesListPublisher.subscribe((v) -> visibleShapes = v);
        selectedShapesListPub = selectedShapesListPublisher;
        visibleShapesListPub = visibleShapesListPublisher;
    }
    
    public static void copyToClipboard(){
        if (selectedShapes != null) {
            clipboard = new ArrayList<IPaintShape>(selectedShapes);
        }
    }

    public static void paste () {
        copyToClipboard();
        if (clipboard != null) {
            for (IPaintShape paintShape : clipboard) {
                paintShape.setSelected(false);            
            }
        
            (new PasteClipboardCommand(clipboard, visibleShapesListPub, 
            selectedShapesListPub)).invoke();
        }
    }

    public static void delete () {
        (new DeleteShapesCommand(selectedShapes, visibleShapes,
         visibleShapesListPub, selectedShapesListPub)).invoke();
    }
    
    public static void group () {
        if (selectedShapes != null || selectedShapes.size() > 0)
            new PaintShapeComposite(selectedShapes);
    }

    public static void ungroup () {

    }
}
