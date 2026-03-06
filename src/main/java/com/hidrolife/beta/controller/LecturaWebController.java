package com.hidrolife.beta.controller;

import com.hidrolife.beta.service.LecturaService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LecturaWebController {

    private final LecturaService lecturaService;

    public LecturaWebController(LecturaService lecturaService) {
        this.lecturaService = lecturaService;
    }

    @PostMapping("/lectura/{id}/desactivar")
    public String desactivarLectura(
            @PathVariable("id") Long id,
            RedirectAttributes redirectAttributes) {

        try {
            lecturaService.desactivarLectura(id);
            redirectAttributes.addFlashAttribute("msg", "Cultivo desactivado correctamente");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }

        return "redirect:/buscar?tabla=Lecturas&criterio=id&valor=&fechaInicio=&fechaFin=";
    }
}
