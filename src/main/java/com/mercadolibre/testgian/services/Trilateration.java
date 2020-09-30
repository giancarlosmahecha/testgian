package com.mercadolibre.testgian.services;

import java.awt.*;

public class Trilateration {

    public String getCoordinateWith(
            Point a, Point b, Point c,
            double dA,
            double dB,
            double dC) {
        double W, Z, x, y, y2;
        W = dA*dA - dB*dB - a.getX()*a.getX() - a.getY()*a.getY() + b.getX()*b.getX() + b.getY()*b.getY();
        Z = dB*dB - dC*dC - b.getX()*b.getX() - b.getY()*b.getY() + c.getX()*c.getX() + c.getY()*c.getY();

        x = (W*(c.getY()-b.getY()) - Z*(b.getY()-a.getY())) / (2 * ((b.getX()-a.getX())*(c.getY()-b.getY()) - (c.getX()-b.getX())*(b.getY()-a.getY())));
        y = (W - 2*x*(b.getX()-a.getX())) / (2*(b.getY()-a.getY()));
        //y2 is a second measure of y to mitigate errors
        y2 = (Z - 2*x*(c.getX()-b.getX())) / (2*(c.getY()-b.getY()));
        y = (y + y2) / 2;
        return "Position: " + x + " , " + y;
    }

}
