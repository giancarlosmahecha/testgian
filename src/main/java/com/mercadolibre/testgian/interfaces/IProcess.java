package com.mercadolibre.testgian.interfaces;

import com.mercadolibre.testgian.entities.PositionResponse;
import com.mercadolibre.testgian.entities.SatelliteWrapper;
import java.util.List;

public interface IProcess {
    public PositionResponse GetLocation(SatelliteWrapper listSatellitesInfo);
}


