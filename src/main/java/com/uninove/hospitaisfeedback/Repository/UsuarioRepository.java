package com.uninove.hospitaisfeedback.Repository;

import com.uninove.hospitaisfeedback.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
