package com.hidrolife.beta.repository;

import com.hidrolife.beta.model.ConfiguracionSensor;
import com.hidrolife.beta.model.TipoSensor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ConfiguracionSensorRepository extends JpaRepository <ConfiguracionSensor, Long> {
    Optional<ConfiguracionSensor> findByTipo(TipoSensor tipo);
}
