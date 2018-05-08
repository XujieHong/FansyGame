package com.fansy.smz.fansygame.mathgame.utils;

/**
 * Created by hongxujie on 1/19/18.
 */

public class Book {
    private int x, y, z;
    private String formula;
    private int score;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "x:" + x + ", y:" + y + ", z:" + z + ", formula:" + formula;
    }
}