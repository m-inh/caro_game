/*
 * Decompiled with CFR 0_102.
 */
package com.t3h.other;

import java.io.PrintStream;

public class Point {
    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void printf() {
        System.out.println("Toa do: " + this.x + ":" + this.y);
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
}

