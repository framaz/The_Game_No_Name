package com.example.framaz1.myapplication.Outstreams;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.SurfaceHolder;

import com.example.framaz1.myapplication.AllBitmaps;
import com.example.framaz1.myapplication.Creatures.MotherCreature;
import com.example.framaz1.myapplication.Game;
import com.example.framaz1.myapplication.Params;
import com.example.framaz1.myapplication.R;
import com.example.framaz1.myapplication.TouchAndThreadParams;

class DrawThread extends Thread {
    private boolean runFlag = false;
    public SurfaceHolder surfaceHolder;
    private Bitmap picture;
    private long prevTime;

    public DrawThread(SurfaceHolder surfaceHolder, Resources resources) {
        this.surfaceHolder = surfaceHolder;
        picture = BitmapFactory.decodeResource(resources, R.drawable.tile);
        prevTime = System.currentTimeMillis();
    }

    public void setRunning(boolean run) {
        runFlag = run;
    }

    @Override
    public void run() {
        Canvas canvas;
        setPriority(Thread.MAX_PRIORITY);
        setName("Thread_of_outstream");
        synchronized (TouchAndThreadParams.outStreamSync) {
            try {
                TouchAndThreadParams.outStreamSync.notifyAll();
                TouchAndThreadParams.outStreamSync.wait(2000);
            } catch (Exception e) {
            }
        }
        while (runFlag) {
            // получаем текущее время и вычисляем разницу с предыдущим
            // сохраненным моментом времени
            synchronized (surfaceHolder) {
                // получаем объект Canvas и выполняем отрисовку
                canvas = surfaceHolder.lockCanvas(null);
                try {
                    canvas.drawColor(Color.BLACK);

                    //Рисование тайлов+вычисления

                    canvas=TouchAndThreadParams.drawAllTilesOnTheField(canvas);

                    //Рисование персонажей

                    picture= AllBitmaps.getPictureById(Game.player.drawId);
                    if(!Game.player.orientatedToRight)
                        picture=Bitmap.createScaledBitmap(picture,-picture.getWidth(),picture.getHeight(),false);
                    canvas.drawBitmap(picture, Game.player.xCoordinates * Params.size - Params.displayY, Game.player.yCoordinates * Params.size - Params.displayX, null);
                    for (int i = 0; i < Game.gamedepths[Game.layer].creatures.size(); i++) {
                        MotherCreature ch = Game.gamedepths[Game.layer].creatures.get(i);
                        picture = AllBitmaps.getPictureById(ch.drawId);
                        if(!ch.orientatedToRight)
                            picture=Bitmap.createScaledBitmap(picture,-picture.getWidth(),picture.getHeight(),false);
                        canvas.drawBitmap(picture, ch.xCoordinates * Params.size - Params.displayY, ch.yCoordinates * Params.size - Params.displayX, null);
                    }

                    //Рисование меню

                    canvas=TouchAndThreadParams.drawMenu(canvas);

                    //Рисование инвентаря и т.д.

                    if(Params.inventory)
                    {
                        canvas=TouchAndThreadParams.drawInventory(canvas);
                    }
                    if(Params.item)
                    {
                        canvas=TouchAndThreadParams.drawItem(canvas);
                    }
                    if(Params.stats)
                    {
                        canvas=TouchAndThreadParams.drawStats(canvas);
                    }
                } finally {
                    if (canvas != null) {
                        // отрисовка выполнена. выводим результат на экран
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    }
                }
                synchronized (TouchAndThreadParams.outStreamSync) {
                    try {
                        TouchAndThreadParams.outStreamSync.notifyAll();
                        TouchAndThreadParams.outStreamSync.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
