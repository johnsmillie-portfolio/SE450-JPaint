package logic;

import java.util.ArrayList;
import java.util.List;

import logic.commands.AddShapeCompositeCommand;
import logic.commands.DeleteShapesCommand;
import logic.commands.PasteClipboardCommand;
import logic.commands.UngroupShapeCompositeCommand;
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
        if (clipboard != null) {
            for (IPaintShape paintShape : visibleShapes) {
                paintShape.setSelected(false);            
            }
        
            (new PasteClipboardCommand(clipboard, visibleShapesListPub, 
                selectedShapesListPub)).invoke();
        }
    }

    public static void delete () {
        if (selectedShapes != null)
            (new DeleteShapesCommand(selectedShapes, visibleShapes,
                visibleShapesListPub, selectedShapesListPub)).invoke();
    }
    
    public static void group () {
        if (selectedShapes.size() > 1 )
            new AddShapeCompositeCommand(new PaintShapeComposite(selectedShapes),
                visibleShapesListPub, selectedShapesListPub).invoke();;
    }

    public static void ungroup () {
        boolean hasComposite = false;
        if (selectedShapes != null) {
            for (IPaintShape shape : selectedShapes) {
                if (shape.isComposite()){
                    hasComposite = true;
                    break;
                }
            }
        }
        if (hasComposite)
            new UngroupShapeCompositeCommand(selectedShapes, visibleShapesListPub, 
                selectedShapesListPub).invoke(); 

            
    

    }
}
