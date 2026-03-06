package com.hidrolife.beta.service;

import com.hidrolife.beta.model.ConfiguracionSensor;
import com.hidrolife.beta.model.TipoSensor;

import java.time.LocalDate;

public interface IConfiguracionSensorService {

    void actualizarLimites(Long id, Double min, Double max);
    ConfiguracionSensor obtenerPorTipo(TipoSensor tipo);
}
