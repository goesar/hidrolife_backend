/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hidrolife.beta.repository;

import com.hidrolife.beta.model.Actividad;
import com.hidrolife.beta.model.Cultivo;
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
public interface CultivoRepository extends JpaRepository<Cultivo, Long> {

    public List<Cultivo> findByNombreContainingIgnoreCase(String valor);

    List<Cultivo> findByPhIdeal(Double ph);


    public List<Cultivo> findByTdsIdealAndFechaBetween(Double tdsIdeal, LocalDateTime fechaInicio, LocalDateTime fechaFin);

    public List<Cultivo> findByIdCultivoAndFechaBetween(Long idCultivo, LocalDateTime fechaInicio, LocalDateTime fechaFin);

    public List<Cultivo> findByNombreContainingIgnoreCaseAndFechaBetween(String valor, LocalDateTime fechaInicio, LocalDateTime fechaFin);

    List<Cultivo> findByPhIdealAndFechaBetween(
        Double phIdeal,
        LocalDateTime fechaInicio,
        LocalDateTime fechaFin
);

    public List<Cultivo> findByTdsIdeal(Double tds);

    public List<Cultivo> findByNumeroPlantas(Integer plantas);

    public List<Cultivo> findByFechaBetween(LocalDateTime fechaInicio, LocalDateTime fechaFin);

}
