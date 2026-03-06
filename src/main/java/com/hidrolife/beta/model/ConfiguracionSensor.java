package com.hidrolife.beta.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(
        name = "configuracion_sensor",
        uniqueConstraints = @UniqueConstraint(columnNames = {"id_cultivo", "tipo"})
)
public class ConfiguracionSensor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idConfigSensor;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_cultivo", nullable = false)
    private Cultivo cultivo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoSensor tipo;

    @Column(nullable = false)
    private Double valorMinimo;

    @Column(nullable = false)
    private Double valorMaximo;

    @Column(nullable = false)
    private boolean activo = true;
}