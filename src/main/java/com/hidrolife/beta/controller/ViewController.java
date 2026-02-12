package com.hidrolife.beta.controller;

import com.hidrolife.beta.model.Actividad;
import com.hidrolife.beta.model.Cultivo;
import com.hidrolife.beta.model.LecturaSensor;
import com.hidrolife.beta.model.Usuario;

import com.hidrolife.beta.service.CultivoService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @Autowired
    private CultivoService cultivoService;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/nuevoUsuario")
    public String nuevoUsuario(Model modelo) {
        modelo.addAttribute("usuario", new Usuario());
        return "nuevoUsuario";
    }

    @GetMapping("/menuPrincipal")
    public String menuPrincipal() {
        return "menuPrincipal";
    }

    @GetMapping("/baseDatos")
    public String baseDatos(Model model) {

        model.addAttribute("actividad", new Actividad());
        model.addAttribute("cultivo", new Cultivo());
        model.addAttribute("lecturaSensor", new LecturaSensor());

        model.addAttribute("cultivos", cultivoService.listarTodo());
        model.addAttribute("resultados", null); // ⬅️ NO mostramos nada al inicio

        return "baseDatos";
    }

    @GetMapping("/sensores")
    public String sensores() {
        return "sensores";
    }

    @GetMapping("/sensorPH")
    public String sensorPH() {
        return "sensorPH";
    }

    @GetMapping("/sensorTDS")
    public String sensorTDS() {
        return "sensorTDS";
    }

    @GetMapping("/sensorTemperatura")
    public String sensorTemperatura() {
        return "sensorTemperatura";
    }
    
    @GetMapping("/sensorHumedad")
    public String sensorHumedad() {
        return "sensorHumedad";
    }

    @GetMapping("/editarUsuario")
    public String editarUsuario() {
        return "editarUsuario";
    }

}
