package com.example.framaz1.myapplication;


public class GameThread extends Thread{

    @Override
    public void run()
    {
        setName("GameThread");
        Game.gaming();
    }
}
