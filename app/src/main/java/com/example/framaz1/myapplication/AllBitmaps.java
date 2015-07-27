package com.example.framaz1.myapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.LinkedList;

/**
 * Created by framaz1 on 22.03.2015.
 */
public class AllBitmaps {
    private static Bitmap originaldungeonFloor= BitmapFactory.decodeResource(Params.resource,R.drawable.tile); // 1
    private static Bitmap dungeonFloor= BitmapFactory.decodeResource(Params.resource,R.drawable.tile); // 1


    private static Bitmap originaldungeonWall= BitmapFactory.decodeResource(Params.resource,R.drawable.wall); // 2
    private static Bitmap dungeonWall= BitmapFactory.decodeResource(Params.resource,R.drawable.wall);

    private static Bitmap originalladderDown = BitmapFactory.decodeResource(Params.resource,R.drawable.ladder_down); // 3
    private static Bitmap ladderDown = BitmapFactory.decodeResource(Params.resource,R.drawable.ladder_down); // 3

    private static Bitmap originalladderUp = BitmapFactory.decodeResource(Params.resource,R.drawable.ladder_up); //4
    private static Bitmap ladderUp = BitmapFactory.decodeResource(Params.resource,R.drawable.ladder_up); //4

                     //Character   //101

    private static Bitmap originalcharacter = BitmapFactory.decodeResource(Params.resource, R.drawable.character);
    private static Bitmap character = BitmapFactory.decodeResource(Params.resource, R.drawable.character);

    private static LinkedList<Bitmap> originalcharacterMove =new LinkedList<>();
    private static LinkedList<Bitmap> characterMove =new LinkedList<>();

    private static LinkedList<Bitmap> originalcharacterAttack = new LinkedList<>();
    private static LinkedList<Bitmap> characterAttack = new LinkedList<>();


                     //Goblin // 102
    private static Bitmap originalgoblin= BitmapFactory.decodeResource(Params.resource,R.drawable.goblin);
    private static Bitmap goblin= BitmapFactory.decodeResource(Params.resource,R.drawable.goblin);

    private static LinkedList<Bitmap> originalgoblinMove =new LinkedList<>();
    private static LinkedList<Bitmap> goblinMove =new LinkedList<>();

    private static LinkedList<Bitmap> originalgoblinAttack =new LinkedList<>();
    private static LinkedList<Bitmap> goblinAttack =new LinkedList<>();


    public static int standartIconSize=64;
    public static Bitmap woodenSword=BitmapFactory.decodeResource(Params.resource, R.drawable.wooden_sword);
    public static Bitmap waitIcon=Bitmap.createScaledBitmap(BitmapFactory.decodeResource(Params.resource, R.drawable.wait_icon), standartIconSize, standartIconSize, false);
    public static Bitmap magicIcon=Bitmap.createScaledBitmap(BitmapFactory.decodeResource(Params.resource, R.drawable.magic_icon), standartIconSize, standartIconSize, false);
    public static Bitmap inventoryImage=BitmapFactory.decodeResource(Params.resource, R.drawable.inventory);
    public static Bitmap itemView=BitmapFactory.decodeResource(Params.resource,R.drawable.item_view);
    public static Bitmap statsView=BitmapFactory.decodeResource(Params.resource,R.drawable.stats_view);
    public static Bitmap plus=BitmapFactory.decodeResource(Params.resource, R.drawable.plus);
    public static Bitmap statsIcon=Bitmap.createScaledBitmap(BitmapFactory.decodeResource(Params.resource, R.drawable.stats_icon), standartIconSize, standartIconSize, false);
    public static Bitmap inventoryIcon=Bitmap.createScaledBitmap(BitmapFactory.decodeResource(Params.resource, R.drawable.inventory_icon), standartIconSize, standartIconSize,false);
    public static Bitmap menu=Bitmap.createScaledBitmap(BitmapFactory.decodeResource(Params.resource, R.drawable.menu), standartIconSize, standartIconSize,false);
    public static Bitmap leftBar=  BitmapFactory.decodeResource(Params.resource, R.drawable.bars_left) ;
    public static Bitmap rightBar= BitmapFactory.decodeResource(Params.resource, R.drawable.bars_right);
    public static Bitmap centerBar=BitmapFactory.decodeResource(Params.resource, R.drawable.bars_center);
    public static Bitmap healthBar=BitmapFactory.decodeResource(Params.resource, R.drawable.health_bar);
    public static Bitmap manaBar=BitmapFactory.decodeResource(Params.resource, R.drawable.mana_bar);
    public static Bitmap expBar=BitmapFactory.decodeResource(Params.resource, R.drawable.exp_bar);

    public static Bitmap equip= BitmapFactory.decodeResource(Params.resource, R.drawable.equip);
    public static Bitmap unequip= BitmapFactory.decodeResource(Params.resource,R.drawable.unequip);
    public static Bitmap drop= BitmapFactory.decodeResource(Params.resource,R.drawable.drop);
    public static Bitmap use= BitmapFactory.decodeResource(Params.resource,R.drawable.use);
    public static Bitmap coins= BitmapFactory.decodeResource(Params.resource,R.drawable.coins);
    public static Bitmap getItemHere = BitmapFactory.decodeResource(Params.resource,R.drawable.get_item_here);


    public static Bitmap originalmiss = BitmapFactory.decodeResource(Params.resource,R.drawable.miss);
    public static Bitmap miss = BitmapFactory.decodeResource(Params.resource,R.drawable.miss);
    //Numbers

    public static Bitmap[] originalnumbers=new Bitmap[10];
    public static Bitmap[] numbers=new Bitmap[10];
    public static Bitmap space=BitmapFactory.decodeResource(Params.resource,R.drawable.space);

    public static void initialize()
    {
        Params.iconOversize=1;
        for(int i=1;inventoryImage.getWidth()*i<Params.displaySettings.widthPixels-100&&inventoryImage.getHeight()*i<Params.displaySettings.heightPixels-100;i++)
            Params.iconOversize=i;
        getItemHere=Bitmap.createScaledBitmap(getItemHere,getItemHere.getWidth()*Params.iconOversize,getItemHere.getHeight()*Params.iconOversize,false);
        coins=Bitmap.createScaledBitmap(coins,coins.getWidth()*Params.iconOversize,coins.getHeight()*Params.iconOversize,false);
        use=Bitmap.createScaledBitmap(use,use.getWidth()*Params.iconOversize,use.getHeight()*Params.iconOversize,false);
        drop=Bitmap.createScaledBitmap(drop,drop.getWidth()*Params.iconOversize,drop.getHeight()*Params.iconOversize,false);
        unequip=Bitmap.createScaledBitmap(unequip,unequip.getWidth()*Params.iconOversize,unequip.getHeight()*Params.iconOversize,false);
        equip=Bitmap.createScaledBitmap(equip,equip.getWidth()*Params.iconOversize,equip.getHeight()*Params.iconOversize,false);
        inventoryIcon=Bitmap.createScaledBitmap(    inventoryIcon   ,   inventoryIcon   .getWidth()*Params.iconOversize,    inventoryIcon   .getHeight()*Params.iconOversize,false);
        itemView=Bitmap.createScaledBitmap(    itemView   ,   itemView   .getWidth()*Params.iconOversize,    itemView   .getHeight()*Params.iconOversize,false);
        inventoryImage=Bitmap.createScaledBitmap(    inventoryImage   ,   inventoryImage   .getWidth()*Params.iconOversize,    inventoryImage   .getHeight()*Params.iconOversize,false);
        statsView=Bitmap.createScaledBitmap(    statsView   ,   statsView   .getWidth()*Params.iconOversize,    statsView   .getHeight()*Params.iconOversize,false);

        magicIcon=Bitmap.createScaledBitmap(    magicIcon   ,   magicIcon   .getWidth()*Params.iconOversize,    magicIcon   .getHeight()*Params.iconOversize,false);
        waitIcon=Bitmap.createScaledBitmap(    waitIcon   ,   waitIcon   .getWidth()*Params.iconOversize,    waitIcon   .getHeight()*Params.iconOversize,false);
        statsIcon=Bitmap.createScaledBitmap(    statsIcon   ,   statsIcon   .getWidth()*Params.iconOversize,    statsIcon   .getHeight()*Params.iconOversize,false);
        menu=Bitmap.createScaledBitmap(    menu   ,   menu   .getWidth()*Params.iconOversize,    menu   .getHeight()*Params.iconOversize,false);
        leftBar=Bitmap.createScaledBitmap(leftBar,leftBar.getWidth(),standartIconSize,false);
        leftBar=Bitmap.createScaledBitmap(    leftBar   ,   leftBar   .getWidth()*Params.iconOversize,    leftBar   .getHeight()*Params.iconOversize,false);
        rightBar=Bitmap.createScaledBitmap(rightBar,rightBar.getWidth(),standartIconSize,false);
        rightBar=Bitmap.createScaledBitmap(    rightBar   ,   rightBar .getWidth()*Params.iconOversize,    rightBar   .getHeight()*Params.iconOversize,false);
        centerBar=Bitmap.createScaledBitmap(centerBar,1,standartIconSize,false);
        centerBar=Bitmap.createScaledBitmap(    centerBar   ,  1  ,    centerBar   .getHeight()*Params.iconOversize,false);
        healthBar=Bitmap.createScaledBitmap(healthBar,1,12,false);
        healthBar=Bitmap.createScaledBitmap(    healthBar   ,  1  ,    healthBar   .getHeight()*Params.iconOversize,false);
        manaBar=Bitmap.createScaledBitmap(manaBar,1,12,false);
        manaBar=Bitmap.createScaledBitmap(    manaBar   ,  1  ,    manaBar   .getHeight()*Params.iconOversize,false);
        expBar=Bitmap.createScaledBitmap(expBar,1,12,false);
        expBar=Bitmap.createScaledBitmap(    expBar   ,  1  ,    expBar   .getHeight()*Params.iconOversize,false);
   //     woodenSword=Bitmap.createScaledBitmap(    woodenSword   ,   woodenSword   .getWidth()*Params.iconOversize,    woodenSword   .getHeight()*Params.iconOversize,false);
        standartIconSize*=Params.iconOversize;

        originalcharacterMove.add(BitmapFactory.decodeResource(Params.resource, R.drawable.character_move1));
        originalcharacterMove.add(BitmapFactory.decodeResource(Params.resource, R.drawable.character_move2));
        originalcharacterAttack.add(BitmapFactory.decodeResource(Params.resource, R.drawable.character_attack1));
        originalcharacterAttack.add(BitmapFactory.decodeResource(Params.resource, R.drawable.character_attack2));

        originalgoblinMove.add(goblin);
        originalgoblinAttack.add(goblin);
        originalnumbers[0]=BitmapFactory.decodeResource(Params.resource, R.drawable.zero);
        originalnumbers[1]=BitmapFactory.decodeResource(Params.resource, R.drawable.one );
        originalnumbers[2]=BitmapFactory.decodeResource(Params.resource, R.drawable.two);
        originalnumbers[3]=BitmapFactory.decodeResource(Params.resource, R.drawable.three);
        originalnumbers[4]=BitmapFactory.decodeResource(Params.resource, R.drawable.four);
        originalnumbers[5]=BitmapFactory.decodeResource(Params.resource, R.drawable.five);
        originalnumbers[6]=BitmapFactory.decodeResource(Params.resource, R.drawable.six);
        originalnumbers[7]=BitmapFactory.decodeResource(Params.resource, R.drawable.seven);
        originalnumbers[8]=BitmapFactory.decodeResource(Params.resource, R.drawable.eight);
        originalnumbers[9]=BitmapFactory.decodeResource(Params.resource, R.drawable.nine);
    }
    public static void changeSize()
    {
        dungeonFloor=Bitmap.createScaledBitmap(originaldungeonFloor,Params.size,Params.size,false);
        dungeonWall=Bitmap.createScaledBitmap(originaldungeonWall,Params.size,Params.size,false);
        character=Bitmap.createScaledBitmap(originalcharacter,Params.size,Params.size,false);
        goblin=Bitmap.createScaledBitmap(originalgoblin,Params.size,Params.size,false);
        ladderDown=Bitmap.createScaledBitmap(originalladderDown,Params.size,Params.size,false);
        ladderUp=Bitmap.createScaledBitmap(originalladderUp,Params.size,Params.size,false);

        for(int i=0;i< originalcharacterMove.size();i++) {
            characterMove.clear();
            characterMove.add(Bitmap.createScaledBitmap(originalcharacterMove.get(i), Params.size, Params.size, false));
        }
        for(int i=0;i< originalcharacterAttack.size();i++) {
            characterAttack.clear();
            characterAttack.add(Bitmap.createScaledBitmap(originalcharacterAttack.get(i), Params.size, Params.size, false));
        }
   //     coins=Bitmap.createScaledBitmap(coins,Params.size,Params.size,false);
        for(int i=0;i< originalgoblinMove.size();i++) {
            goblinMove.clear();
            goblinMove.add(Bitmap.createScaledBitmap(originalgoblinMove.get(i), Params.size, Params.size, false));
        }
        for(int i=0;i< originalgoblinAttack.size();i++) {
            goblinAttack.clear();
            goblinAttack.add(Bitmap.createScaledBitmap(originalgoblinAttack.get(i), Params.size, Params.size, false));
        }
        for(int i=0;i<10;i++) {
            numbers[i] = Bitmap.createScaledBitmap(originalnumbers[i], originalnumbers[i].getHeight() * Params.size / 64, originalnumbers[i].getWidth() * Params.size / 64, false);
        }

            miss = Bitmap.createScaledBitmap(originalmiss, originalmiss.getWidth() * Params.size / 64, originalmiss.getHeight() * Params.size / 64, false);
    }

    public static Bitmap getPictureById(int id)
    {
        switch (id)
        {
            case 1:
                return  dungeonFloor;
            case 2:
                return dungeonWall;
            case 3:
                return ladderDown;
            case 4:
                return ladderUp;
            case 101:
                return character;
            case 102:
                return goblin;
        }
        return null;
    }
    public static LinkedList<Bitmap> getMoveAnimationById(int id)
    {switch (id)
    {
        case 101:
            return characterMove;
        case 102:
            return goblinMove;
    }
        return null;

    }
    public static LinkedList<Bitmap> getAttackAnimationById(int id)
    {switch (id)
    {
        case 101:
            return characterAttack;
        case 102:
            return goblinAttack;
    }
        return null;

    }
}
