package model;

import java.awt.*;

public enum ShapeColor {
    BLACK {
        @SuppressWarnings("unused")
        public Color getColor(){
            return Color.BLACK;
        }
    },
    BLUE,
    CYAN,
    DARK_GRAY,
    GRAY,
    GREEN,
    LIGHT_GRAY,
    MAGENTA,
    ORANGE,
    PINK,
    RED,
    WHITE,
    YELLOW
}
