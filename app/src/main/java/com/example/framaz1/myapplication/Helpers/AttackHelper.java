package com.example.framaz1.myapplication.Helpers;

import com.example.framaz1.myapplication.Creatures.MotherCreature;

/**
 * Created by framaz1 on 15.04.2015.
 */
public class AttackHelper {
    public boolean stunned,missed,scared;
    public int damage;
    public MotherCreature attacked,attacker;
    public AttackHelper()
    {
        stunned=false;
        missed=false;
        scared=false;
        damage=0;
        attacked=new MotherCreature();
        attacker=new MotherCreature();
    }
}
