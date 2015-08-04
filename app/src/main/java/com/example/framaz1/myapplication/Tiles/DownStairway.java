package com.example.framaz1.myapplication.Tiles;

import com.example.framaz1.myapplication.Creatures.MotherCreature;
import com.example.framaz1.myapplication.Game;
import com.example.framaz1.myapplication.Params;

/**
 * Created by framaz1 on 13.04.2015.
 */
public class DownStairway extends Tile {
    public DownStairway()
    {
        super();
        interractable=true;
    }
    @Override
    public void toInteract(MotherCreature who)
    {
        Game.changeLevel(linker+5,Game.layer+1);
        Params.centerOnPlayer();
    }
}
