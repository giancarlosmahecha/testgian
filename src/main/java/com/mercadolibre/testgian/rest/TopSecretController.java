package com.mercadolibre.testgian.rest;

import com.mercadolibre.testgian.entities.PositionResponse;
import com.mercadolibre.testgian.entities.SatelliteWrapper;
import com.mercadolibre.testgian.interfaces.IProcess;
import com.mercadolibre.testgian.entities.Position;
import com.mercadolibre.testgian.entities.Satellite;
import com.mercadolibre.testgian.services.ProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
//import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/")
public class TopSecretController {

    //private QueueMessagingTemplate queueMessagingTemplate;

    //@Value("${cloud.aws.end-point.uri}")
    //private String sqsEndPoint;
    IProcess process;
    public TopSecretController()
    {
        process= new ProcessService();
    }
    @RequestMapping(value = "topsecret", method = RequestMethod.POST)
    public @ResponseBody PositionResponse TopSecret(@RequestBody SatelliteWrapper satellites)
    {
        try{

            return process.GetLocation(satellites);
        }catch (Exception ex)
        {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,""
            );
        }

    }

}
