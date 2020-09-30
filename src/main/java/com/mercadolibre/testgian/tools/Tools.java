package com.mercadolibre.testgian.tools;

import com.mercadolibre.testgian.entities.PositionResponse;
import com.mercadolibre.testgian.entities.Satellite;
import com.mercadolibre.testgian.entities.SatelliteWrapper;

import java.awt.*;
import java.awt.geom.Point2D;

public class Tools {

    public double getDistance(Point2D pointA, Point2D pointB ){
        double distance=0;
        double pointX1=pointA.getX();
        double pointY1=pointA.getY();
        double pointX2=pointB.getX();
        double pointY2=pointB.getY();
        distance= (float) Math.sqrt(Math.pow((pointX2-pointX1),2)+Math.pow((pointY2-pointY1),2));


        return distance;

    }

    public Point getCoordinateWith(Point2D point1, Point2D point2, Point2D point3,double dA,double dB,double dC) {
        double W, Z, x, y, y2;
        W = dA*dA - dB*dB - point1.getX()*point1.getX() - point1.getY()*point1.getY() + point2.getX()*point2.getX() + point2.getY()*point2.getY();
        Z = dB*dB - dC*dC - point2.getX()*point2.getX() - point2.getY()*point2.getY() + point3.getX()*point3.getX() + point3.getY()*point3.getY();

        x = (W*(point3.getY()-point2.getY()) - Z*(point2.getY()-point1.getY())) / (2 * ((point2.getX()-point1.getX())*(point3.getY()-point2.getY()) - (point3.getX()-point2.getX())*(point2.getY()-point1.getY())));
        y = (W - 2*x*(point2.getX()-point1.getX())) / (2*(point2.getY()-point1.getY()));
        //y2 is a second measure of y to mitigate errors
        y2 = (Z - 2*x*(point3.getX()-point2.getX())) / (2*(point3.getY()-point2.getY()));
        y = (y + y2) / 2;

        Point coordenates= new Point();
        coordenates.setLocation(x,y);

        return  coordenates;
    }

    public String getMessage(String[] message1,String[] message2,String[] message3){
        String messageResponse="";
        Integer maxArraysLen=0;
        
        if(message1.length>message2.length)
            if(message1.length>message3.length)
                maxArraysLen=message1.length;
            else
                maxArraysLen=message3.length;
        else
            if(message2.length>message3.length)
                maxArraysLen=message2.length;
            else
                maxArraysLen=message3.length;
        String[] messageArr= new String[maxArraysLen];
        for(Integer i=0;i<=maxArraysLen;i++)
        {
            if(message1.length>i)
                if(message1[i] !="")
                    messageArr[i]=message1[i];
            if(message2.length>i)
                if(message2[i] !="")
                    messageArr[i]=message2[i];
            if(message3.length>i )
                if(message3[i] !="")
                    messageArr[i]=message3[i];
        }
        for (String messagePart :messageArr
             ) {
            messageResponse+= messagePart+" ";

        }
        return messageResponse.trim();
    }


}



