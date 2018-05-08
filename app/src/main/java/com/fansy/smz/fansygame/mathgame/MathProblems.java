package com.fansy.smz.fansygame.mathgame;

import android.os.SystemClock;
import android.support.annotation.NonNull;

import java.util.Random;

/**
 * Created by hongxujie on 1/18/18.
 */

public class MathProblems {
    private static final int OPERATION_ADD = 0;
    private static final int OPERATION_SUB = 1;

    private int x = 0;
    private int y = 0;
    private int z = 0;
    private String mFormula = "";
    private Random rand = new Random();

    public String createMathFormula(){
        String formula = "";
        rand.setSeed(SystemClock.currentThreadTimeMillis());


        if(rand.nextInt() % 2 == 0 ){
            formula = createAddProblem();
            while (formula.compareTo(mFormula) == 0){
                formula = createAddProblem();
            }
        } else {
            formula = createSubProblem();
            while (formula.compareTo(mFormula) == 0){
                formula = createSubProblem();
            }
        }
        mFormula = formula;

        return mFormula;
    }

    public void setMathFormula(int x, int y, int z, String formula){
        this.x = x;
        this.y = y;
        this.z = z;
        this.mFormula = formula;
    }

    public String getFormula(){
        return mFormula;
    }

    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public int getZ(){
        return z;
    }

    public int getAnswer(){
        return z;
    }

    @NonNull
    private String createAddProblem(){
        x = rand.nextInt(9) + 1;
        y = rand.nextInt(9) + 1;

        while (x + y < 10){
            y = rand.nextInt(9) + 1;
        }

        z = x + y;

        return x + " + " + y + " = ";
    }

    @NonNull
    private String createSubProblem(){
        x = rand.nextInt(11) + 10;
        y = rand.nextInt(21);

        while (x - y < 0 || x - y > 9){
            y = rand.nextInt(21);
        }

        z = x - y;

        return x + " - " + y + " = ";
    }
}
