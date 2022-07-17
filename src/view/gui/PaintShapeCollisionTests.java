package view.gui;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.Point;

import org.junit.Test;

import view.interfaces.IPaintShape;

public class PaintShapeCollisionTests {

    public void validateTrueSelectionDragPosAndNeg(
            IPaintShape s, Point p1, Point p2) {
        assertTrue(s.collides(p1, p2));
        assertTrue(s.collides(p2, p1));
    }

    public void validateFalseSelectionDragPosAndNeg(
            IPaintShape s, Point p1, Point p2) {
        assertFalse(s.collides(p1, p2));
        assertFalse(s.collides(p2, p1));
    }

    @Test
    public void testPosShape() {
        var s = new PaintShape(new Point(10, 10),
                new Point(12, 12), null);

        // Surrounded
        validateTrueSelectionDragPosAndNeg(s,
                new Point(9, 9), new Point(13, 13));

        // On BR Corner
        validateTrueSelectionDragPosAndNeg(s,
                new Point(9, 9), new Point(11, 11));

        // On BL Corner
        validateTrueSelectionDragPosAndNeg(s,
                new Point(11, 9), new Point(13, 11));

        // On TR Corner
        validateTrueSelectionDragPosAndNeg(s,
                new Point(9, 11), new Point(11, 13));

        // On TL Corner
        validateTrueSelectionDragPosAndNeg(s,
                new Point(11, 11), new Point(13, 13));

        // On B Edge
        validateTrueSelectionDragPosAndNeg(s,
                new Point(9, 9), new Point(13, 11));

        // On T Edge
        validateTrueSelectionDragPosAndNeg(s,
                new Point(9, 11), new Point(13, 13));

        // On R Edge
        validateTrueSelectionDragPosAndNeg(s,
                new Point(9, 9), new Point(11, 13));

        // On L Edge
        validateTrueSelectionDragPosAndNeg(s,
                new Point(11, 9), new Point(13, 13));

        // To L
        validateFalseSelectionDragPosAndNeg(s,
                new Point(13, 9), new Point(14, 13));
        // To T
        validateFalseSelectionDragPosAndNeg(s,
                new Point(9, 13), new Point(13, 14));
        // To B
        validateFalseSelectionDragPosAndNeg(s,
                new Point(9, 8), new Point(13, 9));
        // To R
        validateFalseSelectionDragPosAndNeg(s,
                new Point(8, 9), new Point(9, 13));

    }

    @Test
    public void testNegShape() {
        var s = new PaintShape(new Point(12, 12),
                new Point(10, 10), null);

        // Surrounded
        validateTrueSelectionDragPosAndNeg(s,
                new Point(9, 9), new Point(13, 13));

        // On BR Corner
        validateTrueSelectionDragPosAndNeg(s,
                new Point(9, 9), new Point(11, 11));

        // On BL Corner
        validateTrueSelectionDragPosAndNeg(s,
                new Point(11, 9), new Point(13, 11));

        // On TR Corner
        validateTrueSelectionDragPosAndNeg(s,
                new Point(9, 11), new Point(11, 13));

        // On TL Corner
        validateTrueSelectionDragPosAndNeg(s,
                new Point(11, 11), new Point(13, 13));

        // On B Edge
        validateTrueSelectionDragPosAndNeg(s,
                new Point(9, 9), new Point(13, 11));

        // On T Edge
        validateTrueSelectionDragPosAndNeg(s,
                new Point(9, 11), new Point(13, 13));

        // On R Edge
        validateTrueSelectionDragPosAndNeg(s,
                new Point(9, 9), new Point(11, 13));

        // On L Edge
        validateTrueSelectionDragPosAndNeg(s,
                new Point(11, 9), new Point(13, 13));

        // To L
        validateFalseSelectionDragPosAndNeg(s,
                new Point(13, 9), new Point(14, 13));
        // To T
        validateFalseSelectionDragPosAndNeg(s,
                new Point(9, 13), new Point(13, 14));
        // To B
        validateFalseSelectionDragPosAndNeg(s,
                new Point(9, 8), new Point(13, 9));
        // To R
        validateFalseSelectionDragPosAndNeg(s,
                new Point(8, 9), new Point(9, 13));

    }

    @Test
    public void testClickPosShape() {
        var s = new PaintShape(new Point(10, 10),
                new Point(12, 12), null);

        assertTrue(s.collides(new Point(11, 11),
                new Point(11, 11)));

        assertFalse(s.collides(new Point(9, 9),
                new Point(9, 9)));
    }

    @Test
    public void testOneDimensionalDrag() {
        var s = new PaintShape(new Point(10, 10),
                new Point(12, 12), null);

        // Horizontal line
        assertTrue(s.collides(new Point(11, 9),
                new Point(11, 13)));

        // Vertical line
        assertTrue(s.collides(new Point(11, 9),
                new Point(11, 13)));

        assertFalse(s.collides(new Point(11, 13),
                new Point(11, 14)));
    }

}
