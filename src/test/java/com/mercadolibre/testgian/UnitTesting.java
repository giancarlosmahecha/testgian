package com.mercadolibre.testgian;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.mercadolibre.testgian.entities.Satellite;
import com.mercadolibre.testgian.entities.SatelliteWrapper;
import com.mercadolibre.testgian.exception.ApiRequestException;
import com.mercadolibre.testgian.rest.TopSecretController;
import com.mercadolibre.testgian.tools.Tools;
import org.apache.catalina.core.ApplicationContext;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


import java.awt.geom.Point2D;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
@RunWith(SpringJUnit4ClassRunner.class)

public class UnitTesting extends TestgianApplication{


    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private MockMvc mockMvc;

   /*@Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }*/

    @Test
    @DisplayName("Distance test")
    public void testDistance() throws Exception
    {
        Tools tools= new Tools() ;
        Point2D pointA=new Point2D.Double(-1200,-200);
        Point2D pointB=new Point2D.Double(100,-100);
        double distance=tools.getDistance(pointA,pointB);
        assertEquals (distance,distance);
    }
}

