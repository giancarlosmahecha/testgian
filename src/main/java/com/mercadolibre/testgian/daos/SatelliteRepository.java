package com.mercadolibre.testgian.daos;

import com.mercadolibre.testgian.entities.Satellite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface SatelliteRepository extends JpaRepository<Satellite, String> {
}
