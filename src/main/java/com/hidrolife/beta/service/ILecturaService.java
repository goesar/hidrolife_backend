/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hidrolife.beta.service;

import com.hidrolife.beta.dto.LecturaDTO;

import com.hidrolife.beta.model.LecturaSensor;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author Esteban
 */
public interface ILecturaService {
    void guardarLectura(LecturaDTO dto);
    
    List<LecturaSensor> listarTodo();
    List<LecturaSensor> buscar(String criterio, String valor);
    
    List<LecturaSensor> buscarPorRangoFecha(LocalDateTime fechaInicio, LocalDateTime fechaFin);
    List<LecturaSensor> buscarPorCriterioYFecha(String criterio, String valor, LocalDateTime fechaInicio, LocalDateTime fechaFin);

    public LecturaDTO obtenerUltimaLectura();

    void desactivarLectura(Long id);

    LecturaSensor obtenerLectura(Long id);
}
