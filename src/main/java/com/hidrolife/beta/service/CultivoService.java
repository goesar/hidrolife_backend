/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hidrolife.beta.service;

import com.hidrolife.beta.model.Cultivo;

import com.hidrolife.beta.repository.CultivoRepository;

import java.time.LocalDate;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class CultivoService implements ICultivoService {

    @Autowired
    private CultivoRepository cultivoRepository;

    @Override
    public void saveCultivo(
            String nombre,
            Integer numeroPlantas,
            Double phIdeal,
            Double tdsIdeal,
            LocalDate fecha
    ) {

        // TODO OK: crear usuario
        Cultivo cultivo = new Cultivo();
        cultivo.setNombre(nombre);
        cultivo.setNumeroPlantas(numeroPlantas);
        cultivo.setPhIdeal(phIdeal);
        cultivo.setTdsIdeal(tdsIdeal);
        cultivo.setFecha(fecha);


        cultivoRepository.save(cultivo);

    }

    @Override
    public void desactivarCultivo(Long id) {
            Cultivo cultivo = obtenerCultivo(id);
        cultivo.setActivo(false);
        cultivoRepository.save(cultivo);
    }

    @Override
    public Cultivo obtenerCultivo(Long id) {
        return cultivoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cultivo no encontrado"));
    }

    @Override
    public List<Cultivo> buscar(String criterio, String valor) {

        switch (criterio) {
            case "id":
                try {
                    Double idCultivo = Double.parseDouble(valor);
                    return cultivoRepository.findByIdCultivoAndActivoTrue(idCultivo);
                } catch (NumberFormatException e) {
                    return List.of();
                }

            case "nombre":
                try {
                    return cultivoRepository.findByNombreContainingIgnoreCaseAndActivoTrue(valor);
                } catch (NumberFormatException e) {
                    return List.of();
                }


            case "numeroPlantas":
                try {
                    Integer numeroPlantas = Integer.valueOf(valor);
                    return cultivoRepository.findByNumeroPlantasAndActivoTrue(numeroPlantas);
                } catch (NumberFormatException e) {
                    return List.of();
                }

            case "phIdeal":
                try {
                    Double ph = Double.valueOf(valor);
                    return cultivoRepository.findByPhIdealAndActivoTrue(ph);
                } catch (NumberFormatException e) {
                    return List.of();
                }

            case "tdsIdeal":
                try {
                    Double tds = Double.valueOf(valor);
                    return cultivoRepository.findByTdsIdealAndActivoTrue(tds);
                } catch (NumberFormatException e) {
                    return List.of();
                }

            default:
                return List.of();
        }
    }

    @Override
    public List<Cultivo> listarTodo() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        boolean esAdmin = auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        if (esAdmin) {
            return cultivoRepository.findAll();
        } else {
            return cultivoRepository.findByActivoTrue();
        }
    }

    @Override
    public List<Cultivo> buscarPorRangoFecha(LocalDate fechaInicio, LocalDate fechaFin) {
        return List.of();
    }

    @Override
    public List<Cultivo> buscarPorCriterioYFecha(String criterio, String valor, LocalDate fechaInicio, LocalDate fechaFin) {
        if (valor == null || valor.isBlank()) {
            return buscarPorRangoFecha(fechaInicio, fechaFin);
        }

        switch (criterio) {

            case "id":
                try {
                    Long idCultivo = Long.parseLong(valor);
                    return cultivoRepository.findByIdCultivoAndFechaBetweenAndActivoTrue(idCultivo, fechaInicio, fechaFin);
                } catch (NumberFormatException e) {
                    return List.of();
                }

            case "nombre":
                return cultivoRepository.findByNombreContainingIgnoreCaseAndFechaBetweenAndActivoTrue(valor, fechaInicio, fechaFin);

            case "phIdeal":
                Double ph = Double.valueOf(valor);
                return cultivoRepository.findByPhIdealAndFechaBetweenAndActivoTrue(ph, fechaInicio, fechaFin);

            case "tdsIdeal":
                Double tds = Double.valueOf(valor);
                return cultivoRepository.findByTdsIdealAndFechaBetweenAndActivoTrue(tds, fechaInicio, fechaFin);

            default:
                return List.of();
        }
    }

    @Override
    public void actualizarCultivo(Long id,
                                  String nombre,
                                  Integer numeroPlantas,
                                  Double phIdeal,
                                  Double tdsIdeal,
                                  LocalDate fecha) {

        Cultivo cultivo = cultivoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cultivo no encontrado"));

        cultivo.setNombre(nombre);
        cultivo.setNumeroPlantas(numeroPlantas);
        cultivo.setPhIdeal(phIdeal);
        cultivo.setTdsIdeal(tdsIdeal);
        cultivo.setFecha(fecha);

        cultivoRepository.save(cultivo); // Hibernate hace UPDATE automáticamente
    }

}
