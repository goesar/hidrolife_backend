/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hidrolife.beta.service;

import com.hidrolife.beta.model.Actividad;
import java.util.List;

import com.hidrolife.beta.model.Cultivo;
import java.time.LocalDateTime;
/**
 *
 * @author Esteban
 */
public interface ICultivoService {
    public List<Cultivo> getCultivos();
    public List<Cultivo> buscar(String criterio, String valor);
    
    public void saveCultivo(String nombre, Integer numeroPlantas, Double phIdeal, Double tdsIdeal, LocalDateTime fecha);
    public void deleteCultivo(Long idCultivo);
    public Cultivo cultivo(Long idCultivo);
    public List<Cultivo> listarTodo();
    public List<Cultivo> buscarPorRangoFecha(LocalDateTime fechaInicio, LocalDateTime fechaFin);
    
    public List<Cultivo> buscarPorCriterioYFecha(String criterio, String valor, LocalDateTime fechaInicio, LocalDateTime fechaFin);
    
}
