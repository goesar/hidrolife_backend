/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hidrolife.beta.service;

import com.hidrolife.beta.model.Usuario;
import com.hidrolife.beta.repository.UsuarioRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements IUsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public List<Usuario> getUsuarios() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void saveUsuario(
            String nombre,
            String password,
       
            String email,
      
            String telefono) {

        // TODO OK: crear usuario
        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setPassword(password);  
        usuario.setEmail(email);
        usuario.setTelefono(telefono);

        usuarioRepository.save(usuario);
    }

    @Override
    public void saveUsuario(Usuario usuario) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void deleteUsuario(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Usuario findUsuario(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String login(String email, String password) {
       Usuario usuario = usuarioRepository.findByEmail(email);

        if (usuario == null) {
            return "El correo no está registrado";
        }

        // Contraseña SIN ENCRIPTAR (por ahora)
        if (!usuario.getPassword().equals(password)) {
            return "Contraseña incorrecta";
        }

        return "OK"; // todo correcto
    }

    public List<Usuario> listarTodo() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public List<Usuario> buscar(String criterio, String valor) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

