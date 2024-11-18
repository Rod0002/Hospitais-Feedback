package com.uninove.hospitaisfeedback.Controller;

import com.uninove.hospitaisfeedback.DTO.UsuarioLoginDTO;
import com.uninove.hospitaisfeedback.Model.Usuario;
import com.uninove.hospitaisfeedback.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // 1. Listar todos os usuários
    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        List<Usuario> usuarios = usuarioService.listarUsuarios();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    // 2. Adicionar um novo usuário
    @PostMapping
    public ResponseEntity<?> criarUsuario(@RequestBody Usuario usuario) {
        try {
            Usuario novoUsuario = usuarioService.criarUsuario(usuario);
            return new ResponseEntity<>(novoUsuario, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            // Retorna um erro amigável em caso de duplicidade
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    // 3. Obter usuário específico por ID
    @GetMapping("/{usuarioId}")
    public ResponseEntity<Usuario> obterUsuario(@PathVariable Long usuarioId) {
        return usuarioService.obterUsuario(usuarioId)
                .map(usuario -> new ResponseEntity<>(usuario, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    // 4. Deletar um usuário
    @DeleteMapping("/{usuarioId}")
    public ResponseEntity<String> deletarUsuario(@PathVariable Long usuarioId) {
        try {
            usuarioService.deletarUsuario(usuarioId);
            return new ResponseEntity<>("Usuário deletado com sucesso.", HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    
    // 5. Autenticar um usuário
    @PostMapping("/login")
    public ResponseEntity<String> autenticarUsuario(@RequestBody UsuarioLoginDTO usuarioLoginDTO) {
        Optional<Usuario> usuarioOptional = usuarioService.obterUsuarioPorEmail(usuarioLoginDTO.getEmail());

        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            if (usuario.getSenha().equals(usuarioLoginDTO.getSenha())) {
                return ResponseEntity.ok("Login realizado com sucesso!");
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Email ou senha incorretos.");
    }
}
