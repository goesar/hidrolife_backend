/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hidrolife.beta.repository;

import com.hidrolife.beta.model.Usuario;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Esteban
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);
    public List<Usuario> findAll();

    Optional<Usuario> findByNombre(String nombre);




    List<Usuario> findByNombreContainingIgnoreCaseAndActivoTrue(String valor);

    List<Usuario> findByTelefonoAndActivoTrue(String valor);

    List<Usuario> findByEmailContainingIgnoreCaseAndActivoTrue(String valor);


    Optional<Usuario> findByIdUsuarioAndActivoTrue(Long id);

    List<Usuario> findAllByActivoTrue();

    Optional<Usuario> findByEmailAndActivoTrue(String email);

    List<Usuario> findByActivoTrue();
}
