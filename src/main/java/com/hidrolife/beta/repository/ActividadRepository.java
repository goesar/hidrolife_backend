/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hidrolife.beta.repository;

import com.hidrolife.beta.model.Actividad;
import com.hidrolife.beta.model.Usuario;
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

    public List<Actividad> findByActividadesContainingIgnoreCase(String valor);

    public List<Actividad> findByUsuarioContainingIgnoreCase(String valor);


    public List<Actividad> findByFechaBetween(LocalDateTime fechaInicio, LocalDateTime fechaFin);

    public List<Actividad> findByIdActividadAndFechaBetween(Long idActividad, LocalDateTime fechaInicio, LocalDateTime fechaFin);

    public List<Actividad> findByActividadesContainingIgnoreCaseAndFechaBetween(String valor, LocalDateTime fechaInicio, LocalDateTime fechaFin);

    public List<Actividad> findByUsuarioContainingIgnoreCaseAndFechaBetween(String valor, LocalDateTime fechaInicio, LocalDateTime fechaFin);

   

    
}
