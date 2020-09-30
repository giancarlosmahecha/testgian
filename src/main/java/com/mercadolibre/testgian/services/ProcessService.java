package com.mercadolibre.testgian.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadolibre.testgian.entities.Position;
import com.mercadolibre.testgian.entities.PositionResponse;
import com.mercadolibre.testgian.entities.Satellite;
import com.mercadolibre.testgian.entities.SatelliteWrapper;
import com.mercadolibre.testgian.exception.ApiRequestException;
import com.mercadolibre.testgian.interfaces.IProcess;
import com.mercadolibre.testgian.tools.Tools;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.awt.*;
import java.awt.geom.Point2D;

public class ProcessService implements IProcess {
    /* private Point2D kenobiSatellite= new Point2D.Double(-500.5d,-200);
     private Point2D skywalkerSatellite= new Point2D.Double(100,-100);
     private Point2D satoSatellite= new Point2D.Double(500,100);
     */


     private Point2D kenobiSatellite= new Point2D.Double(5d,4);
     private Point2D skywalkerSatellite= new Point2D.Double(4,-3);
     private Point2D satoSatellite= new Point2D.Double(-4,13);

    private double kenobiSkywalkerDistance;
    private double kenobiSatoDistance;
    private double skywalkerSatoDistance;

    private float kenobiMessageDistance;
    private float skywalkerMessageDistance;
    private float satoMessageDistance;

    private String[] kenobiMessage;
    private String[] skywalkerMessage;
    private String[] satoMessage;


    /**

     * @param satellitesInfo
     * @return PositionResponse {"position":{"x":100,"y":100},"message":"Esto es un mensaje secreto"}
     */
    public PositionResponse GetLocation(SatelliteWrapper satellitesInfo){

        Tools tools = new Tools();
        try{
            if(satellitesInfo.getSatellites().stream().count()>2) {
                //distancias entre satelites
                kenobiSkywalkerDistance = tools.getDistance(kenobiSatellite, skywalkerSatellite);
                kenobiSatoDistance = tools.getDistance(kenobiSatellite, satoSatellite);
                skywalkerSatoDistance = tools.getDistance(skywalkerSatellite, satoSatellite);
                //Iteramos las los satelites para obtener informacion de los satelites
                for (Satellite satellite : satellitesInfo.getSatellites()) {
                    if (satellite.getDistance() >= 0) {
                        switch (satellite.getName().toLowerCase()) {
                            case "kenobi":
                                kenobiMessageDistance = satellite.getDistance();
                                kenobiMessage = satellite.getMessage();
                                break;
                            case "skywalker":
                                skywalkerMessageDistance = satellite.getDistance();
                                skywalkerMessage = satellite.getMessage();
                                break;
                            case "sato":
                                satoMessageDistance = satellite.getDistance();
                                satoMessage = satellite.getMessage();
                                break;
                        }
                    } else {
                        throw new ApiRequestException("");
                    }
                }
                if(
                        (kenobiMessageDistance+skywalkerMessageDistance)<kenobiSkywalkerDistance||
                                (kenobiMessageDistance+satoMessageDistance)<kenobiSatoDistance ||
                                (skywalkerMessageDistance+satoMessageDistance)<skywalkerSatoDistance
                )
                {
                    throw new ApiRequestException("");
                }
               if(kenobiMessage.length==0||skywalkerMessage.length==0||satoMessage.length==0)
               {
                   throw new ApiRequestException("");
               }

            }
            else
            {
                throw new ApiRequestException("");

            }
        }catch (Exception ex)
        {
            throw new ApiRequestException("");
        }
        //Obtiene las cordenadas del emisor
        Point coordenates = tools.getCoordinateWith(kenobiSatellite,skywalkerSatellite,satoSatellite,kenobiMessageDistance,skywalkerMessageDistance,satoMessageDistance);

        PositionResponse objPositionResponse= new PositionResponse();
        Position objPosition = new Position();
        objPosition.setX((float) coordenates.getX());
        objPosition.setY((float) coordenates.getY());

        objPositionResponse.setPosition(objPosition);
        objPositionResponse.setMessage(tools.getMessage(kenobiMessage,skywalkerMessage,satoMessage));

        ObjectMapper parser = new ObjectMapper();
        try{
            String jsonObject=parser.writeValueAsString(objPositionResponse);
            //SendMessage(jsonObject);
        }catch (Exception ex)
        {

        }

        return  objPositionResponse;
    }



}
