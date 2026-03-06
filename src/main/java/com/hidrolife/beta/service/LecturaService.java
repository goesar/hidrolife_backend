package com.hidrolife.beta.service;

import com.hidrolife.beta.dto.LecturaDTO;
import com.hidrolife.beta.model.ConfiguracionSensor;
import com.hidrolife.beta.model.LecturaSensor;
import com.hidrolife.beta.model.TipoSensor;
import com.hidrolife.beta.repository.ConfiguracionSensorRepository;
import com.hidrolife.beta.repository.LecturaRepository;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class LecturaService implements ILecturaService {

    private final TelegramService telegramService;
    private final LecturaRepository lecturaRepository;
    private final ConfiguracionSensorRepository configuracionSensorRepository;
    private boolean alertaPhEnviada = false;
    private LocalDateTime ultimaAlertaTDS;
    private LocalDateTime ultimaAlertaHumedad;
    private LocalDateTime ultimaAlertaTemperatura;
    private LocalDateTime ultimaAlertaPh;

    public LecturaService(TelegramService telegramService, LecturaRepository lecturaRepository, ConfiguracionSensorRepository configuracionSensorRepository) {
        this.telegramService = telegramService;
        this.lecturaRepository = lecturaRepository;
        this.configuracionSensorRepository = configuracionSensorRepository;
    }

    @Override
    public void guardarLectura(LecturaDTO dto) {

        LecturaSensor lectura = new LecturaSensor();

        lectura.setHumedad(dto.getHumedad());
        lectura.setTemperatura(dto.getTemperatura());
        lectura.setPh(dto.getPh());
        lectura.setTds(dto.getTds());
        lectura.setFechaYHora(LocalDateTime.now());
        lectura.setActivo(true);

        Optional<ConfiguracionSensor> configPh =
                configuracionSensorRepository.findByTipo(TipoSensor.PH);

        Optional<ConfiguracionSensor> configTDS =
                configuracionSensorRepository.findByTipo(TipoSensor.TDS);

        Optional<ConfiguracionSensor> configHumedad =
                configuracionSensorRepository.findByTipo(TipoSensor.HUMEDAD);

        Optional<ConfiguracionSensor> configTemperatura =
                configuracionSensorRepository.findByTipo(TipoSensor.TEMPERATURA);


        if (configPh.isPresent()) {

            ConfiguracionSensor config = configPh.get();

            boolean phEstaFueraDeRango =
                    dto.getPh() < config.getValorMinimo() ||
                            dto.getPh() > config.getValorMaximo();


            if (phEstaFueraDeRango) {

                if (ultimaAlertaPh == null ||
                        Duration.between(ultimaAlertaPh, LocalDateTime.now()).toSeconds() >= 30) {

                    telegramService.enviarAlerta(
                            "⚠️ ALERTA HIDROLIFE ⚠️\n" +
                                    "pH fuera de rango: " + dto.getPh()
                    );

                    ultimaAlertaPh = LocalDateTime.now();
                }
            }
        }

        if (configTDS.isPresent()){

            ConfiguracionSensor config = configTDS.get();
            boolean tdsEstaFueraDeRango =
                    dto.getTds() < config.getValorMinimo() ||
                            dto.getTds() > config.getValorMaximo();

            if (tdsEstaFueraDeRango) {

                if (ultimaAlertaTDS == null ||
                        Duration.between(ultimaAlertaTDS, LocalDateTime.now()).toSeconds() >= 30) {

                    telegramService.enviarAlerta(
                            "⚠️ ALERTA HIDROLIFE ⚠️\n" +
                                    "TDS fuera de rango: " + dto.getTds()
                    );

                    ultimaAlertaTDS = LocalDateTime.now();
                }
            }

        }
        if (configHumedad.isPresent()) {

            ConfiguracionSensor config = configHumedad.get();

            boolean humedadEstaFueraDeRango =
                    dto.getHumedad() < config.getValorMinimo() ||
                            dto.getHumedad() > config.getValorMaximo();


            if (humedadEstaFueraDeRango) {

                if (ultimaAlertaHumedad == null ||
                        Duration.between(ultimaAlertaHumedad, LocalDateTime.now()).toSeconds() >= 30) {

                    telegramService.enviarAlerta(
                            "⚠️ ALERTA HIDROLIFE ⚠️\n" +
                                    "Humedad fuera de rango: " + dto.getHumedad()
                    );

                    ultimaAlertaHumedad = LocalDateTime.now();
                }
            }
        }

        if (configTemperatura.isPresent()) {

            ConfiguracionSensor config = configTemperatura.get();

            boolean temperaturaEstaFueraDeRango =
                    dto.getTemperatura() < config.getValorMinimo() ||
                            dto.getTemperatura() > config.getValorMaximo();


            if (temperaturaEstaFueraDeRango) {

                if (ultimaAlertaTemperatura == null ||
                        Duration.between(ultimaAlertaTemperatura, LocalDateTime.now()).toSeconds() >= 30) {

                    telegramService.enviarAlerta(
                            "⚠️ ALERTA HIDROLIFE ⚠️\n" +
                                    "Temperatura fuera de rango: " + dto.getTemperatura()
                    );

                    ultimaAlertaTemperatura = LocalDateTime.now();
                }
            }
        }

        lecturaRepository.save(lectura);
    }



    @Override
    public List<LecturaSensor> listarTodo() {
        return lecturaRepository.findAll();
    }

    @Override
    public List<LecturaSensor> buscarPorRangoFecha(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        return lecturaRepository.findByFechaYHoraBetween(fechaInicio, fechaFin);
    }

    @Override
    public List<LecturaSensor> buscar(String criterio, String valor) {

        switch (criterio) {

            case "id":
                try {
                    Long idActividad = Long.parseLong(valor);
                    return lecturaRepository.findById(idActividad).map(List::of).orElse(List.of());
                } catch (NumberFormatException e) {
                    return List.of();
                }

            case "ph":
                try {
                    Double ph = Double.valueOf(valor);
                    return lecturaRepository.findByPh(ph);
                } catch (NumberFormatException e) {
                    return List.of();
                }

            case "tds":
                try {
                    Double tds = Double.valueOf(valor);
                    return lecturaRepository.findByTds(tds);
                } catch (NumberFormatException e) {
                    return List.of();
                }

            case "humedad":
                try {
                    Double humedad = Double.valueOf(valor);
                    return lecturaRepository.findByHumedad(humedad);
                } catch (NumberFormatException e) {
                    return List.of();
                }

            case "temperatura":
                try {
                    Double temperatura = Double.valueOf(valor);
                    return lecturaRepository.findByTemperatura(temperatura);
                } catch (NumberFormatException e) {
                    return List.of();
                }

            default:
                return List.of();
        }
    }

    @Override
    public List<LecturaSensor> buscarPorCriterioYFecha(String criterio, String valor, LocalDateTime fechaInicio, LocalDateTime fechaFin) {

        switch (criterio) {

            case "id":
                try {
                    Long idLectura = Long.parseLong(valor);
                    return lecturaRepository.findByIdLecturaAndFechaYHoraBetween(idLectura, fechaInicio, fechaFin);
                } catch (NumberFormatException e) {
                    return List.of();
                }

            case "ph":
                try {
                    Double ph = Double.valueOf(valor);
                    return lecturaRepository.findByPhAndFechaYHoraBetween(ph, fechaInicio, fechaFin);
                } catch (NumberFormatException e) {
                    return List.of();
                }

            case "tds":
                try {
                    Double tds = Double.valueOf(valor);
                    return lecturaRepository.findByTdsAndFechaYHoraBetween(tds, fechaInicio, fechaFin);
                } catch (NumberFormatException e) {
                    return List.of();
                }

            case "humedad":
                try {
                    Double humedad = Double.valueOf(valor);
                    return lecturaRepository.findByHumedadAndFechaYHoraBetween(humedad, fechaInicio, fechaFin);
                } catch (NumberFormatException e) {
                    return List.of();
                }

            case "temperatura":
                try {
                    Double temperatura = Double.valueOf(valor);
                    return lecturaRepository.findByTemperaturaAndFechaYHoraBetween(temperatura, fechaInicio, fechaFin);
                } catch (NumberFormatException e) {
                    return List.of();
                }

            default:
                return List.of();
        }
    }

    @Override
    public LecturaDTO obtenerUltimaLectura() {

        LecturaSensor ultima = lecturaRepository.findTopByOrderByIdLecturaDesc();

        if (ultima == null) return null;

        LecturaDTO dto = new LecturaDTO();
        dto.setHumedad(ultima.getHumedad());
        dto.setPh(ultima.getPh());
        dto.setTds(ultima.getTds());
        dto.setTemperatura(ultima.getTemperatura());


        return dto;
    }

    @Override
    public void desactivarLectura(Long id) {
        LecturaSensor lectura = obtenerLectura(id);
        lectura.setActivo(false);
        lecturaRepository.save(lectura);
    }

    @Override
    public LecturaSensor obtenerLectura(Long id) {
        return lecturaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cultivo no encontrado"));
    }

}
