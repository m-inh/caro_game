/*
 * Decompiled with CFR 0_102.
 */
package com.t3h.other;

import java.awt.Color;

public class Caro {
    private int x;
    private int y;
    private int type;
    private Color color;

    public Caro(int x, int y, int type) {
        int temp = x / 50;
        this.x = temp * 50;
        temp = y / 50;
        this.y = temp * 50;
        this.type = type;
        this.color = type == 0 ? Color.BLUE : Color.RED;
    }

    public boolean equals(Caro caro) {
        if (caro.getX() == this.x && caro.getY() == this.y) {
            return true;
        }
        return false;
    }

    public boolean typeEquals(Caro otherCaro) {
        if (otherCaro.type == this.type) {
            return true;
        }
        return false;
    }

    public String toString() {
        if (this.type == 0) {
            return "O";
        }
        return "X";
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getType() {
        return this.type;
    }

    public Color getColor() {
        return this.color;
    }
}

