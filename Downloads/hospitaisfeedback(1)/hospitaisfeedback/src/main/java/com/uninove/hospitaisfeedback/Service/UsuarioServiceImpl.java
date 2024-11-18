package com.uninove.hospitaisfeedback.Service;

import com.uninove.hospitaisfeedback.Model.Usuario;
import com.uninove.hospitaisfeedback.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario criarUsuario(Usuario usuario) {
        // Verifica se já existe um usuário com o mesmo e-mail
        if (usuarioRepository.findByEmail(usuario.getEmail()).isPresent()) {
            throw new IllegalArgumentException("E-mail já está em uso.");
        }

        return usuarioRepository.save(usuario);
    }

    @Override
    public Optional<Usuario> obterUsuario(Long id) {
        return usuarioRepository.findById(id);
    }

    @Override
    public Optional<Usuario> autenticar(String email, String senha) {
        Optional<Usuario> usuario = usuarioRepository.findByEmail(email);
        if (usuario.isPresent() && usuario.get().getSenha().equals(senha)) {
            return usuario;
        }
        return Optional.empty();
    }

    @Override
    public void deletarUsuario(Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);

        if (usuario.isEmpty()) {
            throw new IllegalArgumentException("Usuário não encontrado com o ID: " + id);
        }

        usuarioRepository.deleteById(id);
    }

    @Override
    public Optional<Usuario> obterUsuarioPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }
}
