/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hidrolife.beta.service;

import com.hidrolife.beta.model.Actividad;
import com.hidrolife.beta.repository.ActividadRepository;

import java.time.LocalDate;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class ActividadService implements IActividadService {

    @Autowired
    private ActividadRepository actividadRepository;

    public void saveActividad(
            String actividades,
            LocalDate fecha,
            String usuario,
            String descripcion
    ) {

        // TODO OK: crear usuario
        Actividad actividad = new Actividad();
        actividad.setActividades(actividades);
        actividad.setFecha(fecha);
        actividad.setUsuario(usuario);
        actividad.setDescripcion(descripcion);
        actividad.setActivo(true);
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
                return actividadRepository.findByActividadesContainingIgnoreCaseAndActivoTrue(valor);

            case "usuario":
                return actividadRepository.findByUsuarioContainingIgnoreCaseAndActivoTrue(valor);


            default:
                return List.of();
        }
    }

    @Override
    public List<Actividad> listarTodo() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        boolean esAdmin = auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        if (esAdmin) {
            return actividadRepository.findAll();
        } else {
            return actividadRepository.findByActivoTrue();
        }
    }

    @Override
    public List<Actividad> buscarPorRangoFecha(LocalDate fechaInicio, LocalDate fechaFin) {
        return actividadRepository.findByFechaBetweenAndActivoTrue(fechaInicio, fechaFin);
    }

    @Override
    public void actualizarCultivo(Long id,
                                  String actividades,
                                  LocalDate fecha,
                                  String usuario,
                                  String descripcion
                                  ) {

        Actividad actividad = actividadRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Actividad no encontrada"));

        actividad.setActividades(actividades);
        actividad.setFecha(fecha);
        actividad.setUsuario(usuario);
        actividad.setDescripcion(descripcion);


        actividadRepository.save(actividad); // Hibernate hace UPDATE automáticamente
    }

    @Override
    public void desactivarActividad(Long id) {
        Actividad actividad = obtenerActividad(id);
        actividad.setActivo(false);
        actividadRepository.save(actividad);
    }

    @Override
    public Actividad obtenerActividad(Long id) {
        return actividadRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Actividad no encontrado"));
    }


    @Override
    public void saveActividad(String actividades, String usuario, LocalDate fecha, String descripcion) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Actividad> buscarPorCriterioYFecha(String criterio, String valor, LocalDate fechaInicio, LocalDate fechaFin) {

        switch (criterio) {

            case "id":
                try {
                    Long idActividad = Long.parseLong(valor);
                    return actividadRepository.findByIdActividadAndFechaBetweenAndActivoTrue(idActividad, fechaInicio, fechaFin);
                } catch (NumberFormatException e) {
                    return List.of();
                }

            case "actividades":
                return actividadRepository.findByActividadesContainingIgnoreCaseAndFechaBetweenAndActivoTrue(valor, fechaInicio, fechaFin);

            case "usuario":
                return actividadRepository.findByUsuarioContainingIgnoreCaseAndFechaBetweenAndActivoTrue(valor, fechaInicio, fechaFin);

            case "fecha":
                return actividadRepository.findByFechaBetweenAndActivoTrue(fechaInicio, fechaFin);

            default:
                return List.of();
        }
    }

}
