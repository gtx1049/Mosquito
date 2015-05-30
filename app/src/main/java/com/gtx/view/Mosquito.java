package com.gtx.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.gtx.caculate.Caculate;
import com.gtx.caculate.Point;
import com.gtx.mosquito.R;

/**
 * Created by Administrator on 2015/5/26.
 */
public class Mosquito extends View
{
    private Paint paint;
    private float ratio;
    private float radius;

    private float len;
    private float burn;
    private float ash;

    private DrawHandler handler;
    private Bitmap bitmap;

    public Mosquito(Context context)
    {
        super(context);
        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(3);

        handler = new DrawHandler();
    }

    public Mosquito(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(3);

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.Mosquito);

        radius = array.getFloat(R.styleable.Mosquito_radius, 1.0f);
        ratio = array.getFloat(R.styleable.Mosquito_ratio, 1.0f);

        Log.d("Mosquito", radius + "; radius");
        Log.d("Mosquito", ratio + " ; ratio");

        len = 24.995f;
        burn = 25f;
        ash = 25f;

        handler = new DrawHandler();
    }

    public Mosquito(Context context, AttributeSet attrs, Paint paint)
    {
        super(context, attrs);
        this.paint = paint;
    }

    public Mosquito(Context context, AttributeSet attrs, int defStyleAttr, Paint paint)
    {
        super(context, attrs, defStyleAttr);
        this.paint = paint;
    }

    protected void onDraw(Canvas canvas)
    {
        if(bitmap != null)
        {
            canvas.drawBitmap(bitmap, 0, 0, paint);
            //canvas.drawPath(path, paint);
            bitmap.recycle();
            bitmap = null;
        }
    }

    public void fresh()
    {
        new DrawThread().start();
    }

    public float getBurn()
    {
        return burn;
    }

    public void setBurn(float burn)
    {
        this.burn = burn;
    }

    public float getAsh()
    {
        return ash;
    }

    public void setAsh(float ash)
    {
        this.ash = ash;
    }

    public float getLen()
    {
        return len;
    }

    public void setLen(float len)
    {
        this.len = len;
    }

    public class DrawHandler extends Handler
    {
        @Override
        public void handleMessage(Message msg)
        {
            bitmap = (Bitmap)msg.obj;
            Mosquito.this.invalidate();
        }
    }

    public class DrawThread extends Thread
    {
        @Override
        public void run()
        {
            if(Mosquito.this.getWidth() > 0 && Mosquito.this.getHeight() > 0)
            {
                Bitmap localbitmap = Bitmap.createBitmap(Mosquito.this.getWidth(), Mosquito.this.getHeight(), Bitmap.Config.ARGB_8888);

                for (float t = 0f; t < len; t = t + 0.003f)
                {
                    for (float k = -15; k < 15; k = k + 0.5f)
                    {
                        Point p = Caculate.getArchimedesPoint(t, 12.0f, k);
                        //canvas.drawPoint(p.getX() + 350, p.getY() + 500, paint);
                        localbitmap.setPixel(p.getX() + 325, p.getY() + 350, Color.BLACK);
                    }
                }
                for (float t = len; t < burn; t = t + 0.003f)
                {
                    for (float k = -15; k < 15; k = k + 0.5f)
                    {
                        Point p = Caculate.getArchimedesPoint(t, 12.0f, k);
                        //canvas.drawPoint(p.getX() + 350, p.getY() + 500, paint);
                        localbitmap.setPixel(p.getX() + 325, p.getY() + 350, Color.RED);
                    }
                }
                for (float t = burn; t < ash; t = t + 0.003f)
                {
                    for (float k = -15; k < 15; k = k + 0.5f)
                    {
                        Point p = Caculate.getArchimedesPoint(t, 12.0f, k);
                        //canvas.drawPoint(p.getX() + 350, p.getY() + 500, paint);
                        localbitmap.setPixel(p.getX() + 325, p.getY() + 350, Color.GRAY);
                    }
                }

                Message msg = new Message();
                msg.obj = localbitmap;
                handler.sendMessage(msg);
            }
        }
    }
}
