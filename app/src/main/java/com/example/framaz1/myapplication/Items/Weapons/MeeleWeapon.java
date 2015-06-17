package com.example.framaz1.myapplication.Items.Weapons;

import com.example.framaz1.myapplication.Items.ItemType;
import com.example.framaz1.myapplication.Items.MotherItem;

/**
 * Created by framaz1 on 26.03.2015.
 */
public class MeeleWeapon extends MotherItem {
    public MeeleWeapon()
    {
        type= ItemType.MeeleWeapon;
        equipable=true;
    }
}
