/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hidrolife.beta.repository;

import com.hidrolife.beta.model.Actividad;
import com.hidrolife.beta.model.Cultivo;
import com.hidrolife.beta.model.Usuario;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Esteban
 */
@Repository
public interface CultivoRepository extends JpaRepository<Cultivo, Long> {

    List<Cultivo> findByNombreContainingIgnoreCaseAndActivoTrue(String nombre);

    List<Cultivo> findByPhIdealAndActivoTrue(Double ph);


    public List<Cultivo> findByTdsIdealAndFechaBetweenAndActivoTrue(Double tdsIdeal, LocalDate fechaInicio, LocalDate fechaFin);

    public List<Cultivo> findByIdCultivoAndFechaBetweenAndActivoTrue(Long idCultivo, LocalDate fechaInicio, LocalDate fechaFin);

    public List<Cultivo> findByNombreContainingIgnoreCaseAndFechaBetweenAndActivoTrue(String valor, LocalDate fechaInicio, LocalDate fechaFin);

    List<Cultivo> findByPhIdealAndFechaBetweenAndActivoTrue(
        Double phIdeal,
        LocalDate fechaInicio,
        LocalDate fechaFin
);

    public List<Cultivo> findByTdsIdealAndActivoTrue(Double tds);

    public List<Cultivo> findByNumeroPlantasAndActivoTrue(Integer plantas);

    public List<Cultivo> findByFechaBetweenAndActivoTrue(LocalDate fechaInicio, LocalDate fechaFin);

    List<Cultivo> findByIdCultivoAndActivoTrue(Double idCultivo);


    List<Cultivo> findByActivoTrue();
}
