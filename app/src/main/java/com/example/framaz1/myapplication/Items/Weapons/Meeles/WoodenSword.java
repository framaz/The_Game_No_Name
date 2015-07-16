package com.example.framaz1.myapplication.Items.Weapons.Meeles;

import com.example.framaz1.myapplication.AllBitmaps;
import com.example.framaz1.myapplication.Items.Weapons.MeeleWeapon;

/**
 * Created by framaz1 on 26.03.2015.
 */
public class WoodenSword extends MeeleWeapon {
    public WoodenSword()
    {
        super();
        name="Wooden Sword";
        description="12354654561 12316857498 12321 4564 1231 8789 44422 22";
        picture= AllBitmaps.woodenSword;
        strNeeded=3;
        agiNeeded=2;
        intNeeded=0;
        bonus=3;
        delay=1;
    }
}
