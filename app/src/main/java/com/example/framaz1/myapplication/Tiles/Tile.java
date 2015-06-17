package com.example.framaz1.myapplication.Tiles;

import android.graphics.Bitmap;

import com.example.framaz1.myapplication.Creatures.MotherCreature;
import com.example.framaz1.myapplication.Items.MotherItem;

import java.util.LinkedList;
import java.util.Stack;

/**
 * Created by framaz1 on 22.03.2015.
 */
public class Tile {
    public boolean iswall;
    public double movedelay;
    public boolean isMobHere;
    public int goldHere;
    public boolean interractable;
    public int drawId;
    public Stack<MotherItem> itemsHere;
    public int linker;//1-5 downstair 6-10 upstair
    public Tile()
    {
        isMobHere=false;
        itemsHere=new Stack<MotherItem>();
        goldHere=0;
        movedelay=1;
        interractable=false;
        iswall=false;
        linker=-1;
    }
    public void toInteract() {}
    public void toInteract(MotherCreature who) {}
}
