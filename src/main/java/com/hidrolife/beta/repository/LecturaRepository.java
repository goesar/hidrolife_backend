package com.hidrolife.beta.repository;

import com.hidrolife.beta.model.LecturaSensor;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LecturaRepository extends JpaRepository<LecturaSensor, Long> {

    List<LecturaSensor> findByPh(Double ph);
    List<LecturaSensor> findByTds(Double tds);
    List<LecturaSensor> findByHumedad(Double humedad);
    List<LecturaSensor> findByTemperatura(Double temperatura);

    public List<LecturaSensor> findByFechaYHoraBetween(LocalDateTime fechaInicio, LocalDateTime fechaFin);

    public List<LecturaSensor> findByIdLecturaAndFechaYHoraBetween(Long idLectura, LocalDateTime fechaInicio, LocalDateTime fechaFin);

    public List<LecturaSensor> findByPhAndFechaYHoraBetween(Double ph, LocalDateTime fechaInicio, LocalDateTime fechaFin);

    public List<LecturaSensor> findByTdsAndFechaYHoraBetween(Double tds, LocalDateTime fechaInicio, LocalDateTime fechaFin);

    public List<LecturaSensor> findByHumedadAndFechaYHoraBetween(Double humedad, LocalDateTime fechaInicio, LocalDateTime fechaFin);

    public List<LecturaSensor> findByTemperaturaAndFechaYHoraBetween(Double temperatura, LocalDateTime fechaInicio, LocalDateTime fechaFin);

    LecturaSensor findTopByOrderByIdLecturaDesc();

}
