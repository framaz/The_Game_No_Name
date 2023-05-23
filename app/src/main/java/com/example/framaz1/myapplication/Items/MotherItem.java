package com.example.framaz1.myapplication.Items;

import android.graphics.Bitmap;

import com.example.framaz1.myapplication.Creatures.MotherCreature;

import java.util.Arrays;

public class MotherItem {
    public ItemType type;
    public String name, description;
    public Bitmap picture;

    public double delay;
    public int strengthNeeded, agilityNeeded, intelligenceNeeded;
    public int weight;
    public boolean canEquip, usable;
    public int attackBonus, strBonus, agiBonus, intBonus;

    public MotherItem() {
        canEquip = false;
        usable = false;
    }

    public boolean equip(MotherCreature creature) {
        boolean isEquipped = false;
        switch (type) {
            case Helm:
                if (!creature.helmet.isEquipped()) {
                    creature.helmet = this;
                    isEquipped = true;
                }
                break;
            case BodyWear:
                if (!creature.bodyWear.isEquipped()) {
                    creature.bodyWear = this;
                    isEquipped = true;
                }
                break;
            case MeeleWeapon:
            case RangedWeapon:
                if (!creature.weapon.isEquipped()) {
                    creature.weapon = this;
                    isEquipped = true;
                }
                break;
            case Jewel:
                if (!creature.jewel.isEquipped()) {
                    creature.jewel = this;
                    isEquipped = true;
                }
                break;
            case Ring:
                if (!creature.ring1.isEquipped()) {
                    creature.ring1 = this;
                    isEquipped = true;
                    break;
                }

                if (!creature.ring2.isEquipped()) {
                    creature.ring2 = this;
                    isEquipped = true;
                }
                break;
        }

        creature.applyBonuses(this);
        return isEquipped;
    }

    public boolean unEquip(MotherCreature creature) {
        int emptyInventoryIndex = -1;

        for (int i = 0; i < MotherCreature.inventoryLength; i++) {
            if(!creature.items[i].isEquipped()) {
                emptyInventoryIndex = i;
                break;
            }
        }

        if (emptyInventoryIndex != -1) {
            creature.unapplyBonuses(this);
            creature.items[emptyInventoryIndex] = this;
            return true;
        }
        return false;
    }

    public boolean isEquipped(){
        return !name.equals("");
    }
}
