package com.hidrolife.beta.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class CultivoDTO {
    private Long idCultivo;
    private String nombre;
    private Integer numeroPlantas;
    private Double phIdeal;
    private Double tdsIdeal;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fecha;
    private boolean activo;

}
