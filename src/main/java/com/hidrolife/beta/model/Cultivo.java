/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hidrolife.beta.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Entity


@Setter @Getter
public class Cultivo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long idCultivo;
    private String nombre;
    private Integer numeroPlantas;
    private Double phIdeal;
    private Double tdsIdeal;

    private LocalDate fecha;
    private boolean activo = true;

    public Cultivo() {
    }

    public Cultivo(Long idCultivo, String nombre, Integer numeroPlantas, Double phIdeal, Double tdsIdeal, LocalDate fecha) {
        this.idCultivo = idCultivo;
        this.nombre = nombre;
        this.numeroPlantas = numeroPlantas;
        this.phIdeal = phIdeal;
        this.tdsIdeal = tdsIdeal;
        this.fecha = fecha;
    }
}
