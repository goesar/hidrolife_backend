
package com.hidrolife.beta.controller;

import com.hidrolife.beta.service.ActividadService;
import com.hidrolife.beta.service.CultivoService;
import com.hidrolife.beta.service.LecturaService;
import com.hidrolife.beta.service.ReporteService;
import com.hidrolife.beta.service.UsuarioService;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ReporteController {

    @Autowired
    private ReporteService reporteService;

    @Autowired
    private ActividadService actividadService;

    @Autowired
    private CultivoService cultivoService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private LecturaService lecturaService;

    @GetMapping("/reporte/pdf")
    public ResponseEntity<byte[]> generarPDF(
            @RequestParam String tabla,
            @RequestParam(required = false) String criterio,
            @RequestParam(required = false) String valor,
            @RequestParam(required = false) LocalDate fechaInicio,
            @RequestParam(required = false) LocalDate fechaFin
    ) {

        List<?> datos = obtenerDatos(tabla, criterio, valor, fechaInicio, fechaFin);

        byte[] pdfBytes = reporteService.generarPDF(tabla, datos);

        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=reporte_" + tabla + ".pdf")
                .body(pdfBytes);
    }

    private List<?> obtenerDatos(String tabla, String criterio, String valor,
                                 LocalDate fechaInicio, LocalDate fechaFin) {

        // La misma lógica que tu método /buscar:
        switch (tabla) {
            case "Actividades" -> {
                if (valor == null || valor.isBlank()) {
                    if (fechaInicio == null || fechaFin == null)
                        return actividadService.listarTodo();
                    return actividadService.buscarPorRangoFecha(fechaInicio.atStartOfDay(), fechaFin.atTime(23,59,59));
                }
                if (fechaInicio == null || fechaFin == null)
                    return actividadService.buscar(criterio, valor);
                return actividadService.buscarPorCriterioYFecha(criterio, valor,
                        fechaInicio.atStartOfDay(), fechaFin.atTime(23,59,59));
            }

            case "Cultivos" -> {
                if (valor == null || valor.isBlank()) {
                    if (fechaInicio == null || fechaFin == null)
                        return cultivoService.listarTodo();
                    return cultivoService.buscarPorRangoFecha(fechaInicio.atStartOfDay(), fechaFin.atTime(23,59,59));
                }
                if (fechaInicio == null || fechaFin == null)
                    return cultivoService.buscar(criterio, valor);
                return cultivoService.buscarPorCriterioYFecha(criterio, valor,
                        fechaInicio.atStartOfDay(), fechaFin.atTime(23,59,59));
            }

            case "Usuarios" -> {
                if (valor == null || valor.isBlank())
                    return usuarioService.listarTodo();
                return usuarioService.buscar(criterio, valor);
            }

            case "Lecturas" -> {
                if (valor == null || valor.isBlank()) {
                    if (fechaInicio == null || fechaFin == null)
                        return lecturaService.listarTodo();
                    return lecturaService.buscarPorRangoFecha(fechaInicio.atStartOfDay(), fechaFin.atTime(23,59,59));
                }
                if (fechaInicio == null || fechaFin == null)
                    return lecturaService.buscar(criterio, valor);
                return lecturaService.buscarPorCriterioYFecha(criterio, valor,
                        fechaInicio.atStartOfDay(), fechaFin.atTime(23,59,59));
            }
        }

        return List.of(); // fallback
    }
}

