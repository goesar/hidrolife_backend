/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hidrolife.beta.controller;

import com.hidrolife.beta.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/usuarios/crear")
    public String crearUsuario(
            @RequestParam String nombre,
            @RequestParam String password,
            @RequestParam String email,
            @RequestParam String telefono,
            RedirectAttributes redirectAttributes) {
        System.out.println("---- DEBUG ----");
        System.out.println("Nombre: " + nombre);
        System.out.println("Email: " + email);
        System.out.println("tTelefono: " + telefono);
        System.out.println("Contraseña: " + password);

        System.out.println("----------------");
        try {
            usuarioService.saveUsuario(
                    nombre, password,
                    email, telefono
            );

            redirectAttributes.addFlashAttribute("msg", "Usuario creado correctamente");
            return "redirect:/login";

        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "nuevoUsuario"; // tu HTML
        }
    }
}
