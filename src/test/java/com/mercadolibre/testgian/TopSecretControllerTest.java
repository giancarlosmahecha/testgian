package com.mercadolibre.testgian;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadolibre.testgian.daos.SatelliteRepository;
import com.mercadolibre.testgian.entities.Satellite;
import com.mercadolibre.testgian.entities.SatelliteWrapper;
import com.mercadolibre.testgian.rest.TopSecretController;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;

import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
public class TopSecretControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private TopSecretController topSecretController;

    @Before
    public  void setUp() throws Exception{
        mockMvc= MockMvcBuilders.standaloneSetup(topSecretController).build();
    }



    @Test
    public void TopSecret() throws Exception
    {

        List<Satellite> listSatellite = new ArrayList<Satellite>();
        Satellite satelliteKenobi = new Satellite();
        SatelliteWrapper satelliteWrapp = new SatelliteWrapper();
        satelliteKenobi.setName("kenobi");
        satelliteKenobi.setDistance(1000.0f);
        String[] arrMessagekenobi= {"este","","","mensaje",""};
        satelliteKenobi.setMessage(arrMessagekenobi);
        listSatellite.add(satelliteKenobi);

        Satellite satelliteSkywalker = new Satellite();
        satelliteSkywalker.setName("skywalker");
        satelliteSkywalker.setDistance(1150.5f);
        String[] arrMessageSkywalker= {"","es","","","secreto"};
        satelliteSkywalker.setMessage(arrMessageSkywalker);
        listSatellite.add(satelliteSkywalker);

        Satellite satelliteSato = new Satellite();
        satelliteSato.setName("sato");
        satelliteSato.setDistance(1420.7f);
        String[] arrMessageSato= {"este","","un","",""};
        satelliteSato.setMessage(arrMessageSato);
        listSatellite.add(satelliteSato);

        satelliteWrapp.setSatellites(listSatellite);
    String jsonStr=asJsonString(satelliteWrapp);
        mockMvc.perform( MockMvcRequestBuilders
            .post ("/topsecret")
            .content(jsonStr)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(jsonPath("$.message", Matchers.is("este es un mensaje secreto")))
                .andExpect(jsonPath("$.position.x", Matchers.is(-458.0)))
                .andExpect(jsonPath("$.position.y", Matchers.is(-221.0)))
                ;
    }
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
