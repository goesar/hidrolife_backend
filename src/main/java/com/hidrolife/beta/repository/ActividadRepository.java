/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hidrolife.beta.repository;

import com.hidrolife.beta.model.Actividad;
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
public interface ActividadRepository extends JpaRepository<Actividad, Long> {

    public List<Actividad> findByActividadesContainingIgnoreCaseAndActivoTrue(String valor);

    public List<Actividad> findByUsuarioContainingIgnoreCaseAndActivoTrue(String valor);


    public List<Actividad> findByFechaBetweenAndActivoTrue(LocalDate fechaInicio, LocalDate fechaFin);

    public List<Actividad> findByIdActividadAndFechaBetweenAndActivoTrue(Long idActividad, LocalDate fechaInicio, LocalDate fechaFin);

    public List<Actividad> findByActividadesContainingIgnoreCaseAndFechaBetweenAndActivoTrue(String valor, LocalDate fechaInicio, LocalDate fechaFin);

    public List<Actividad> findByUsuarioContainingIgnoreCaseAndFechaBetweenAndActivoTrue(String valor, LocalDate fechaInicio, LocalDate fechaFin);


    List<Actividad> findByActivoTrue();
}
