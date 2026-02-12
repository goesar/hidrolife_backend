/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hidrolife.beta.service;

import com.hidrolife.beta.model.Usuario;
import java.util.List;

/**
 *
 * @author Esteban
 */
public interface IUsuarioService {
    public List<Usuario> getUsuarios();
    public void saveUsuario(Usuario usuario);
    public void deleteUsuario(Long id);
    public Usuario findUsuario(Long id);
    String login(String email, String password);
}
