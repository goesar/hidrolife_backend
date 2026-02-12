/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hidrolife.beta.controller;

import com.hidrolife.beta.service.ActividadService;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ActividadController {
    @Autowired
    private ActividadService actividadService;

    @PostMapping("/guardarActividad")
    public String guardarActividad(
            @RequestParam String actividades,
            @RequestParam LocalDateTime fecha,
            @RequestParam String usuario,
            @RequestParam String descripcion,
            RedirectAttributes redirectAttributes) {
        System.out.println("---- DEBUG ----");
        System.out.println("Actividad: " + actividades);
        System.out.println("Fecha: " + fecha);
        System.out.println("Usuario: " + usuario);

        System.out.println("----------------");
        try {
            actividadService.saveActividad(
                    actividades, fecha,
                    usuario, descripcion
            );
            redirectAttributes.addFlashAttribute("msg", "Actividad creada correctamente");
            return "redirect:/baseDatos";

        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/baseDatos"; // tu HTML
        }
    }
}
