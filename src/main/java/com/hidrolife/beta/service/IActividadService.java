/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hidrolife.beta.service;

import com.hidrolife.beta.model.Usuario;

import java.time.LocalDate;
import java.util.List;
import com.hidrolife.beta.model.Actividad;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
/**
 *
 * @author Esteban
 */
public interface IActividadService {
    
    public List<Actividad> buscar(String criterio, String valor);
    public List<Actividad> listarTodo();

    Actividad obtenerActividad(Long id);

    public void saveActividad(String actividades, String usuario, LocalDate fecha, String descripcion);
    public List<Actividad> buscarPorCriterioYFecha(String criterio, String valor, LocalDate fechaInicio, LocalDate fechaFin);
    public List<Actividad> buscarPorRangoFecha(LocalDate fechaInicio, LocalDate fechaFin);

    void actualizarCultivo(Long id, String actividades, LocalDate fecha, String usuario, String descripcion);

    void desactivarActividad(Long id);

    void actualizarActividad(Long id, String actividades, LocalDate fecha, String usuario, String descripcion);
}
