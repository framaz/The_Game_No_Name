package com.example.framaz1.myapplication.Outstreams;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.SurfaceHolder;

import com.example.framaz1.myapplication.AllBitmaps;
import com.example.framaz1.myapplication.Creatures.MotherCreature;
import com.example.framaz1.myapplication.Game;
import com.example.framaz1.myapplication.Helpers.AttackHelper;
import com.example.framaz1.myapplication.Params;
import com.example.framaz1.myapplication.TouchAndThreadParams;

import java.util.LinkedList;

/**
 * Created by framaz1 on 27.03.2015.
 */
public class AnimationThread extends Thread {
    SurfaceHolder surfaceHolder;
    AttackHelper attackHelper;
    private int fromX,fromY;
    private MotherCreature whom;
    private int whatToDo;//1 двигаться, 2 - атаковать и т.д
    public AnimationThread(SurfaceHolder sf)
    {
        surfaceHolder=sf;
    }
    public void setMoveAnimation(MotherCreature character, int fX, int fY)
    {
        whatToDo=1;
        fromX=fX;
        fromY=fY;
        whom=character;
    }
    public void setAttackAnimation(AttackHelper helper)
    {
        attackHelper=helper;
        whatToDo=2;
    }
    @Override
    public void run()
    {
        Canvas canvas;
        synchronized (TouchAndThreadParams.animationSync) {
            try {
                TouchAndThreadParams.animationSync.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        while(true)
        {
            synchronized (TouchAndThreadParams.animationSync) {
                switch (whatToDo) {
                    case 1: {
                        long startTime = System.currentTimeMillis();
                        LinkedList<Bitmap> animation=AllBitmaps.getMoveAnimationById(whom.drawId);
                        while (System.currentTimeMillis() < startTime + 250) {
                        int startX=Params.displayX,startY=Params.displayY;
                        //    canvas = surfaceHolder.lockCanvas(null);
                        //    canvas.drawBitmap(Game.gamedepths[Game.layer].field[fromY][fromX].picture, fromX * Params.size - Params.displayY, fromY * Params.size - Params.displayX, null);
                        //    canvas.drawBitmap(Game.gamedepths[Game.layer].field[whom.yCoordinates][whom.xCoordinates].picture, whom.xCoordinates * Params.size - Params.displayY, whom.yCoordinates * Params.size - Params.displayX, null);
                        //      if(fromX<whom.xCoordinates)
                        //        canvas.drawBitmap(Game.gamedepths[Game.layer].field[fromY][fromX+1].picture, (fromX+1) * Params.size - Params.displayY, fromY * Params.size - Params.displayX, null);
                        //    if(fromX>whom.xCoordinates)
                        //        canvas.drawBitmap(Game.gamedepths[Game.layer].field[fromY][fromX-1].picture, (fromX-1) * Params.size - Params.displayY, fromY * Params.size - Params.displayX, null);
                        //    if(fromY<whom.xCoordinates)
                        //        canvas.drawBitmap(Game.gamedepths[Game.layer].field[fromY+1][fromX].picture, (fromX) * Params.size - Params.displayY, (fromY+1) * Params.size - Params.displayX, null);
                        //    if(fromY>whom.xCoordinates)
                        //        canvas.drawBitmap(Game.gamedepths[Game.layer].field[fromY-1][fromX].picture, (fromX) * Params.size - Params.displayY, (fromY-1) * Params.size - Params.displayX, null);
                        //    canvas.drawBitmap(Game.gamedepths[Game.layer].field[fromY][fromX+1].picture, (fromX+1) * Params.size - Params.displayY, (fromY) * Params.size - Params.displayX, null);
                        //    canvas.drawBitmap(Game.gamedepths[Game.layer].field[fromY+1][fromX+1].picture, (fromX+1) * Params.size - Params.displayY, (fromY+1) * Params.size - Params.displayX, null);
                        //    canvas.drawBitmap(Game.gamedepths[Game.layer].field[fromY+1][fromX].picture, (fromX) * Params.size - Params.displayY, (fromY+1) * Params.size - Params.displayX, null);
                        //    canvas.drawBitmap(Game.gamedepths[Game.layer].field[fromY+1][fromX-1].picture, (fromX-1) * Params.size - Params.displayY, (fromY+1) * Params.size - Params.displayX, null);
                        //    canvas.drawBitmap(Game.gamedepths[Game.layer].field[fromY][fromX-1].picture, (fromX-1) * Params.size - Params.displayY, (fromY) * Params.size - Params.displayX, null);
                        //    canvas.drawBitmap(Game.gamedepths[Game.layer].field[fromY-1][fromX-1].picture, (fromX-1) * Params.size - Params.displayY, (fromY-1) * Params.size - Params.displayX, null);
                        //    canvas.drawBitmap(Game.gamedepths[Game.layer].field[fromY-1][fromX].picture, (fromX) * Params.size - Params.displayY, (fromY-1) * Params.size - Params.displayX, null);
                        //    canvas.drawBitmap(Game.gamedepths[Game.layer].field[fromY-1][fromX+1].picture, (fromX+1) * Params.size - Params.displayY, (fromY-1) * Params.size - Params.displayX, null);/*
                        //
                        //    canvas.drawBitmap(Game.gamedepths[Game.layer].field[whom.yCoordinates][whom.xCoordinates+1].picture, (whom.xCoordinates+1) * Params.size - Params.displayY, (whom.yCoordinates) * Params.size - Params.displayX, null);
                        //    canvas.drawBitmap(Game.gamedepths[Game.layer].field[whom.yCoordinates+1][whom.xCoordinates+1].picture, (whom.xCoordinates+1) * Params.size - Params.displayY, (whom.yCoordinates+1) * Params.size - Params.displayX, null);
                        //    canvas.drawBitmap(Game.gamedepths[Game.layer].field[whom.yCoordinates+1][whom.xCoordinates].picture, (whom.xCoordinates) * Params.size - Params.displayY, (whom.yCoordinates+1) * Params.size - Params.displayX, null);
                        //    canvas.drawBitmap(Game.gamedepths[Game.layer].field[whom.yCoordinates+1][whom.xCoordinates-1].picture, (whom.xCoordinates-1) * Params.size - Params.displayY, (whom.yCoordinates+1) * Params.size - Params.displayX, null);
                        //    canvas.drawBitmap(Game.gamedepths[Game.layer].field[whom.yCoordinates][whom.xCoordinates-1].picture, (whom.xCoordinates-1) * Params.size - Params.displayY, (whom.yCoordinates) * Params.size - Params.displayX, null);
                        //    canvas.drawBitmap(Game.gamedepths[Game.layer].field[whom.yCoordinates-1][whom.xCoordinates-1].picture, (whom.xCoordinates-1) * Params.size - Params.displayY, (whom.yCoordinates-1) * Params.size - Params.displayX, null);
                        //    canvas.drawBitmap(Game.gamedepths[Game.layer].field[whom.yCoordinates-1][whom.xCoordinates].picture, (whom.xCoordinates) * Params.size - Params.displayY, (whom.yCoordinates-1) * Params.size - Params.displayX, null);
                        //    canvas.drawBitmap(Game.gamedepths[Game.layer].field[whom.yCoordinates-1][whom.xCoordinates+1].picture, (whom.xCoordinates+1) * Params.size - Params.displayY, (whom.yCoordinates-1) * Params.size - Params.displayX, null);
                        //
                        //    canvas.drawBitmap(whom.picture, fromX * Params.size - Params.displayY + (whom.xCoordinates - fromX) * Params.size * (System.currentTimeMillis() - startTime) / 250,
                        //            fromY * Params.size - Params.displayX + (whom.yCoordinates - fromY) * Params.size * (System.currentTimeMillis() - startTime) / 250, null);
                        //    surfaceHolder.unlockCanvasAndPost(canvas);

                            canvas = surfaceHolder.lockCanvas(null);
                            try {

                                canvas.drawColor(Color.BLACK);
                                Bitmap picture;
                                //Рисование тайлов+вычисления

                                canvas=TouchAndThreadParams.drawAllTilesOnTheField(canvas);

                                //Рисование персонажей

                                picture= AllBitmaps.getPictureById(Game.player.drawId);
                                if(whom.xCoordinates!=Game.player.xCoordinates||whom.yCoordinates!=Game.player.yCoordinates) {
                                    if(!Game.player.orientatedToRight)
                                        picture=Bitmap.createScaledBitmap(picture,-picture.getWidth(),picture.getHeight(),false);
                                    canvas.drawBitmap(picture, Game.player.xCoordinates * Params.size - Params.displayY, Game.player.yCoordinates * Params.size - Params.displayX, null);
                                }
                                for (int i = 0; i < Game.gamedepths[Game.layer].creatures.size(); i++) {
                                    MotherCreature ch = Game.gamedepths[Game.layer].creatures.get(i);
                                    picture = AllBitmaps.getPictureById(ch.drawId);
                                    if(whom.xCoordinates!=ch.xCoordinates||whom.yCoordinates!=ch.yCoordinates) {
                                        if(!ch.orientatedToRight)
                                            picture=Bitmap.createScaledBitmap(picture,-picture.getWidth(),picture.getHeight(),false);
                                        canvas.drawBitmap(picture, ch.xCoordinates * Params.size - Params.displayY, ch.yCoordinates * Params.size - Params.displayX, null);
                                    }
                                }

                                //Рисовка движущегося персонажа

                                long thisTime=System.currentTimeMillis();
                                long time=thisTime - startTime;
                                if(time<=250) {
                                    if(time==0) time=1;
                                    int i = (int) (time) /(255/ animation.size());
                                    picture=animation.get(i);
                                    if(whom.orientatedToRight==false)
                                        picture=Bitmap.createScaledBitmap(picture,-picture.getWidth(),picture.getHeight(),false);
                                    if(Game.player.drawId==whom.drawId)
                                    {
                                        if((fromX * Params.size - Params.displayY<100&&whom.xCoordinates - fromX<0)||(fromX * Params.size - Params.displayY>Params.displaySettings.widthPixels-100-Params.size&&whom.xCoordinates - fromX>0))
                                            Params.displayY=(int)(startY+(whom.xCoordinates - fromX) * Params.size * (time) / 250);
                                        if((fromY * Params.size - Params.displayX<100+AllBitmaps.standartIconSize&&whom.yCoordinates - fromY<0)||(fromY * Params.size - Params.displayX>Params.displaySettings.heightPixels-Params.size-AllBitmaps.waitIcon.getWidth()-100&&whom.yCoordinates - fromY>0))
                                            Params.displayX=(int)(startX+(whom.yCoordinates - fromY) * Params.size * (time) / 250);
                                        if (Params.displayY < 0)
                                            Params.displayY = 0;
                                        if (Params.displayX < -AllBitmaps.standartIconSize)
                                            Params.displayX = -AllBitmaps.standartIconSize;
                                        if (Params.displayX > 100 * Params.size - Params.displaySettings.heightPixels + AllBitmaps.standartIconSize)
                                            Params.displayX = 100 * Params.size - Params.displaySettings.heightPixels + AllBitmaps.standartIconSize;
                                        if (Params.displayY > 100 * Params.size - Params.displaySettings.widthPixels)
                                            Params.displayY = 100 * Params.size - Params.displaySettings.widthPixels;
                                    }
                                    canvas.drawBitmap(picture, fromX * Params.size - Params.displayY + (whom.xCoordinates - fromX) * Params.size * (time) / 250,
                                            fromY * Params.size - Params.displayX + (whom.yCoordinates - fromY) * Params.size * (time) / 250, null);

                                }
                                else {
                                    picture=AllBitmaps.getPictureById(whom.drawId);
                                    if (whom.orientatedToRight == false)
                                        picture = Bitmap.createScaledBitmap(picture, -picture.getWidth(), picture.getHeight(), false);
                                    canvas.drawBitmap(picture, fromX * Params.size - Params.displayY + (whom.xCoordinates - fromX) * Params.size,
                                            fromY * Params.size - Params.displayX + (whom.yCoordinates - fromY) * Params.size, null);
                                }

                                //Рисование меню

                                canvas=TouchAndThreadParams.drawMenu(canvas);

                            } finally {
                                if (canvas != null) {
                                    // отрисовка выполнена. выводим результат на экран
                                    surfaceHolder.unlockCanvasAndPost(canvas);
                                }
                            }
                        }
                    }
                    break;
                    case 2:

                        //First part(Painting hitAnimation)

                        long startTime=System.currentTimeMillis();
                        LinkedList<Bitmap> animation=AllBitmaps.getAttackAnimationById(attackHelper.attacker.drawId);
                        while (System.currentTimeMillis() < startTime + 250) {
                            canvas = surfaceHolder.lockCanvas(null);
                            long thisTime=System.currentTimeMillis();
                            long time=thisTime - startTime;
                            canvas.drawColor(Color.BLACK);
                            Bitmap picture;
                            //Рисование тайлов+вычисления
                            whom=attackHelper.attacker;
                            canvas=TouchAndThreadParams.drawAllTilesOnTheField(canvas);

                            //Рисование персонажей

                            picture= AllBitmaps.getPictureById(Game.player.drawId);
                            if(whom.xCoordinates!=Game.player.xCoordinates||whom.yCoordinates!=Game.player.yCoordinates) {
                                if(!Game.player.orientatedToRight)
                                    picture=Bitmap.createScaledBitmap(picture,-picture.getWidth(),picture.getHeight(),false);
                                canvas.drawBitmap(picture, Game.player.xCoordinates * Params.size - Params.displayY, Game.player.yCoordinates * Params.size - Params.displayX, null);
                            }
                            for (int i = 0; i < Game.gamedepths[Game.layer].creatures.size(); i++) {
                                MotherCreature ch = Game.gamedepths[Game.layer].creatures.get(i);
                                picture = AllBitmaps.getPictureById(ch.drawId);
                                if(whom.xCoordinates!=ch.xCoordinates||whom.yCoordinates!=ch.yCoordinates) {
                                    if(!ch.orientatedToRight)
                                        picture=Bitmap.createScaledBitmap(picture,-picture.getWidth(),picture.getHeight(),false);
                                    canvas.drawBitmap(picture, ch.xCoordinates * Params.size - Params.displayY, ch.yCoordinates * Params.size - Params.displayX, null);
                                }
                            }
                            if(time<=250) {
                                int i = (int) (time) /(255/ animation.size());
                                picture=animation.get(i);
                                if(!attackHelper.attacker.orientatedToRight)
                                    picture=Bitmap.createScaledBitmap(picture,-picture.getWidth(),picture.getHeight(),false);
                                canvas.drawBitmap(picture, attackHelper.attacker.xCoordinates * Params.size - Params.displayY,
                                        attackHelper.attacker.yCoordinates * Params.size - Params.displayX , null);
                            }
                            else {
                                picture=AllBitmaps.getPictureById(attackHelper.attacker.drawId);
                                if (attackHelper.attacked.orientatedToRight)
                                    picture = Bitmap.createScaledBitmap(picture, -picture.getWidth(), picture.getHeight(), false);
                                canvas.drawBitmap(picture, attackHelper.attacker.xCoordinates * Params.size - Params.displayY,
                                        attackHelper.attacker.yCoordinates * Params.size - Params.displayX, null);
                            }

                            canvas=TouchAndThreadParams.drawMenu(canvas);

                            surfaceHolder.unlockCanvasAndPost(canvas);

                        }

                    //Second Part (Conclusion)

                        //Preparings

                        Bitmap picture;
                        LinkedList<Bitmap> toOut=new LinkedList<>();
                        if(attackHelper.missed)
                            toOut.add(AllBitmaps.miss);
                        else
                        {
                            String temp=Integer.toString(attackHelper.damage);
                            toOut=TouchAndThreadParams.textToPicture(temp);
                        }
                        startTime=System.currentTimeMillis();
                        int widthOfStatus=0;
                        for(int i=0;i<toOut.size();i++)
                            widthOfStatus+=toOut.get(i).getWidth();
                        int whereToDrawX=attackHelper.attacked.xCoordinates*Params.size+Params.size/2-Params.displayY-widthOfStatus/2;
                        boolean reversed=attackHelper.attacked.yCoordinates<=1;
                        if(whereToDrawX<0)
                            whereToDrawX=0;
                        if(whereToDrawX>100 * Params.size-widthOfStatus)
                            whereToDrawX=100 * Params.size-widthOfStatus-1;
                        int whereToDrawY;
                        if(!reversed) {
                            whereToDrawY = (attackHelper.attacked.yCoordinates) * Params.size-toOut.get(0).getHeight()-Params.displayX;
                        }
                        else
                            whereToDrawY = (attackHelper.attacked.yCoordinates+1) * Params.size-Params.displayX;
                        int howManyToGo=2*Params.size-toOut.get(0).getHeight();
                        howManyToGo/=2;
                        if(reversed)
                            howManyToGo=-howManyToGo;

                        //Drawings

                        while (System.currentTimeMillis() < startTime + 750) {
                            canvas = surfaceHolder.lockCanvas(null);

                            try {
                                canvas.drawColor(Color.BLACK);

                                //Рисование тайлов+вычисления

                                canvas = TouchAndThreadParams.drawAllTilesOnTheField(canvas);

                                //Рисование персонажей

                                picture = AllBitmaps.getPictureById(Game.player.drawId);
                                if (!Game.player.orientatedToRight)
                                    picture = Bitmap.createScaledBitmap(picture, -picture.getWidth(), picture.getHeight(), false);
                                canvas.drawBitmap(picture, Game.player.xCoordinates * Params.size - Params.displayY, Game.player.yCoordinates * Params.size - Params.displayX, null);
                                for (int i = 0; i < Game.gamedepths[Game.layer].creatures.size(); i++) {
                                    MotherCreature ch = Game.gamedepths[Game.layer].creatures.get(i);
                                    picture = AllBitmaps.getPictureById(ch.drawId);
                                    if (!ch.orientatedToRight)
                                        picture = Bitmap.createScaledBitmap(picture, -picture.getWidth(), picture.getHeight(), false);
                                    canvas.drawBitmap(picture, ch.xCoordinates * Params.size - Params.displayY, ch.yCoordinates * Params.size - Params.displayX, null);
                                }

                                //Рисование меню

                                canvas = TouchAndThreadParams.drawMenu(canvas);
                                int doneIdentifier = 0;
                                for (int i = 0; i < toOut.size(); i++) {
                                    double time = System.currentTimeMillis() - startTime;
                                    canvas.drawBitmap(toOut.get(i), doneIdentifier + whereToDrawX, whereToDrawY - (int)((time / 750) * howManyToGo), null);
                                    doneIdentifier += toOut.get(i).getWidth();
                                }
                            } finally {
                                if (canvas != null) {
                                    surfaceHolder.unlockCanvasAndPost(canvas);
                                }
                            }
                        }
                        break;
                }
                TouchAndThreadParams.animationSync.notifyAll();
                try {
                    TouchAndThreadParams.animationSync.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

}
