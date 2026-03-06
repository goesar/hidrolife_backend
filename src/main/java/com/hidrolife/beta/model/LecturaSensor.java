
package com.hidrolife.beta.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "lectura")
@Getter @Setter
public class LecturaSensor {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLectura;
    private Double humedad;
    private Double temperatura;
    private Double ph;
    private Double tds;
    private LocalDateTime fechaYHora;
    private boolean activo = true;
}
