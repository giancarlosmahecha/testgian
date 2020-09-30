package com.mercadolibre.testgian.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadolibre.testgian.daos.SatelliteRepository;
import com.mercadolibre.testgian.entities.PositionResponse;
import com.mercadolibre.testgian.entities.Satellite;
import com.mercadolibre.testgian.entities.SatelliteWrapper;
import com.mercadolibre.testgian.entities.Satellites;
import com.mercadolibre.testgian.exception.ApiRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

//TopSecretSplit
@RestController
@ControllerAdvice
@RequestMapping("/")
public class TopSecretSplitController {
    @Value("${topsecretapi}")
    private String topSecretApi;
    @Autowired
    private SatelliteRepository satelliteRepository;

    Flux<PositionResponse> positionResponse;
    /*public TopSecretController()
    {
        process= new ProcessService();
    }*/
    @RequestMapping(path="/topsecret_split/{satellite_name}",method = RequestMethod.GET)
    public @ResponseBody Flux<PositionResponse> TopSecretSplit(@PathVariable("satellite_name") String satellite_name, @RequestBody Satellite satellites) {

        String satel=Satellites.kenobi.toString();
            if(satellite_name.toLowerCase().equals(Satellites.kenobi.toString().toLowerCase()) ||
                    satellite_name.toLowerCase().equals( Satellites.skywalker.toString().toLowerCase()) ||
                    satellite_name.toLowerCase().equals(Satellites.sato.toString())
            )
            {
                satellites.setName(satellite_name);
                satelliteRepository.save(satellites);
                List<Satellite> listSatellites = satelliteRepository.findAll();
                Satellite objSatelliteKenobi = (Satellite) listSatellites.stream().filter(satellite -> "kenobi".equals(satellite.getName())).findAny().orElse(null);
                Satellite objSatelliteSkywalker = (Satellite) listSatellites.stream().filter(satellite -> "skywalker".equals(satellite.getName())).findAny().orElse(null);
                Satellite objSatelliteSato = (Satellite) listSatellites.stream().filter(satellite -> "sato".equals(satellite.getName())).findAny().orElse(null);
                if (objSatelliteKenobi == null || objSatelliteSkywalker == null || objSatelliteSato == null) {
                    throw new ApiRequestException("not enough information");
                } else {
                    String jsonStr;
                    SatelliteWrapper objSatelliteWrapper = new SatelliteWrapper();
                    List<Satellite> listSatellitesRequest = new ArrayList<Satellite>();
                    listSatellitesRequest.add(objSatelliteKenobi);
                    listSatellitesRequest.add(objSatelliteSkywalker);
                    listSatellitesRequest.add(objSatelliteSato);
                    objSatelliteWrapper.setSatellites(listSatellitesRequest);
                    ObjectMapper objJson;
                    objJson = new ObjectMapper();
                    try{
                        jsonStr = objJson.writeValueAsString(objSatelliteWrapper);
                    }catch (Exception e)
                    {
                        throw new ApiRequestException("could not map request body");
                    }

                    //System.out.println(jsonStr);
                    positionResponse = WebClient.create()
                            .post()
                            .uri(topSecretApi)
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(BodyInserters.fromObject(jsonStr))
                            .retrieve()

                            .bodyToFlux(PositionResponse.class);

                    return positionResponse;
                }

            }else {
                throw new ApiRequestException("satellite is not configured");
            }



    }
    @RequestMapping(path="/topsecret_split/{satellite_name}",method = RequestMethod.POST)
    public @ResponseBody Flux<PositionResponse> TopSecretSplitPost(@PathVariable("satellite_name") String satellite_name, @RequestBody Satellite satellites) {


        if(satellite_name.toLowerCase().equals(Satellites.kenobi.toString().toLowerCase()) ||
                satellite_name.toLowerCase().equals( Satellites.skywalker.toString().toLowerCase()) ||
                satellite_name.toLowerCase().equals(Satellites.sato.toString())
        )
        {
            satellites.setName(satellite_name);
            satelliteRepository.save(satellites);
            List<Satellite> listSatellites = satelliteRepository.findAll();
            Satellite objSatelliteKenobi = (Satellite) listSatellites.stream().filter(satellite -> "kenobi".equals(satellite.getName())).findAny().orElse(null);
            Satellite objSatelliteSkywalker = (Satellite) listSatellites.stream().filter(satellite -> "skywalker".equals(satellite.getName())).findAny().orElse(null);
            Satellite objSatelliteSato = (Satellite) listSatellites.stream().filter(satellite -> "sato".equals(satellite.getName())).findAny().orElse(null);
            if (objSatelliteKenobi == null || objSatelliteSkywalker == null || objSatelliteSato == null) {
                throw new ApiRequestException("not enough information");
            } else {
                String jsonStr;
                SatelliteWrapper objSatelliteWrapper = new SatelliteWrapper();
                List<Satellite> listSatellitesRequest = new ArrayList<Satellite>();
                listSatellitesRequest.add(objSatelliteKenobi);
                listSatellitesRequest.add(objSatelliteSkywalker);
                listSatellitesRequest.add(objSatelliteSato);
                objSatelliteWrapper.setSatellites(listSatellitesRequest);
                ObjectMapper objJson;
                objJson = new ObjectMapper();
                try{
                    jsonStr = objJson.writeValueAsString(objSatelliteWrapper);
                }catch (Exception e)
                {
                    throw new ApiRequestException("could not map request body");
                }

                //System.out.println(jsonStr);
                positionResponse = WebClient.create()
                        .post()
                        .uri(topSecretApi)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromObject(jsonStr))
                        .retrieve()

                        .bodyToFlux(PositionResponse.class);

                return positionResponse;
            }

        }else {
            throw new ApiRequestException("satellite is not configured");
        }



    }
}
