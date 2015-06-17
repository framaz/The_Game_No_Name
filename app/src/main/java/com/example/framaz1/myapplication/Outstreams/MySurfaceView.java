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
        } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
            {
                    if (TouchAndThreadParams.justClicked == false || Math.abs(TouchAndThreadParams.flastY - event.getX()) > 10 || Math.abs(TouchAndThreadParams.flastX - event.getY()) > 10) {
                        TouchAndThreadParams.justClicked = false;
                        synchronized (TouchAndThreadParams.animationSync) {
                            Params.displayY += TouchAndThreadParams.flastY - event.getX();
                            Params.displayX += TouchAndThreadParams.flastX - event.getY();
                            TouchAndThreadParams.flastY = (int) event.getX();
                            TouchAndThreadParams.flastX = (int) event.getY();
                            if (Params.displayY < 0)
                                Params.displayY = 0;
                            if (Params.displayX < 0)
                                Params.displayX = 0;
                            if (Params.displayX > 100 * Params.size - Params.displaySettings.heightPixels+AllBitmaps.standartIconSize+38)
                                Params.displayX = 100 * Params.size - Params.displaySettings.heightPixels+AllBitmaps.standartIconSize+38;
                            if (Params.displayY > 100 * Params.size - Params.displaySettings.widthPixels)
                                Params.displayY = 100 * Params.size - Params.displaySettings.widthPixels;
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
        if (event.getAction() == MotionEvent.ACTION_UP) {
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