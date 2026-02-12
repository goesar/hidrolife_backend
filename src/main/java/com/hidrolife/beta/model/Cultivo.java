/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hidrolife.beta.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

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
    private LocalDateTime fecha;

    public Cultivo() {
    }

    public Cultivo(Long idCultivo, String nombre, Integer numeroPlantas, Double phIdeal, Double tdsIdeal, LocalDateTime fecha) {
        this.idCultivo = idCultivo;
        this.nombre = nombre;
        this.numeroPlantas = numeroPlantas;
        this.phIdeal = phIdeal;
        this.tdsIdeal = tdsIdeal;
        this.fecha = fecha;
    }
}
