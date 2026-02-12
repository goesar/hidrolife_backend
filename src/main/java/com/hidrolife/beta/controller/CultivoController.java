/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hidrolife.beta.controller;

import com.hidrolife.beta.service.CultivoService;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CultivoController {
     @Autowired
    private CultivoService cultivoService;

    @PostMapping("/guardarCultivo")
    public String guardarCultivo(
            @RequestParam String nombre,
            @RequestParam Integer numeroPlantas,
            @RequestParam Double phIdeal,
            @RequestParam Double tdsIdeal,
            @RequestParam LocalDateTime fecha,
            RedirectAttributes redirectAttributes) {
        System.out.println("---- DEBUG ----");
        System.out.println("Nombre: " + nombre);
        System.out.println("Usuario: " + numeroPlantas);
        System.out.println("Fecha: " + fecha);

        System.out.println("----------------");
        try {
            cultivoService.saveCultivo(nombre, numeroPlantas, phIdeal, tdsIdeal, fecha);
            redirectAttributes.addFlashAttribute("msg", "Cultivo creado correctamente");
            return "redirect:/baseDatos";

        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/baseDatos"; // tu HTML
        }
    }

}
