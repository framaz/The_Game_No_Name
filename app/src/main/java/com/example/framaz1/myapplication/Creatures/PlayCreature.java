package com.example.framaz1.myapplication.Creatures;

import com.example.framaz1.myapplication.AllBitmaps;
import com.example.framaz1.myapplication.Game;
import com.example.framaz1.myapplication.Items.Weapons.Meeles.WoodenSword;
import com.example.framaz1.myapplication.MainGameActivity;
import com.example.framaz1.myapplication.Params;
import com.example.framaz1.myapplication.Tiles.Tile;
import com.example.framaz1.myapplication.TouchAndThreadParams;

/**
 * Created by framaz1 on 23.03.2015.
 */
public class PlayCreature extends MotherCreature {
    public PlayCreature()
    {
        super();
        health=10;
        maxHP=health;
        mana=10;
        maxMana=15;
        maxVision=10000;
        drawId=101;
        str=10;
        experience=9;        //TODO remove this
        lvlUps=3;
        intel=10;
        agi=10;
        items[0]=new WoodenSword();
        items[5]=new WoodenSword();
    }
    @Override
    public void behavior(){
        String choice;
        Game.doneMyTurn=false;
        point: while (!Game.doneMyTurn) {
            if(pathing.size()<1) {
                synchronized (TouchAndThreadParams.gameSync) {
                    try {
                        TouchAndThreadParams.gameSync.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                choice = TouchAndThreadParams.getWhatPlayerDesired();
            }
            else
            choice="Move";
            switch (choice) {

                //OnField

                case "Move":
                    if(pathing.size()<1) {

                        int x, y;
                        y = (Game.whereToGoY + Params.displayY) / Params.size;
                        x = (Game.whereToGoX + Params.displayX) / Params.size;
                        if (Math.max(Math.abs(yCoordinates - x), Math.abs(xCoordinates - y)) < 2 && Game.gamedepths[Game.layer].field[x][y].iswall||!Game.gamedepths[Game.layer].field[x][y].wasSeen)
                            continue point;
                        if ((Game.gamedepths[Game.layer].field[x][y + 1].iswall || Game.gamedepths[Game.layer].field[x][y + 1].isMobHere) &&
                                (Game.gamedepths[Game.layer].field[x + 1][y + 1].iswall || Game.gamedepths[Game.layer].field[x + 1][y + 1].isMobHere) &&
                                (Game.gamedepths[Game.layer].field[x + 1][y].iswall || Game.gamedepths[Game.layer].field[x + 1][y].isMobHere) &&
                                (Game.gamedepths[Game.layer].field[x + 1][y - 1].iswall || Game.gamedepths[Game.layer].field[x + 1][y - 1].isMobHere) &&
                                (Game.gamedepths[Game.layer].field[x][y - 1].iswall || Game.gamedepths[Game.layer].field[x][y - 1].isMobHere) &&
                                (Game.gamedepths[Game.layer].field[x - 1][y - 1].iswall || Game.gamedepths[Game.layer].field[x - 1][y - 1].isMobHere) &&
                                (Game.gamedepths[Game.layer].field[x - 1][y].iswall || Game.gamedepths[Game.layer].field[x - 1][y].isMobHere) &&
                                (Game.gamedepths[Game.layer].field[x - 1][y + 1].iswall || Game.gamedepths[Game.layer].field[x - 1][y + 1].isMobHere))
                            continue point;
                        if (Game.gamedepths[Game.layer].field[x][y].isMobHere == false || Math.max(Math.abs(yCoordinates - x), Math.abs(xCoordinates - y)) > 1) {
                            pathing = Game.pathFinding(this, y, x);
                            if(Game.player.xCoordinates*Params.size-Params.displayY>100&&Game.player.xCoordinates*Params.size-Params.displayY<Params.displaySettings.widthPixels-100 &&
                                    Game.player.yCoordinates*Params.size-Params.displayX>0&&Game.player.yCoordinates*Params.size-Params.displayX<Params.displaySettings.heightPixels-100-Params.size) {

                            }
                            else
                            Params.centerOnPlayer();
                            if(pathing.size()>0)
                            move(pathing.poll(), Game.gamedepths[Game.layer].field[yCoordinates][xCoordinates]);
                        } else if (Math.max(Math.abs(yCoordinates - x), Math.abs(xCoordinates - y)) == 1) {
                            attack(y, x);
                            int whom = Game.findCreatureByCoordinater(y, x);
                            if(Game.gamedepths[Game.layer].creatures.get(whom).health<=0)
                            {
                                Game.player.addExp(Game.gamedepths[Game.layer].creatures.get(whom).expOnDeath);
                                Game.gamedepths[Game.layer].creatures.get(whom).die();
                            }
                        }
                        else
                            break;
                    }
                    else {
                        move(pathing.poll(), Game.gamedepths[Game.layer].field[yCoordinates][xCoordinates]);
                    }
                    Game.doneMyTurn = true;
                    break;

                case "Wait":
                    Game.doneMyTurn=true;
                    toWait=(int)moveDelay;
                    break;
                case "Inventory":
                    Params.startInventory();
                    synchronized (TouchAndThreadParams.outStreamSync)
                    {
                        TouchAndThreadParams.outStreamSync.notifyAll();
                    }
                    break;
                case "Stats":
                    Params.startStats();
                    synchronized (TouchAndThreadParams.outStreamSync)
                    {
                        TouchAndThreadParams.outStreamSync.notifyAll();
                    }
                    break;
                case "TakeFromFloor":
                    if(take())
                    {
                        Params.startGameField();
                        toWait=(int)(moveDelay);
                        Game.doneMyTurn=true;
                    }
                    break;
                case "Interact":
                    Game.gamedepths[Game.layer].field[yCoordinates][xCoordinates].toInteract(this);
                    toWait=(int)(moveDelay);
                    Game.doneMyTurn=true;
                //In Inventory

                case "ChooseItem":
                    int widthOfInv=AllBitmaps.inventoryImage.getWidth();
                    int heightOfInv=AllBitmaps.inventoryImage.getHeight();
                    Game.whereToGoY=Game.whereToGoY-(Params.displaySettings.widthPixels/2 - AllBitmaps.inventoryImage.getWidth()/2)-4*widthOfInv/288;
                    int wid=(int)(Game.whereToGoY/(56*widthOfInv/288));
                    Game.whereToGoX=Game.whereToGoX-(Params.displaySettings.heightPixels/2 - AllBitmaps.inventoryImage.getHeight()/2)-108*widthOfInv/288;
                    int hei=(int)(Game.whereToGoX/(88*heightOfInv/464));
                    if(wid==5)
                        wid=4;
                    if(hei>3)
                        hei=3;
                    int what=wid+hei*5;
                    if(Game.whereToGoX<0) what=wid-5;
                    if((what==-5&&!bodyWear.name.equals(""))||(what==-4&&!weapon.name.equals(""))||(what==-3&&!jewel.name.equals(""))
                            ||(what==-2&&!ring1.name.equals(""))||(what==-1&&!ring2.name.equals(""))||(what>=0&& !items[what].name.equals("")))
                    {
                        Params.startItem(what);
                        synchronized (TouchAndThreadParams.outStreamSync) {
                            TouchAndThreadParams.outStreamSync.notifyAll();
                        }
                    }
                    break;
                case "Back":
                    Params.startGameField();
                    synchronized (TouchAndThreadParams.outStreamSync) {
                        TouchAndThreadParams.outStreamSync.notifyAll();
                    }
                    break;

                    //In ItemView

                case "PushInventoryButton":
                    //1 - Equip/Unequip  2 - Use   3 - Drop
                    widthOfInv=AllBitmaps.inventoryImage.getWidth();
                    Game.whereToGoY=Game.whereToGoY-(Params.displaySettings.widthPixels/2 - AllBitmaps.inventoryImage.getWidth()/2);
                    int whatToChoose=(int)((Game.whereToGoY)/(96*widthOfInv/288))+1;
                    switch (whatToChoose)
                    {
                        case 1:
                            if(Params.itemToShow<0) {
                                if (unEquip(Params.itemToShow)) {
                                    Game.doneMyTurn = true;
                                    toWait=(int)moveDelay;
                                    Params.startGameField();
                                }
                            }
                            else
                            {

                                if (items[Params.itemToShow].canEquip &&equip(Params.itemToShow)) {
                                    Game.doneMyTurn = true;
                                    toWait=(int)moveDelay;
                                    Params.startGameField();
                                }
                                else
                                    if(items[Params.itemToShow].canEquip)
                                    {
                                        Params.startInventory();

                                    }
                            }
                            synchronized (TouchAndThreadParams.outStreamSync) {
                                TouchAndThreadParams.outStreamSync.notifyAll();
                            }
                            break;
                        case 3:
                            if(Params.itemToShow>=0) {
                                drop(Params.itemToShow);
                                Params.startGameField();
                                Game.doneMyTurn=true;
                                toWait=(int)moveDelay;
                                makeInventoryFit();
                                synchronized (TouchAndThreadParams.outStreamSync) {
                                    TouchAndThreadParams.outStreamSync.notifyAll();
                                }
                            }
                    }
                    break;
                case "BackToInventory":
                    Params.startInventory();
                    synchronized (TouchAndThreadParams.outStreamSync) {
                        TouchAndThreadParams.outStreamSync.notifyAll();
                    }
                    break;
                //in stats
                case "StatUp":
                    if(lvlUps>=1) {
                        widthOfInv = AllBitmaps.statsView.getWidth();
                        heightOfInv = AllBitmaps.statsView.getHeight();
                        Game.whereToGoX = Game.whereToGoX - (Params.displaySettings.heightPixels / 2 - AllBitmaps.statsView.getHeight() / 2) - 54 * widthOfInv / 288;
                        int whatStatToUp = (int) (Game.whereToGoX / (46 * widthOfInv / 288));
                        switch (whatStatToUp) {
                            case 0:
                                maxHP += 5;
                                break;
                            case 1:
                                maxMana += 5;
                                break;
                            case 2:
                                str++;
                                break;
                            case 3:
                                intel++;
                                break;
                            case 4:
                                agi++;
                                break;
                        }
                        lvlUps--;
                    }
                    break;
                case "Nothing":
                    break;


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

        }
    }
    @Override
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

            Game.fieldOfView();
            Game.gamedepths[Game.layer].field[yCoordinates][xCoordinates].isMobHere = true;
        }
    }
}
