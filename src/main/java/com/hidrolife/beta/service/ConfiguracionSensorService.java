package com.hidrolife.beta.service;

import com.hidrolife.beta.model.ConfiguracionSensor;
import com.hidrolife.beta.model.TipoSensor;
import com.hidrolife.beta.repository.ConfiguracionSensorRepository;
import org.springframework.stereotype.Service;

@Service
public class ConfiguracionSensorService implements IConfiguracionSensorService{

    private final ConfiguracionSensorRepository repo;

    public ConfiguracionSensorService(ConfiguracionSensorRepository repo) {
        this.repo = repo;
    }

    @Override
    public void actualizarLimites(Long id, Double min, Double max) {

        ConfiguracionSensor config =
                repo.findById(id)
                        .orElseThrow(() -> new RuntimeException("No encontrado"));

        if (min > max) {
            throw new IllegalArgumentException("El mínimo no puede ser mayor que el máximo");
        }

        config.setValorMinimo(min);
        config.setValorMaximo(max);

        repo.save(config);
    }


    @Override
    public ConfiguracionSensor obtenerPorTipo(TipoSensor tipo) {

        return repo
                .findByTipo(tipo)
                .orElseThrow(() ->
                        new RuntimeException("No existe configuración para " + tipo));
    }
}