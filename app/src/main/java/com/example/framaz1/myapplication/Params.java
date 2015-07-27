package com.example.framaz1.myapplication;

import android.content.res.Resources;
import android.graphics.Point;
import android.hardware.display.DisplayManager;
import android.util.DisplayMetrics;
import android.view.Display;

import com.example.framaz1.myapplication.Items.MotherItem;

/**
 * Created by framaz1 on 04.03.2015.
 */
public class Params {
    public static boolean menu=false,inventory=false,map,gameField=true,item=false,stats=false;
    public static int size=64;
    public static Resources resource;
    public static int itemToShow;
    public static DisplayMetrics displaySettings=new DisplayMetrics();
    public static int displayX=0,displayY=0;
    public static int iconOversize;
    public static void startGameField()
    {
        gameField=true;
        menu=false;
        inventory=false;
        map=false;
        item=false;
        stats=false;
    }
    public static void startInventory()
    {
        inventory=true;
        gameField=false;
        menu=false;
        map=false;
        item=false;
        stats=false;
    }
    public static void startItem(int itemTo)
    {
        inventory=false;
        gameField=false;
        menu=false;
        map=false;
        item=true;
        stats=false;
        itemToShow=itemTo;

    }
    public static void startStats()
    {
        inventory=false;
        gameField=false;
        menu=false;
        map=false;
        item=false;
        stats=true;
    }
    public static void centerOnPlayer()
    {


            displayX = Game.player.yCoordinates * size + size / 2 - Params.displaySettings.heightPixels / 2;
            displayY = Game.player.xCoordinates * size + size / 2 - Params.displaySettings.widthPixels / 2;

        if(displayX<-AllBitmaps.standartIconSize) displayX=-AllBitmaps.standartIconSize;
        if(displayY<0) displayY=0;
        if (Params.displayX > 100 * Params.size - Params.displaySettings.heightPixels+AllBitmaps.standartIconSize+38)
            Params.displayX = 100 * Params.size - Params.displaySettings.heightPixels+AllBitmaps.standartIconSize+38;
        if (Params.displayY > 100 * Params.size - Params.displaySettings.widthPixels)
            Params.displayY = 100 * Params.size - Params.displaySettings.widthPixels;
    }
}
