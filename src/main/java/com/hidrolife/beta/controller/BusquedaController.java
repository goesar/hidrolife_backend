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
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
            @RequestParam(defaultValue = "0") int pagina,
            Model model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        boolean esAdmin = auth.getAuthorities()
                .stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        if (tabla.equals("Usuarios") && !esAdmin) {
            throw new AccessDeniedException("No autorizado");
        }

        model.addAttribute("tablaActual", tabla);

        boolean tieneValor = valor != null && !valor.isEmpty();
        boolean tieneRango = fechaInicio != null && fechaFin != null;


        LocalDate inicioD = null;
        LocalDate finD = null;

        LocalDateTime inicioDT = null;
        LocalDateTime finDT = null;

        if (tieneRango) {
            inicioD = fechaInicio;
            finD = fechaFin;

            inicioDT = fechaInicio.atStartOfDay();
            finDT = fechaFin.plusDays(1).atStartOfDay().minusNanos(1);
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
                    resultados = actividadService.buscarPorRangoFecha(fechaInicio, fechaFin);
                } else if (!tieneRango) {
                    resultados = actividadService.buscar(criterio, valor);
                } else {
                    resultados = actividadService.buscarPorCriterioYFecha(criterio, valor, fechaInicio, fechaFin);
                }
            }

            // ---------------------------------------------------------
            // 🟢 TABLA CULTIVOS
            // ---------------------------------------------------------
            case "Cultivos" -> {
                if (!tieneValor && !tieneRango) {
                    resultados = cultivoService.listarTodo();
                } else if (!tieneValor) {
                    resultados = cultivoService.buscarPorRangoFecha(fechaInicio, fechaFin);
                } else if (!tieneRango) {
                    resultados = cultivoService.buscar(criterio, valor);
                } else {
                    resultados = cultivoService.buscarPorCriterioYFecha(criterio, valor, fechaInicio, fechaFin);
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
        model.addAttribute("criterio", criterio);
        model.addAttribute("valor", valor);
        model.addAttribute("fechaInicio", fechaInicio);
        model.addAttribute("fechaFin", fechaFin);

        return "baseDatos";
    }
}
