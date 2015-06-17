package com.example.framaz1.myapplication.Map_etc;

import com.example.framaz1.myapplication.Creatures.MotherCreature;
import com.example.framaz1.myapplication.Tiles.Tile;

import java.util.LinkedList;

/**
 * Created by framaz1 on 22.03.2015.
 */
public class Mothermap {
    public Tile field[][];
    public LinkedList<MotherCreature> creatures;
    public boolean generated;
    public void generateTheField()
    {

    }
    public Mothermap()
    {
        creatures=new LinkedList<MotherCreature>();
        field=new Tile[100][100];
        generated=false;
    }
}
