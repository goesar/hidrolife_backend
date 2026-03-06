package com.hidrolife.beta.dto;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class UsuarioDTO {
    private String nombre;
    private String email;
    private String confirmarEmail;
    private String password;
    private String confirmarPassword;
    private String telefono;
}
