/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hidrolife.beta.controller;

import com.hidrolife.beta.dto.CultivoDTO;
import com.hidrolife.beta.dto.UsuarioDTO;
import com.hidrolife.beta.service.CultivoService;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
            @RequestParam LocalDate fecha,
            RedirectAttributes redirectAttributes) {

        try {
            cultivoService.saveCultivo(nombre, numeroPlantas, phIdeal, tdsIdeal, fecha);
            redirectAttributes.addFlashAttribute("msg", "Cultivo creado correctamente");
            return "redirect:/baseDatos";

        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/baseDatos"; // tu HTML
        }
    }

    @PostMapping("/editarCultivo/{id}")
    public String editarCultivo(
            @PathVariable Long id,
            @RequestParam String nombre,
            @RequestParam Integer numeroPlantas,
            @RequestParam Double phIdeal,
            @RequestParam Double tdsIdeal,
            @RequestParam LocalDate fecha,
            RedirectAttributes redirectAttributes) {

        try {

            cultivoService.actualizarCultivo(id, nombre, numeroPlantas, phIdeal, tdsIdeal, fecha);

            redirectAttributes.addFlashAttribute("msg", "Cultivo actualizado correctamente");
            return "redirect:/baseDatos";

        } catch (IllegalArgumentException e) {

            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/baseDatos";
        }
    }

    @PostMapping("/cultivo/{id}/desactivar")
    public String desactivarCultivo(
            @PathVariable("id") Long id,
            RedirectAttributes redirectAttributes) {

        try {
            cultivoService.desactivarCultivo(id);
            redirectAttributes.addFlashAttribute("msg", "Cultivo desactivado correctamente");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }

        return "redirect:/baseDatos?tabla=Cultivos";
    }

}
