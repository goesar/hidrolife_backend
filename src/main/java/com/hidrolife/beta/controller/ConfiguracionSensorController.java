package com.hidrolife.beta.controller;

import com.hidrolife.beta.model.ConfiguracionSensor;
import com.hidrolife.beta.service.ConfiguracionSensorService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ConfiguracionSensorController {

    public final ConfiguracionSensorService configuracionSensorService;

    public ConfiguracionSensorController(ConfiguracionSensorService configuracionSensorService) {
        this.configuracionSensorService = configuracionSensorService;
    }

    @PostMapping("/guardarConfig")
    public String guardarConfig(
            @RequestParam Long idConfigSensor,
            @RequestParam Double valorMinimo,
            @RequestParam Double valorMaximo,
            @RequestParam String redirectUrl,

            RedirectAttributes redirectAttributes) {

        try {

            configuracionSensorService.actualizarLimites(
                    idConfigSensor,
                    valorMinimo,
                    valorMaximo
            );

            redirectAttributes.addFlashAttribute("msg", "Límites actualizados");

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al actualizar");
        }

        return "redirect:" + redirectUrl;
    }
}