package com.gtx.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
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

    public Mosquito(Context context)
    {
        super(context);
        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(3);
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
        Bitmap bitmap = Bitmap.createBitmap(this.getWidth(), this.getHeight(), Bitmap.Config.ARGB_8888);

        for(float t = 0; t < 25; t = t + 0.003f)
        {
            for(float k = -10; k < 10; k = k + 0.5f)
            {
                Point p = Caculate.getArchimedesPoint(t, 12.0f, k);
                //canvas.drawPoint(p.getX() + 350, p.getY() + 500, paint);
                bitmap.setPixel(p.getX() + 325, p.getY() + 350, Color.BLACK);
            }

        }

        canvas.drawBitmap(bitmap, 0, 0, paint);
        //canvas.drawPath(path, paint);
    }
}
