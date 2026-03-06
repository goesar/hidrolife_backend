/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hidrolife.beta.service;

import com.hidrolife.beta.dto.UsuarioDTO;
import com.hidrolife.beta.model.Usuario;

import java.util.List;

/**
 *
 * @author Esteban
 */
public interface IUsuarioService {

    public void saveUsuario(UsuarioDTO dto);

    public void desactivarUsuario(Long id);

    public Usuario obtenerUsuario(Long id);

    Object obtenerUsuarioPorEmail(String email);

    String login(String email, String password);

    public List<Usuario> buscar(String criterio, String valor);
    public List<Usuario> listarTodo();

    public void actualizarPerfil(String emailActual, UsuarioDTO dto);
}
