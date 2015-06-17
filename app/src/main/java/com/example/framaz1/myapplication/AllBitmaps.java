package com.example.framaz1.myapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.LinkedList;

/**
 * Created by framaz1 on 22.03.2015.
 */
public class AllBitmaps {
    private static Bitmap dungeonFloor= BitmapFactory.decodeResource(Params.resource,R.drawable.tile); // 1
    private static Bitmap dungeonWall= BitmapFactory.decodeResource(Params.resource,R.drawable.wall); // 2
    private static Bitmap ladderDown = BitmapFactory.decodeResource(Params.resource,R.drawable.ladder_down); // 3
    private static Bitmap ladderUp = BitmapFactory.decodeResource(Params.resource,R.drawable.ladder_up); //4

    //Character

    private static Bitmap character = BitmapFactory.decodeResource(Params.resource, R.drawable.character); //101
    private static LinkedList<Bitmap> characterMove =new LinkedList<>();
    private static LinkedList<Bitmap> characterAttack = new LinkedList<>();

    //Goblin
    private static Bitmap goblin= BitmapFactory.decodeResource(Params.resource,R.drawable.goblin); // 102
    private static LinkedList<Bitmap> goblinMove =new LinkedList<>();
    private static LinkedList<Bitmap> goblinAttack =new LinkedList<>();

    public static int standartIconSize=64;
    public static Bitmap woodenSword=BitmapFactory.decodeResource(Params.resource,R.drawable.wooden_sword);
    public static Bitmap waitIcon=Bitmap.createScaledBitmap(BitmapFactory.decodeResource(Params.resource,R.drawable.wait_icon),standartIconSize,standartIconSize,false);
    public static Bitmap magicIcon=Bitmap.createScaledBitmap(BitmapFactory.decodeResource(Params.resource,R.drawable.magic_icon),standartIconSize,standartIconSize,false);
    public static Bitmap inventoryImage=BitmapFactory.decodeResource(Params.resource,R.drawable.inventory);
    public static Bitmap itemView=BitmapFactory.decodeResource(Params.resource,R.drawable.item_view);
    public static Bitmap inventoryIcon=Bitmap.createScaledBitmap(BitmapFactory.decodeResource(Params.resource,R.drawable.inventory_icon),standartIconSize,standartIconSize,false);
    public static Bitmap equip= BitmapFactory.decodeResource(Params.resource,R.drawable.equip);
    public static Bitmap unequip= BitmapFactory.decodeResource(Params.resource,R.drawable.unequip);
    public static Bitmap drop= BitmapFactory.decodeResource(Params.resource,R.drawable.drop);
    public static Bitmap use= BitmapFactory.decodeResource(Params.resource,R.drawable.use);
    public static Bitmap coins= BitmapFactory.decodeResource(Params.resource,R.drawable.coins);
    public static Bitmap getItemHere = BitmapFactory.decodeResource(Params.resource,R.drawable.get_item_here);
    public static Bitmap miss = BitmapFactory.decodeResource(Params.resource,R.drawable.miss);

    //Numbers

    public static Bitmap[] numbers=new Bitmap[10];


    public static void initialize()
    {
        characterMove.add(BitmapFactory.decodeResource(Params.resource, R.drawable.character_move1));
        characterMove.add(BitmapFactory.decodeResource(Params.resource, R.drawable.character_move2));
        characterAttack.add(BitmapFactory.decodeResource(Params.resource, R.drawable.character_attack1));
        characterAttack.add(BitmapFactory.decodeResource(Params.resource, R.drawable.character_attack2));

        goblinMove.add(goblin);
        goblinAttack.add(goblin);

        numbers[0]=BitmapFactory.decodeResource(Params.resource, R.drawable.zero);
        numbers[1]=BitmapFactory.decodeResource(Params.resource, R.drawable.one);
        numbers[2]=BitmapFactory.decodeResource(Params.resource, R.drawable.two);
        numbers[3]=BitmapFactory.decodeResource(Params.resource, R.drawable.three);
        numbers[4]=BitmapFactory.decodeResource(Params.resource, R.drawable.four);
        numbers[5]=BitmapFactory.decodeResource(Params.resource, R.drawable.five);
        numbers[6]=BitmapFactory.decodeResource(Params.resource, R.drawable.six);
        numbers[7]=BitmapFactory.decodeResource(Params.resource, R.drawable.seven);
        numbers[8]=BitmapFactory.decodeResource(Params.resource, R.drawable.eight);
        numbers[9]=BitmapFactory.decodeResource(Params.resource, R.drawable.nine);
    }
    public static void changeSize()
    {
        dungeonFloor=Bitmap.createScaledBitmap(dungeonFloor,Params.size,Params.size,false);
        dungeonWall=Bitmap.createScaledBitmap(dungeonWall,Params.size,Params.size,false);
        character=Bitmap.createScaledBitmap(character,Params.size,Params.size,false);
        goblin=Bitmap.createScaledBitmap(goblin,Params.size,Params.size,false);
        ladderDown=Bitmap.createScaledBitmap(ladderDown,Params.size,Params.size,false);
        ladderUp=Bitmap.createScaledBitmap(ladderUp,Params.size,Params.size,false);

        for(int i=0;i< characterMove.size();i++)
            characterMove.add(Bitmap.createScaledBitmap(characterMove.poll(),Params.size,Params.size,false));
        for(int i=0;i< characterAttack.size();i++)
            characterAttack.add(Bitmap.createScaledBitmap(characterAttack.poll(),Params.size,Params.size,false));
   //     coins=Bitmap.createScaledBitmap(coins,Params.size,Params.size,false);
        for(int i=0;i< goblinMove.size();i++)
            goblinMove.add(Bitmap.createScaledBitmap(goblinMove.poll(),Params.size,Params.size,false));
        for(int i=0;i< goblinAttack.size();i++)
            goblinAttack.add(Bitmap.createScaledBitmap(goblinAttack.poll(),Params.size,Params.size,false));
        for(int i=0;i<10;i++)
            numbers[i]=Bitmap.createScaledBitmap(numbers[i],numbers[i].getHeight()*Params.size/64,numbers[i].getWidth()*Params.size/64,false);
        miss=Bitmap.createScaledBitmap(miss,miss.getWidth()*Params.size/64,miss.getHeight()*Params.size/64,false);
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
