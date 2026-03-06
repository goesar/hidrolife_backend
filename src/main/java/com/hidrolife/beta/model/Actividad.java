/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hidrolife.beta.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Entity

@Getter
@Setter
public class Actividad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idActividad;
    private String actividades;
    private String usuario;
    private LocalDate fecha;
    private String descripcion;
    private Boolean activo = true;

    @ManyToOne
    @JoinColumn(name = "id_cultivo")
    private Cultivo cultivo;

    public Actividad() {
    }

    public Actividad(Long idActividad, String actividades, String usuario, LocalDate fecha, String descripcion, Cultivo cultivo) {
        this.idActividad = idActividad;
        this.actividades = actividades;
        this.usuario = usuario;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.cultivo = cultivo;
    }

}
