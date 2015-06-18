package com.example.framaz1.myapplication.Creatures;

import com.example.framaz1.myapplication.AllBitmaps;
import com.example.framaz1.myapplication.Game;
import com.example.framaz1.myapplication.Items.Weapons.Meeles.WoodenSword;
import com.example.framaz1.myapplication.Params;
import com.example.framaz1.myapplication.TouchAndThreadParams;

import java.util.LinkedList;

/**
 * Created by framaz1 on 23.03.2015.
 */
public class PlayCreature extends MotherCreature {
    public PlayCreature()
    {
        super();
        health=10;
        maxVision=10000;
        drawId=101;
        str=10;
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
            synchronized (TouchAndThreadParams.gameSync) {
                try {
                    TouchAndThreadParams.gameSync.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            choice = TouchAndThreadParams.getWhatPlayerDesired();
            switch (choice) {

                //OnField

                case "Move":
                    int x, y;
                    y = (Game.whereToGoY + Params.displayY) / Params.size;
                    x = (Game.whereToGoX + Params.displayX) / Params.size;
                    if (Math.max(Math.abs(yCoordinates - x), Math.abs(xCoordinates - y)) < 2 && Game.gamedepths[Game.layer].field[x][y].iswall == true)
                        continue point;
                    if((Game.gamedepths[Game.layer].field[x][y+1].iswall||Game.gamedepths[Game.layer].field[x][y+1].isMobHere)&&
                            (Game.gamedepths[Game.layer].field[x+1][y+1].iswall||Game.gamedepths[Game.layer].field[x+1][y+1].isMobHere)&&
                            (Game.gamedepths[Game.layer].field[x+1][y].iswall||Game.gamedepths[Game.layer].field[x+1][y].isMobHere)&&
                            (Game.gamedepths[Game.layer].field[x+1][y-1].iswall||Game.gamedepths[Game.layer].field[x+1][y-1].isMobHere)&&
                            (Game.gamedepths[Game.layer].field[x][y-1].iswall||Game.gamedepths[Game.layer].field[x][y-1].isMobHere)&&
                            (Game.gamedepths[Game.layer].field[x-1][y-1].iswall||Game.gamedepths[Game.layer].field[x-1][y-1].isMobHere)&&
                            (Game.gamedepths[Game.layer].field[x-1][y].iswall||Game.gamedepths[Game.layer].field[x-1][y].isMobHere)&&
                            (Game.gamedepths[Game.layer].field[x-1][y+1].iswall||Game.gamedepths[Game.layer].field[x-1][y+1].isMobHere))
                        continue point;
                    if (Game.gamedepths[Game.layer].field[x][y].isMobHere == false || Math.max(Math.abs(yCoordinates - x), Math.abs(xCoordinates - y)) > 1) {
                        LinkedList<String> pathing = Game.pathFinding(this, y, x);
                        move(pathing.poll(), Game.gamedepths[Game.layer].field[yCoordinates][xCoordinates]);
                    } else
                    if(Math.max(Math.abs(yCoordinates - x), Math.abs(xCoordinates - y)) == 1)
                        attack(y, x);
                    else
                    break;
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

                                if (items[Params.itemToShow].equipable&&equip(Params.itemToShow)) {
                                    Game.doneMyTurn = true;
                                    toWait=(int)moveDelay;
                                    Params.startGameField();
                                }
                                else
                                    if(items[Params.itemToShow].equipable)
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
}