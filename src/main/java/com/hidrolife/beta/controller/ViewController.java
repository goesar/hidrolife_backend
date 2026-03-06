package com.hidrolife.beta.controller;

import com.hidrolife.beta.dto.LecturaDTO;
import com.hidrolife.beta.dto.UsuarioDTO;
import com.hidrolife.beta.model.*;

import com.hidrolife.beta.service.ConfiguracionSensorService;
import com.hidrolife.beta.service.CultivoService;

import com.hidrolife.beta.service.LecturaService;
import com.hidrolife.beta.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class ViewController {

    private final ConfiguracionSensorService configuracionSensorService;
    private final CultivoService cultivoService;
    private final LecturaService lecturaService;
    private final UsuarioService usuarioService;


    public ViewController(ConfiguracionSensorService configuracionSensorService, CultivoService cultivoService, LecturaService lecturaService, UsuarioService usuarioService) {
        this.configuracionSensorService = configuracionSensorService;
        this.cultivoService = cultivoService;
        this.lecturaService = lecturaService;
        this.usuarioService = usuarioService;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/nuevoUsuario")
    public String nuevoUsuario(Model modelo) {
        modelo.addAttribute("usuario", new UsuarioDTO());
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
    public String sensorPH(Model model) {

        LecturaDTO lectura = lecturaService.obtenerUltimaLectura();
        ConfiguracionSensor config =
                configuracionSensorService.obtenerPorTipo(TipoSensor.PH);

        model.addAttribute("config", config);
        model.addAttribute("lectura", lectura);
        return "sensorPH";
    }

    @GetMapping("/sensorTDS")
    public String sensorTDS(Model model) {

        LecturaDTO lectura = lecturaService.obtenerUltimaLectura();
        ConfiguracionSensor config = configuracionSensorService.obtenerPorTipo(TipoSensor.TDS);

        model.addAttribute("config", config);
        model.addAttribute("lectura",lectura);
        return "sensorTDS";
    }

    @GetMapping("/sensorTemperatura")
    public String sensorTemperatura(Model model) {
        LecturaDTO lectura = lecturaService.obtenerUltimaLectura();
        ConfiguracionSensor config = configuracionSensorService.obtenerPorTipo(TipoSensor.TEMPERATURA);

        model.addAttribute("config", config);
        model.addAttribute("lectura",lectura);
        return "sensorTemperatura";
    }
    
    @GetMapping("/sensorHumedad")
    public String sensorHumedad(Model model) {
        LecturaDTO lectura = lecturaService.obtenerUltimaLectura();
        ConfiguracionSensor config = configuracionSensorService.obtenerPorTipo(TipoSensor.HUMEDAD);

        model.addAttribute("config", config);
        model.addAttribute("lectura",lectura);
        return "sensorHumedad";
    }

    @GetMapping("/editarUsuario")
    public String editarUsuario(Model model, Principal principal) {
        String email = principal.getName(); // Spring guarda el username aquí

        Usuario usuario = usuarioService.obtenerUsuarioPorEmail(email);

        UsuarioDTO dto = new UsuarioDTO();
        dto.setNombre(usuario.getNombre());
        dto.setEmail(usuario.getEmail());
        dto.setTelefono(usuario.getTelefono());

        model.addAttribute("usuario", dto);

        return "editarUsuario";
    }

}
