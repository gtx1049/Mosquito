package com.gtx.mosquito;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CursorTreeAdapter;
import android.widget.TextView;

import com.gtx.view.Mosquito;


public class MainActivity extends ActionBarActivity
{
    private Mosquito mosquito;
    private Handler handler;
    private Handler timeHandler;

    private TimerCount tc;
    private TextView tv;

    private int seconds = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //setContentView(new Mosquito(this));
        mosquito = (Mosquito)findViewById(R.id.mos);
        tv = (TextView)findViewById(R.id.timer);

        handler = new Handler(){

            @Override
            public void handleMessage(Message msg)
            {
                if(mosquito.getLen() > 0.05f)
                {
                    mosquito.setLen(mosquito.getLen() - 0.001f);
                    mosquito.setBurn(mosquito.getLen() + 0.001f);
                    mosquito.setAsh(25);
                    mosquito.invalidate();
                }
            }
        };

        timeHandler = new Handler(){
            @Override
            public void handleMessage(Message msg)
            {
                seconds++;
                tv.setText(seconds + " Seconds Pass ...");
            }
        };

        tc = new TimerCount();
        tc.start();

        new Seconds().start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public class TimerCount extends Thread
    {
        @Override
        public void run()
        {
            while(true)
            {
                handler.sendMessage(handler.obtainMessage());
                try
                {
                    Thread.sleep(1000);
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    public class Seconds extends Thread
    {
        public void run()
        {
            while(true)
            {
                timeHandler.sendMessage(timeHandler.obtainMessage());
                try
                {
                    Thread.sleep(1000);
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
}
