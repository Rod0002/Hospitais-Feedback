package com.uninove.hospitaisfeedback.Service;

import com.uninove.hospitaisfeedback.Model.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    List<Usuario> listarUsuarios();
    Usuario criarUsuario(Usuario usuario);
    Optional<Usuario> obterUsuario(Long id);
}

