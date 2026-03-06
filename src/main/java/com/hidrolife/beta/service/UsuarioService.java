/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hidrolife.beta.service;

import com.hidrolife.beta.dto.UsuarioDTO;
import com.hidrolife.beta.model.Rol;
import com.hidrolife.beta.model.Usuario;
import com.hidrolife.beta.repository.UsuarioRepository;

import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UsuarioService implements IUsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void saveUsuario(UsuarioDTO dto) {

        if (!dto.getPassword().equals(dto.getConfirmarPassword())) {
            throw new IllegalArgumentException("Las contraseñas no coinciden");
        }

        if (!dto.getEmail().equals(dto.getConfirmarEmail())) {
            throw new IllegalArgumentException("Los correos no coinciden ");
        }

        Usuario usuario = new Usuario();
        usuario.setNombre(dto.getNombre());
        usuario.setPassword(passwordEncoder.encode(dto.getPassword()));
        usuario.setEmail(dto.getEmail());
        usuario.setTelefono(dto.getTelefono());
        usuario.setRol(Rol.USER);
        usuario.setActivo(true);

        usuarioRepository.save(usuario);
    }

    @Override
    public void desactivarUsuario(Long id) {
        Usuario usuario = obtenerUsuario(id);
        usuario.setActivo(false);
        usuarioRepository.save(usuario);
    }

    @Override
    public Usuario obtenerUsuario(Long id) {
        return usuarioRepository.findByIdUsuarioAndActivoTrue(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    @Override
    public Usuario obtenerUsuarioPorEmail(String email) {
        return usuarioRepository.findByEmailAndActivoTrue(email)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    @Override
    public String login(String email, String password) {
        Optional<Usuario> usuario = usuarioRepository.findByEmail(email);

        if (usuario.isEmpty()) {
            return "El correo no está registrado";
        }

        // Contraseña SIN ENCRIPTAR (por ahora)
        if (!usuario.get().getPassword().equals(password)) {
            return "Contraseña incorrecta";
        }

        return "OK"; // todo correcto
    }

    @Override
    public List<Usuario> buscar(String criterio, String valor) {

        switch (criterio) {

            case "id":
                try {
                    Long idUsuario = Long.parseLong(valor);
                    return usuarioRepository.findById(idUsuario).map(List::of).orElse(List.of());
                } catch (NumberFormatException e) {
                    return List.of();
                }

            case "nombre":
                return usuarioRepository.findByNombreContainingIgnoreCaseAndActivoTrue(valor);

            case "telefono":
                return usuarioRepository.findByTelefonoAndActivoTrue(valor);

            case "email":
                return usuarioRepository.findByEmailContainingIgnoreCaseAndActivoTrue(valor);


            default:
                return List.of();
        }
    }

    public List<Usuario> listarTodo() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        boolean esAdmin = auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        if (esAdmin) {
            return usuarioRepository.findAll();
        } else {
            return usuarioRepository.findByActivoTrue();
        }
    }


    @Override
    public void actualizarPerfil(String emailActual, UsuarioDTO dto) {

        Usuario usuarioBD = usuarioRepository
                .findByEmailAndActivoTrue(emailActual)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Actualizar campos normales
        usuarioBD.setNombre(dto.getNombre());
        usuarioBD.setTelefono(dto.getTelefono());

        usuarioBD.setEmail(dto.getEmail());


        // 🔐 Actualizar contraseña solo si escribió algo
        if (dto.getPassword() != null &&
                !dto.getPassword().isBlank()) {
            if (!dto.getPassword().equals(dto.getConfirmarPassword())) {
                throw new IllegalArgumentException("Las contraseñas no coinciden");
            }


            usuarioBD.setPassword(
                    passwordEncoder.encode(dto.getPassword())
            );
        }

        usuarioRepository.save(usuarioBD);
    }


    public void eliminarPorId(Long idUsuario) {
        if (!usuarioRepository.existsById(idUsuario)) {
            throw new IllegalArgumentException("No existe usuario con id: " + idUsuario);
        }
        usuarioRepository.deleteById(idUsuario);
    }
}

