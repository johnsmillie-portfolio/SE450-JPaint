package logic.commands;

import java.util.ArrayList;
import java.util.List;


import logic.observer.IPublisher;
import logic.observer.IStatefulListPublisher;
import view.interfaces.IPaintShape;

public class Clipboard {
    public static List<IPaintShape> selectedShapes;
    public static List<IPaintShape> clipboard;
    public static IStatefulListPublisher<IPaintShape> visibleShapesListPub;

    public static void setup (
            IPublisher<List<IPaintShape>> selectedShapesListPublisher,
            IStatefulListPublisher<IPaintShape> visibleShapesListPublisher) {
        selectedShapesListPublisher.subscribe((v) -> selectedShapes = v);
        visibleShapesListPub = visibleShapesListPublisher;
    }
    
    public static void copyToClipboard(){
        //TODO Refactor
        clipboard = new ArrayList<IPaintShape>(); 
        for (IPaintShape paintShape : selectedShapes) {
            IPaintShape newPaintShape = paintShape.cloneShape();
            clipboard.add(newPaintShape); 
            System.out.println("paint shape copied");
        }
    }

     public static void paste () {
        (new PasteClipboardCommand(clipboard, visibleShapesListPub)).invoke();
       
    }

    
}
