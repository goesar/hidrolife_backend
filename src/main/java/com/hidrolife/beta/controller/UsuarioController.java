/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hidrolife.beta.controller;

import com.hidrolife.beta.dto.UsuarioDTO;
import com.hidrolife.beta.model.Usuario;
import com.hidrolife.beta.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;


    @PostMapping("/usuario/crear")
    public String crearUsuario(
            @ModelAttribute UsuarioDTO dto,
            RedirectAttributes redirectAttributes,
            Model model) {

        try {

            usuarioService.saveUsuario(dto);

            redirectAttributes.addFlashAttribute("msg", "Usuario creado correctamente");
            return "redirect:/login";

        } catch (IllegalArgumentException e) {

            model.addAttribute("usuario", dto); // volver a enviar datos
            model.addAttribute("error", e.getMessage());

            return "nuevoUsuario";
        }
    }

    @PostMapping("/usuario/{id}/desactivar")
    public String desactivarUsuario(
            @PathVariable("id") Long id,
            RedirectAttributes redirectAttributes) {

        try {
            usuarioService.desactivarUsuario(id);
            redirectAttributes.addFlashAttribute("msg", "Usuario eliminado correctamente");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }

        return "redirect:/buscar?tabla=Usuarios&criterio=id&valor=&fechaInicio=&fechaFin=";
    }

    @PostMapping("/usuario/actualizar")
    public String actualizarPerfil(@ModelAttribute UsuarioDTO usuario,
                                   Principal principal,
                                   RedirectAttributes redirectAttributes) {

        try {

            String emailActual = principal.getName();

            usuarioService.actualizarPerfil(emailActual, usuario);

            redirectAttributes.addFlashAttribute("msg",
                    "Datos actualizados correctamente");

        } catch (Exception e) {

            redirectAttributes.addFlashAttribute("error",
                    "Ocurrió un error al actualizar la información");

        }

        return "redirect:/editarUsuario";
    }
}
