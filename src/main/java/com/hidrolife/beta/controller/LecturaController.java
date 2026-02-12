
package com.hidrolife.beta.controller;

import com.hidrolife.beta.dto.LecturaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.hidrolife.beta.service.ILecturaService;

@RestController
@RequestMapping("/api/lecturas")
@CrossOrigin("*")
public class LecturaController {
    @Autowired
    private ILecturaService lecturaService;
    
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
