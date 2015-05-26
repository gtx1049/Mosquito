package com.gtx.caculate;

/**
 * Created by Administrator on 2015/5/26.
 */
public class Caculate
{
    public static Point getArchimedesPoint(double angle, double a, double b)
    {
        Point ret = new Point(0, 0);

        double dx = a * Math.pow(angle, 1.0f / b) * Math.cos(angle);
        double dy = a * Math.pow(angle, 1.0f / b) * Math.sin(angle);

        ret.setX((int)dx);
        ret.setY((int)dy);

        return ret;
    }
}
