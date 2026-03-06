package com.hidrolife.beta.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;

    private String nombre;
    private String email;
    private String telefono;
    private String password;

    @Enumerated(EnumType.STRING)
    private Rol rol;
    private boolean activo;

    public Usuario() {
    }

    public Usuario(Long idUsuario, String nombre, String email, String telefono, String password, Rol rol, boolean activo) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.password = password;
        this.rol = rol;
        this.activo = activo;
    }

}
