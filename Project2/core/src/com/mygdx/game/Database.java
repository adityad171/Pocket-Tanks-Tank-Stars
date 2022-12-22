package com.mygdx.game;

import com.badlogic.gdx.utils.Array;
import screens.Choose_Tanks;
import screens.Main_Page;
import screens.Start;

import java.io.Serializable;
import java.util.ArrayList;

public class Database implements Serializable {


    public ArrayList<Tanks> getTankList() {
        return TankList;
    }
    private ArrayList<Tanks> TankList=new ArrayList<Tanks>();

    public int[] getArrY() {
        return arrY;
    }

    private int [] arrY={120,125,125,130,130,135,137,140, 143, 145, 148, 154, 154, 154, 146, 146, 142, 136, 132, 124, 122, 120, 120, 122, 124, 122, 124, 120, 118, 118, 120, 126, 124, 122, 120, 123, 123, 124, 125, 132, 133,124,121, 121, 120 , 122, 123, 128, 122, 120, 122, 124, 124, 126, 126, 124, 128, 130, 124};

}
