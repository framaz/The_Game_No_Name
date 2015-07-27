package com.example.framaz1.myapplication.Creatures;

import com.example.framaz1.myapplication.AllBitmaps;
import com.example.framaz1.myapplication.Game;

import java.util.LinkedList;

/**
 * Created by framaz1 on 23.03.2015.
 */
public class Goblin extends MotherCreature{
    public Goblin()
    {
        super();
        xCoordinates=9;
        yCoordinates=8;
        maxVision=20;
        health=3;
        maxHP=health;
        attack=2;
        str=3;agi=3;intel=3;deffense=1;weight=4;
        expOnDeath=1;
        experience=0;
        aggred=true;
        moveDelay=150;
        drawId=102;
    }
}
