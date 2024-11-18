package com.uninove.hospitaisfeedback.Repository;

import com.uninove.hospitaisfeedback.Model.Usuario;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
    boolean existsByEmail(String email);
    Optional<Usuario> findByNome(String nome);

}

