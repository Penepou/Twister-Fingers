package com.ut3.twister_fingers.Roulette;

import android.graphics.Color;

public enum SpotColor {
    ROUGE, BLEU, VERT, JAUNE;

    public int getColor() {
        switch (this) {
            case ROUGE:
                return Color.RED;
            case BLEU:
                return Color.BLUE;
            case VERT:
                return Color.GREEN;
            case JAUNE:
                return Color.YELLOW;
            default:
                throw new IllegalStateException("Unexpected value: " + this);
        }
    }
}
