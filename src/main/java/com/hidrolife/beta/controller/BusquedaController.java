/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hidrolife.beta.controller;

import com.hidrolife.beta.model.Actividad;
import com.hidrolife.beta.model.Cultivo;
import com.hidrolife.beta.model.LecturaSensor;
import com.hidrolife.beta.model.Usuario;
import com.hidrolife.beta.service.ActividadService;
import com.hidrolife.beta.service.CultivoService;
import com.hidrolife.beta.service.LecturaService;
import com.hidrolife.beta.service.UsuarioService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BusquedaController {
    @Autowired
    private ActividadService actividadService;
    @Autowired
    private CultivoService cultivoService;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private LecturaService lecturaService;

    @GetMapping("/buscar")
    public String buscar(
            @RequestParam String tabla,
            @RequestParam String criterio,
            @RequestParam(required = false) String valor,
            @RequestParam(required = false) LocalDate fechaInicio,
            @RequestParam(required = false) LocalDate fechaFin,
            Model model) {

        model.addAttribute("tablaActual", tabla);

        boolean tieneValor = valor != null && !valor.isEmpty();
        boolean tieneRango = fechaInicio != null && fechaFin != null;

        LocalDateTime inicioDT = null;
        LocalDateTime finDT = null;

        if (tieneRango) {
            inicioDT = fechaInicio.atStartOfDay();
            finDT = fechaFin.atTime(23, 59, 59);
        }
        // LISTA GENÉRICA PARA RESULTADOS
        Object resultados = null;

        switch (tabla) {

            // ---------------------------------------------------------
            // 🔵 TABLA ACTIVIDADES
            // ---------------------------------------------------------
            case "Actividades" -> {
                if (!tieneValor && !tieneRango) {
                    resultados = actividadService.listarTodo();
                } else if (!tieneValor) {
                    resultados = actividadService.buscarPorRangoFecha(inicioDT, finDT);
                } else if (!tieneRango) {
                    resultados = actividadService.buscar(criterio, valor);
                } else {
                    resultados = actividadService.buscarPorCriterioYFecha(criterio, valor, inicioDT, finDT);
                }
            }

            // ---------------------------------------------------------
            // 🟢 TABLA CULTIVOS
            // ---------------------------------------------------------
            case "Cultivos" -> {
                if (!tieneValor && !tieneRango) {
                    resultados = cultivoService.listarTodo();
                } else if (!tieneValor) {
                    resultados = cultivoService.buscarPorRangoFecha(inicioDT, finDT);
                } else if (!tieneRango) {
                    resultados = cultivoService.buscar(criterio, valor);
                } else {
                    resultados = cultivoService.buscarPorCriterioYFecha(criterio, valor, inicioDT, finDT);
                }
            }

            // ---------------------------------------------------------
            // 🟣 TABLA USUARIOS
            // ---------------------------------------------------------
            case "Usuarios" -> {
                if (!tieneValor) {
                    resultados = usuarioService.listarTodo();
                } else {
                    resultados = usuarioService.buscar(criterio, valor);
                }
            }

            case "Lecturas" -> {
                if (!tieneValor && !tieneRango) {
                    resultados = lecturaService.listarTodo();
                } else if (!tieneValor) {
                    resultados = lecturaService.buscarPorRangoFecha(inicioDT, finDT);
                } else if (!tieneRango) {
                    resultados = lecturaService.buscar(criterio, valor);
                } else {
                    resultados = lecturaService.buscarPorCriterioYFecha(criterio, valor, inicioDT, finDT);
                }
            }
        }

        // PARA QUE LOS MODALES NO CRASHEN
        model.addAttribute("actividad", new Actividad());
        model.addAttribute("cultivo", new Cultivo());
        model.addAttribute("lecturaSensor", new LecturaSensor());

        model.addAttribute("cultivos", cultivoService.listarTodo());
        model.addAttribute("usuario", new Usuario());

        // LO QUE SE GUARDA PARA RECARGAR INPUTS
        model.addAttribute("resultados", resultados);
        model.addAttribute("criterioActual", criterio);
        model.addAttribute("valorActual", valor);
        model.addAttribute("fechaInicioActual", fechaInicio);
        model.addAttribute("fechaFinActual", fechaFin);

        return "baseDatos";
    }
}
