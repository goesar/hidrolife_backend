/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hidrolife.beta.service;

import com.hidrolife.beta.model.Actividad;
import com.hidrolife.beta.model.Usuario;
import com.hidrolife.beta.repository.ActividadRepository;
import java.time.LocalDateTime;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActividadService implements IActividadService {

    @Autowired
    private ActividadRepository actividadRepository;

    public void saveActividad(
            String actividades,
            LocalDateTime fecha,
            String usuario,
            String descripcion
    ) {

        // TODO OK: crear usuario
        Actividad actividad = new Actividad();
        actividad.setActividades(actividades);
        actividad.setFecha(fecha);
        actividad.setUsuario(usuario);
        actividad.setDescripcion(descripcion);

        actividadRepository.save(actividad);
    }

    
  

    @Override

    public List<Actividad> buscar(String criterio, String valor) {

        switch (criterio) {

            case "id":
                try {
                    Long idActividad = Long.parseLong(valor);
                    return actividadRepository.findById(idActividad).map(List::of).orElse(List.of());
                } catch (NumberFormatException e) {
                    return List.of();
                }

            case "actividades":
                return actividadRepository.findByActividadesContainingIgnoreCase(valor);

            case "usuario":
                return actividadRepository.findByUsuarioContainingIgnoreCase(valor);


            default:
                return List.of();
        }
    }

    @Override
    public List<Actividad> listarTodo() {
        return actividadRepository.findAll();
    }

    @Override
    public List<Actividad> buscarPorRangoFecha(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        return actividadRepository.findByFechaBetween(fechaInicio, fechaFin);
    }

   


    @Override
    public void saveActividad(String actividades, String usuario, LocalDateTime fecha, String descripcion) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Actividad> buscarPorCriterioYFecha(String criterio, String valor, LocalDateTime fechaInicio, LocalDateTime fechaFin) {

        switch (criterio) {

            case "id":
                try {
                    Long idActividad = Long.parseLong(valor);
                    return actividadRepository.findByIdActividadAndFechaBetween(idActividad, fechaInicio, fechaFin);
                } catch (NumberFormatException e) {
                    return List.of();
                }

            case "actividades":
                return actividadRepository.findByActividadesContainingIgnoreCaseAndFechaBetween(valor, fechaInicio, fechaFin);

            case "usuario":
                return actividadRepository.findByUsuarioContainingIgnoreCaseAndFechaBetween(valor, fechaInicio, fechaFin);

            case "fecha":
                return actividadRepository.findByFechaBetween(fechaInicio, fechaFin);

            default:
                return List.of();
        }
    }

}
