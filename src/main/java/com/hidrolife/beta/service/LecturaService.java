package com.hidrolife.beta.service;

import com.hidrolife.beta.dto.LecturaDTO;
import com.hidrolife.beta.model.LecturaSensor;
import com.hidrolife.beta.repository.LecturaRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LecturaService implements ILecturaService {

    @Autowired
    private LecturaRepository lecturaRepository;

    @Override
    public void guardarLectura(LecturaDTO dto) {
        LecturaSensor lectura = new LecturaSensor();

        lectura.setHumedad(dto.getHumedad());
        lectura.setTemperatura(dto.getTemperatura());
        lectura.setPh(dto.getPh());
        lectura.setTds(dto.getTds());
        lectura.setFechaYHora(LocalDateTime.now());

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
        dto.setFechaYHora(ultima.getFechaYHora());
        

        return dto;
    }

}
