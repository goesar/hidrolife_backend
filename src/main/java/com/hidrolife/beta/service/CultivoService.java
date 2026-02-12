/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hidrolife.beta.service;

import com.hidrolife.beta.model.Actividad;
import com.hidrolife.beta.model.Cultivo;

import com.hidrolife.beta.repository.CultivoRepository;
import java.time.LocalDateTime;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
            LocalDateTime fecha
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
    public List<Cultivo> getCultivos() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void deleteCultivo(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Cultivo cultivo(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Cultivo> buscar(String criterio, String valor) {

        switch (criterio) {
            case "id":
                try {
                    Long idCultivo = Long.parseLong(valor);
                    return cultivoRepository.findById(idCultivo).map(List::of).orElse(List.of());
                } catch (NumberFormatException e) {
                    return List.of();
                }

            case "nombre":
                return cultivoRepository.findByNombreContainingIgnoreCase(valor);

            case "numeroPlantas":
                try {
                    Integer numeroPlantas = Integer.valueOf(valor);
                    return cultivoRepository.findByNumeroPlantas(numeroPlantas);
                } catch (NumberFormatException e) {
                    return List.of();
                }

            case "phIdeal":
                try {
                    Double ph = Double.valueOf(valor);
                    return cultivoRepository.findByPhIdeal(ph);
                } catch (NumberFormatException e) {
                    return List.of();
                }

            case "tdsIdeal":
                try {
                    Double tds = Double.valueOf(valor);
                    return cultivoRepository.findByTdsIdeal(tds);
                } catch (NumberFormatException e) {
                    return List.of();
                }

            default:
                return List.of();
        }
    }

    @Override
    public List<Cultivo> listarTodo() {
        return cultivoRepository.findAll();
    }

    @Override
    public List<Cultivo> buscarPorRangoFecha(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        return cultivoRepository.findByFechaBetween(fechaInicio, fechaFin);
    }

    @Override
    public List<Cultivo> buscarPorCriterioYFecha(String criterio, String valor, LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        if (valor == null || valor.isBlank()) {
            return buscarPorRangoFecha(fechaInicio, fechaFin);
        }

        switch (criterio) {

            case "id":
                try {
                    Long idCultivo = Long.parseLong(valor);
                    return cultivoRepository.findByIdCultivoAndFechaBetween(idCultivo, fechaInicio, fechaFin);
                } catch (NumberFormatException e) {
                    return List.of();
                }

            case "nombre":
                return cultivoRepository.findByNombreContainingIgnoreCaseAndFechaBetween(valor, fechaInicio, fechaFin);

            case "phIdeal":
                Double ph = Double.valueOf(valor);
                return cultivoRepository.findByPhIdealAndFechaBetween(ph, fechaInicio, fechaFin);

            case "tdsIdeal":
                Double tds = Double.valueOf(valor);
                return cultivoRepository.findByTdsIdealAndFechaBetween(tds, fechaInicio, fechaFin);

            default:
                return List.of();
        }
    }

}
