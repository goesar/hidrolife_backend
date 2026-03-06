
package com.hidrolife.beta.controller;

import com.hidrolife.beta.dto.LecturaDTO;
import com.hidrolife.beta.model.ConfiguracionSensor;
import com.hidrolife.beta.model.TipoSensor;
import com.hidrolife.beta.service.ConfiguracionSensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.hidrolife.beta.service.ILecturaService;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
@RequestMapping("/api/lecturas")
@CrossOrigin("*")
public class LecturaController {

    private final ConfiguracionSensorService configuracionSensorService;
    private final ILecturaService lecturaService;

    public LecturaController(ConfiguracionSensorService configuracionSensorService, ILecturaService lecturaService) {
        this.configuracionSensorService = configuracionSensorService;
        this.lecturaService = lecturaService;
    }


    @PostMapping("/guardar")
    public ResponseEntity<String> guardarLectura(@RequestBody LecturaDTO dto) {
        lecturaService.guardarLectura(dto);
        return ResponseEntity.ok("Lectura almacenada exitosamente");
    }



    @CrossOrigin(origins = "*")
    @GetMapping("/ultima")
    public LecturaDTO ultima() {
        return lecturaService.obtenerUltimaLectura();
    }


}
