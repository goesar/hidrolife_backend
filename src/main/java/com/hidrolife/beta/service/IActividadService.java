/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hidrolife.beta.service;

import com.hidrolife.beta.model.Usuario;
import java.util.List;
import com.hidrolife.beta.model.Actividad;
import java.time.LocalDateTime;
/**
 *
 * @author Esteban
 */
public interface IActividadService {
    
    public List<Actividad> buscar(String criterio, String valor);
    public List<Actividad> listarTodo();
    public void saveActividad(String actividades,String usuario, LocalDateTime fecha, String descripcion);
    public List<Actividad> buscarPorCriterioYFecha(String criterio, String valor, LocalDateTime fechaInicio, LocalDateTime fechaFin);
    public List<Actividad> buscarPorRangoFecha(LocalDateTime fechaInicio, LocalDateTime fechaFin);
}
