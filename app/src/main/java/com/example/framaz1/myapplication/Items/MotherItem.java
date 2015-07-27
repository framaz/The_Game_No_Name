package com.example.framaz1.myapplication.Items;

import android.graphics.Bitmap;

import com.example.framaz1.myapplication.Creatures.MotherCreature;

public class MotherItem {
    public ItemType type;
    public String name, description;
    public Bitmap picture;
    public MotherItem()
    {
        equipable=false;
        usable=false;
    }
    public String getInformation() {
        return "";
    }
    public double delay;
    public int strNeeded, agiNeeded, intNeeded;
    //public EffectEnum effect;
    public int weight;
    public boolean equipable,usable;
    public int bonus, strBonus, agiBonus, intBonus;

    public void use() {
    }
    public boolean use(MotherCreature mc) {
        if (!equip(mc)) {
            return false;
        }
        return true;
    }
    public boolean equip(MotherCreature creature) {
        switch (type) {
            case Helm:
                if (creature.helmet.name.equals("")) {
                    creature.helmet = this;
                    creature.str += strBonus;
                    creature.intel += intBonus;
                    creature.agi += agiBonus;
                    creature.weight += weight;
                    creature.deffense += bonus;
                    return true;
                } else return false;
            case BodyWear:
                if (creature.bodyWear.name.equals("")) {
                    creature.bodyWear = this;
                    creature.str += strBonus;
                    creature.intel += intBonus;
                    creature.agi += agiBonus;
                    creature.weight += weight;
                    creature.deffense += bonus;
                    return true;
                } else return false;
            case MeeleWeapon:
            case RangedWeapon:
                if (creature.weapon.name.equals("")) {
                    creature.weapon = this;
                    creature.str += strBonus;
                    creature.intel += intBonus;
                    creature.agi += agiBonus;
                    creature.weight += weight;
                    creature.attack+=bonus;
                    return true;
                } else return false;
            case Jewel:
                if (creature.jewel.name.equals("")) {
                    creature.jewel = this;
                    creature.str += strBonus;
                    creature.intel += intBonus;
                    creature.agi += agiBonus;
                    creature.weight += weight;
                    return true;
                } else return false;
            case Ring:
                if (creature.ring1.name.equals("")) {
                    creature.ring1 = this;
                    creature.str += strBonus;
                    creature.intel += intBonus;
                    creature.agi += agiBonus;
                    creature.weight += weight;
                    return true;
                } else {
                    if (creature.ring2.name.equals("")) {
                        creature.ring2 = this;
                        creature.str += strBonus;
                        creature.intel += intBonus;
                        creature.agi += agiBonus;
                        creature.weight += weight;
                        return true;
                    } else
                        return false;
                }
        }
        return false;
    }
    public boolean unEquip(MotherCreature creature) {
        int i;
        for(i=0;i<20&&!creature.items[i].name.equals("");i++)
        {
        }
        if(i!=20) {
            switch (type) {
                case Helm:
                    creature.str -= strBonus;
                    creature.intel -= intBonus;
                    creature.agi -= agiBonus;
                    creature.weight -= weight;
                    creature.deffense -= bonus;
                case BodyWear:
                    creature.str -= strBonus;
                    creature.intel -= intBonus;
                    creature.agi -= agiBonus;
                    creature.weight -= weight;
                    creature.deffense -= bonus;
                case MeeleWeapon:
                case RangedWeapon:
                    creature.str -= strBonus;
                    creature.intel -= intBonus;
                    creature.agi -= agiBonus;
                    creature.weight -= weight;
                    creature.attack -= bonus;
                case Jewel:
                    creature.str -= strBonus;
                    creature.intel -= intBonus;
                    creature.agi -= agiBonus;
                    creature.weight -= weight;
                case Ring:
                    creature.str -= strBonus;
                    creature.intel -= intBonus;
                    creature.agi -= agiBonus;
                    creature.weight -= weight;
            }
            creature.items[i]=this;
            return true;
        }
        return false;
    }
}
