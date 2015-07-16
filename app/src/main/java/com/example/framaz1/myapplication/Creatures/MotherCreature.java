package com.example.framaz1.myapplication.Creatures;

import com.example.framaz1.myapplication.Game;
import com.example.framaz1.myapplication.Helpers.AttackHelper;
import com.example.framaz1.myapplication.Items.EmptyItem;
import com.example.framaz1.myapplication.Items.MotherItem;
import com.example.framaz1.myapplication.MainGameActivity;
import com.example.framaz1.myapplication.Tiles.Tile;
import com.example.framaz1.myapplication.TouchAndThreadParams;

import java.util.LinkedList;


public class MotherCreature {
    public int health,mana,name,maxHP;
    public boolean aggred;
    public int str,agi,intel,deffense,attack;
    public int xCoordinates,yCoordinates;
    public int drawId;
    public int lvl,lvlUps,experience;
    public int expOnDeath;
    public int maxVision;
    public LinkedList<String> pathing;
    public MotherItem helmet,bodyWear,weapon,ring1,ring2,jewel;
    public double moveDelay;
    public double weight;
    public boolean orientatedToRight;
    public int gold;
    public int toWait;
    public MotherItem items[];
    public MotherCreature(){
        helmet=new EmptyItem();
        bodyWear=new EmptyItem();
        weapon=new EmptyItem();
        ring1=new EmptyItem();
        ring2=new EmptyItem();
        lvl=1;
        lvlUps=0;
        jewel=new EmptyItem();
        pathing=new LinkedList<String>();
        orientatedToRight=true;
        moveDelay=100;
        gold=300;
        items=new MotherItem[20];
        for(int i=0;i<20;i++)
            items[i]=new EmptyItem();
    }
    public void behavior(){
        if(aggred)
        {
            LinkedList<String> pathing=Game.pathFinding(this, Game.player);
            synchronized (TouchAndThreadParams.outStreamSync) {
                if (pathing.size() > 1) {
                    move(pathing.poll(), Game.gamedepths[Game.layer].field[yCoordinates][xCoordinates]);
                    int talala=3;
                }
                else
                if (pathing.size() == 1) {
                    attack(Game.player.xCoordinates, Game.player.yCoordinates);
                }
                TouchAndThreadParams.outStreamSync.notifyAll();
            }
        }
    }
    protected boolean unEquip(int what) {
        switch (what)
        {
            case -5:
                if(bodyWear.unEquip(this))
                {
                    bodyWear=new EmptyItem();
                    return true;
                }
            case -4:
                if(weapon.unEquip(this))
                {
                    weapon=new EmptyItem();
                    return true;
                }
            case -3:
                if(jewel.unEquip(this))
                {
                    jewel=new EmptyItem();
                    return true;
                }
            case -2:
                if(ring1.unEquip(this))
                {
                    ring1=new EmptyItem();
                    return true;
                }
            case -1:
                if(ring2.unEquip(this))
                {
                    ring2=new EmptyItem();
                    return true;
                }

        }
        return false;
    }
    protected boolean equip(int what){
        if(items[what].equip(this))
        {
            items[what]=new EmptyItem();
            makeInventoryFit();
            return true;
        }
        return false;
    }
    protected void move(String str,Tile wherefrom) {
        synchronized (TouchAndThreadParams.animationSync) {
        toWait = (int) (moveDelay * wherefrom.movedelay);
        Game.gamedepths[Game.layer].field[yCoordinates][xCoordinates].isMobHere = false;
        MainGameActivity.sf.animThread.setMoveAnimation(this, xCoordinates, yCoordinates);
                switch (str) {
                    case "Up":
                        yCoordinates--;
                        break;
                    case "Down":
                        yCoordinates++;
                        break;
                    case "Left":
                        xCoordinates--;
                        orientatedToRight=false;
                        break;
                    case "Right":
                        xCoordinates++;
                        orientatedToRight=true;
                        break;
                    case "UpLeft":
                        xCoordinates--;
                        orientatedToRight=false;
                        yCoordinates--;
                        break;
                    case "UpRight":
                        yCoordinates--;
                        xCoordinates++;
                        orientatedToRight=true;
                        break;
                    case "DownLeft":
                        yCoordinates++;
                        xCoordinates--;
                        orientatedToRight=false;
                        break;
                    case "DownRight":
                        yCoordinates++;
                        xCoordinates++;
                        orientatedToRight=true;
                        break;
                }
               TouchAndThreadParams.animationSync.notifyAll();
               try {
                   TouchAndThreadParams.animationSync.wait();

               } catch (InterruptedException e) {}

           //     TouchAndThreadParams.outStreamSync.notifyAll();


                Game.gamedepths[Game.layer].field[yCoordinates][xCoordinates].isMobHere = true;
        }
    }
    protected boolean attack(int x,int y) {
        int whom = Game.findCreatureByCoordinater(x, y);
        AttackHelper helper=new AttackHelper();
        if(xCoordinates<x)
            orientatedToRight=true;
        if(xCoordinates>x)
            orientatedToRight=false;
        helper.attacker=this;
        //Delays

        toWait = (int) (moveDelay * weapon.delay);
        if(weight>str)
            toWait*=(weight*weight)/(str*str);
        if(weapon.agiNeeded>agi)
            toWait*=weapon.agiNeeded/agi;
        if(weapon.strNeeded>str)
            toWait*=weapon.strNeeded/str;

        //Whom to attack

        MotherCreature who;
        if(whom>=0)
            who=Game.gamedepths[Game.layer].creatures.get(whom);
        else
            who=Game.player;
        helper.attacked=who;
        //Battle

        //Dodge

        int hitchance=90-10*((int)(who.weight/((1/4)*who.str*10)));
        if(agi<who.agi)
            hitchance=hitchance-(who.agi-agi)*5;
        if(hitchance<30)
            hitchance=30;

        //Hit

        int toHit=(int)(Math.floor(attack/2+Math.random()*attack/2+1));
        int critChance=(int)(str+agi)/2;
        int toDef=(int)(Math.floor(who.deffense/2+Math.random()*who.deffense/2+1));

        //Applying effects
        //
        //

        //Final calculations
        if(Math.random()*100<critChance)
            toHit*=2;
        int asdasd=(int)(Math.random()*100);
        if(asdasd<hitchance)
        {
            if(toHit<=toDef)
            {
                toHit=toDef+1;
            }
            int rawDamage=toHit-toDef;
            helper.damage=rawDamage;
            who.health-=rawDamage;
            if(whom>=0)
                Game.gamedepths[Game.layer].creatures.get(whom).health-=rawDamage;
            else
                Game.player.health-=rawDamage;
        }
        else
            helper.missed=true;
        synchronized (TouchAndThreadParams.animationSync)
        {
            MainGameActivity.sf.animThread.setAttackAnimation(helper);
            TouchAndThreadParams.animationSync.notifyAll();
            try {
                TouchAndThreadParams.animationSync.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        synchronized (TouchAndThreadParams.outStreamSync)
        {
            TouchAndThreadParams.outStreamSync.notifyAll();
            try {
                TouchAndThreadParams.outStreamSync.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return who.health <= 0;
    }
    protected void makeInventoryFit() {
        for(int i=0;i<items.length;i++)
            if (items[i].name.equals("")) {
                int j;
                for (j = i + 1; j < items.length && items[j].name.equals(""); j++) {}
                if(j<items.length)
                {
                    items[i]=items[j];
                    items[j]=new EmptyItem();
                }
            }
    }
    protected void drop(int what) {
        Game.gamedepths[Game.layer].field[yCoordinates][xCoordinates].itemsHere.add(items[what]);
        items[what]=new EmptyItem();
    }
    protected boolean take()
    {
        if(Game.gamedepths[Game.layer].field[yCoordinates][xCoordinates].goldHere>0)
        {
            gold+=Game.gamedepths[Game.layer].field[yCoordinates][xCoordinates].goldHere;
            Game.gamedepths[Game.layer].field[yCoordinates][xCoordinates].goldHere=0;
            return true;
        }
        else
        {
            if(Game.gamedepths[Game.layer].field[yCoordinates][xCoordinates].itemsHere.size()>0||!items[19].name.equals(""))
            {
                items[19]=Game.gamedepths[Game.layer].field[yCoordinates][xCoordinates].itemsHere.pop();
                makeInventoryFit();
                return true;
            }
        }
        return false;
    }
}
