/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hidrolife.beta.service;

import com.hidrolife.beta.model.Actividad;

import java.time.LocalDate;
import java.util.List;

import com.hidrolife.beta.model.Cultivo;
import com.hidrolife.beta.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
/**
 *
 * @author Esteban
 */
public interface ICultivoService {


    Cultivo obtenerCultivo(Long id);

    public List<Cultivo> buscar(String criterio, String valor);
    
    public void saveCultivo(String nombre, Integer numeroPlantas, Double phIdeal, Double tdsIdeal, LocalDate fecha);
    public void desactivarCultivo(Long idCultivo);

    public List<Cultivo> listarTodo();



    public List<Cultivo> buscarPorRangoFecha(LocalDate fechaInicio, LocalDate fechaFin);
    
    public List<Cultivo> buscarPorCriterioYFecha(String criterio, String valor, LocalDate fechaInicio, LocalDate fechaFin);

    void actualizarCultivo(Long id, String nombre, Integer numeroPlantas, Double phIdeal, Double tdsIdeal, LocalDate fecha);
}
