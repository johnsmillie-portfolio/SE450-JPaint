package logic.commands;

import java.util.ArrayList;
import java.util.List;


import logic.observer.IPublisher;
import logic.observer.IRetriggerable;
import logic.observer.IStatefulListPublisher;
import logic.observer.StatefulListPublisher;
import view.interfaces.IPaintShape;

public class Clipboard {
    public static List<IPaintShape> selectedShapes;
    public static List<IPaintShape> clipboard;
    public static IStatefulListPublisher<IPaintShape> clipboardPublisher;

    public static void getSelectedShapesListPublisher (
            IPublisher<List<IPaintShape>> selectedShapesListPublisher) {
        selectedShapesListPublisher.subscribe((v) -> selectedShapes = v);
        clipboardPublisher =  new StatefulListPublisher<>(selectedShapesListPublisher);
    }
    
    public static void copyToClipboard(){
        //TODO Implement
        clipboard = new ArrayList<IPaintShape>(); 
        for (IPaintShape paintShape : selectedShapes) {
            clipboard.add(paintShape);
            System.out.println("paint shape copied");
        }
    }

     public static void paste () {
        //TODO
        for (IPaintShape paintShape : clipboard) {
            relocate(paintShape);
            System.out.println("this is an object");
        }
        clipboardPublisher.retrigger(); 
        
     }

     public static void relocate (IPaintShape shape) {
        shape.move(0, 0);
     }
}
