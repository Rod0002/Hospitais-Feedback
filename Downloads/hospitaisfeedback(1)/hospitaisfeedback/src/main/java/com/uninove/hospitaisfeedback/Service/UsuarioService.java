package com.uninove.hospitaisfeedback.Service;

import com.uninove.hospitaisfeedback.Model.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    List<Usuario> listarUsuarios();

    Usuario criarUsuario(Usuario usuario);

    Optional<Usuario> obterUsuario(Long id);

    Optional<Usuario> autenticar(String email, String senha);

    void deletarUsuario(Long id);

    // Método para buscar usuário pelo email
    Optional<Usuario> obterUsuarioPorEmail(String email);
}
