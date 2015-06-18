package com.example.framaz1.myapplication;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.framaz1.myapplication.Outstreams.MySurfaceView;

/**
 * Created by framaz1 on 16.02.2015.
 */
public class MainGameActivity extends Activity {
        GameThread gameThread=new GameThread();
         Bitmap bitmap;
        public static MySurfaceView sf;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
            sf=new MySurfaceView(this);
            setContentView(sf);
            Display display = getWindowManager().getDefaultDisplay();
            display.getMetrics(Params.displaySettings);
            gameThread.start();
            sf.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Game.whereToGoX = TouchAndThreadParams.flastX;
                    Game.whereToGoY = TouchAndThreadParams.flastY;
                    synchronized (TouchAndThreadParams.gameSync)
                    {
                        TouchAndThreadParams.gameSync.notifyAll();
                    }
                }
            });
            }
        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.menu_main, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            int id = item.getItemId();

            //noinspection SimplifiableIfStatement
            if (id == R.id.action_settings) {
                return true;
            }

            return super.onOptionsItemSelected(item);
        }
    }
