package com.example.framaz1.myapplication.Outstreams;

import android.content.Context;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.framaz1.myapplication.AllBitmaps;
import com.example.framaz1.myapplication.Game;
import com.example.framaz1.myapplication.Params;
import com.example.framaz1.myapplication.TouchAndThreadParams;

public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback {
    private DrawThread drawThread;
    public AnimationThread animThread;
    private int length=0;
    public MySurfaceView(Context context) {
        super(context);
        getHolder().addCallback(this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {


        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            TouchAndThreadParams.justClicked = true;
            TouchAndThreadParams.flastY = (int) event.getX();
            TouchAndThreadParams.flastX = (int) event.getY();
            TouchAndThreadParams.whenClicked=System.currentTimeMillis();
            Game.player.pathing.clear();
        } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
            {
                if(event.getPointerCount()==1) {
                    if (TouchAndThreadParams.justClicked == false || Math.abs(TouchAndThreadParams.flastY - event.getX()) > 3 || Math.abs(TouchAndThreadParams.flastX - event.getY()) > 3) {
                        TouchAndThreadParams.justClicked = false;
                        synchronized (TouchAndThreadParams.animationSync) {
                            if(TouchAndThreadParams.flastY==-1||
                            TouchAndThreadParams.flastX==-1)
                            {
                                TouchAndThreadParams.flastY=(int)event.getX();
                                TouchAndThreadParams.flastX=(int)event.getY();
                            }
                            Params.displayY += TouchAndThreadParams.flastY - (int)event.getX();
                            Params.displayX += TouchAndThreadParams.flastX - (int)event.getY();
                            TouchAndThreadParams.flastY = (int) event.getX();
                            TouchAndThreadParams.flastX = (int) event.getY();
                            if (Params.displayY < 0)
                                Params.displayY = 0;
                            if (Params.displayX < 0)
                                Params.displayX = 0;
                            if (Params.displayX > 100 * Params.size - Params.displaySettings.heightPixels + AllBitmaps.standartIconSize)
                                Params.displayX = 100 * Params.size - Params.displaySettings.heightPixels + AllBitmaps.standartIconSize;
                            if (Params.displayY > 100 * Params.size - Params.displaySettings.widthPixels)
                                Params.displayY = 100 * Params.size - Params.displaySettings.widthPixels;
                        }

                    }
                }
                if(event.getPointerCount()==2){
                    int newLength=0;
                    TouchAndThreadParams.flastY=-1;
                    TouchAndThreadParams.flastX=-1;
                    try {
                        newLength = (int) (Math.sqrt(event.getX(0) - event.getX(1) * (event.getX(0) - event.getX(1)) + (event.getY(0) - event.getY(1)) * (event.getY(0) - event.getY(1))));
                    }
                    catch (Exception e){
                        int asdasdgdfghfg=3;
                    }
                    finally {
                        if (length != 0 && Math.abs(length - newLength) > 10) {
                            int size1=Params.size;
                            Params.size += (newLength - length) / 10;
                            if(Params.size<32) Params.size=32;
                            if(Params.size>96) Params.size=96;
                            AllBitmaps.changeSize();
                            if(Params.size!=32&&Params.size!=96) {
                                Params.displayY += Params.displaySettings.widthPixels / 2;
                                Params.displayX += Params.displaySettings.heightPixels / 2;
                                Params.displayY += (newLength - length) / 10 * (Params.displayY) / size1;
                                Params.displayX += (newLength - length) / 10 * (Params.displayX) / size1;
                                Params.displayY -= Params.displaySettings.widthPixels / 2;
                                Params.displayX -= Params.displaySettings.heightPixels / 2;
                            }
                    //        Params.displayY-=(Params.size-size1)*Params.displaySettings.widthPixels/2/Params.size;
                   //         Params.displayX-=(Params.size-size1)*Params.displaySettings.heightPixels/2/Params.size;
                            if (Params.displayY < 0)
                                Params.displayY = 0;
                            if (Params.displayX < 0)
                                Params.displayX = 0;
                            if (Params.displayX > 100 * Params.size - Params.displaySettings.heightPixels + AllBitmaps.standartIconSize)
                                Params.displayX = 100 * Params.size - Params.displaySettings.heightPixels + AllBitmaps.standartIconSize;
                            if (Params.displayY > 100 * Params.size - Params.displaySettings.widthPixels)
                                Params.displayY = 100 * Params.size - Params.displaySettings.widthPixels;
                            length = newLength;
                        }
                        if (length == 0)
                            length = newLength;
                    }
                }
            }
            synchronized (TouchAndThreadParams.outStreamSync) {
                TouchAndThreadParams.outStreamSync.notifyAll();
                try {
                    TouchAndThreadParams.outStreamSync.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        if (event.getAction() == MotionEvent.ACTION_UP) {
            length=0;
            if(TouchAndThreadParams.justClicked&&System.currentTimeMillis()- TouchAndThreadParams.whenClicked<250) {
                Game.whereToGoX = TouchAndThreadParams.flastX;
                Game.whereToGoY = TouchAndThreadParams.flastY;
                synchronized (TouchAndThreadParams.gameSync) {
                    TouchAndThreadParams.gameSync.notifyAll();
                }
            }
        }

        return true;
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,int height) {
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        drawThread = new DrawThread(getHolder(), getResources());
        animThread=new AnimationThread(getHolder());
        drawThread.setRunning(true);
        drawThread.start();
        animThread.start();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        // завершаем работу потока
        drawThread.setRunning(false);
        while (retry) {
            try {
                drawThread.join();
                retry = false;
            } catch (InterruptedException e) {
                // если не получилось, то будем пытаться еще и еще
            }
        }
    }

}