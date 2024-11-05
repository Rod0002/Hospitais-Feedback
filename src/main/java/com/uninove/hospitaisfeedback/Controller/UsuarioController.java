package com.uninove.hospitaisfeedback.Controller;

import com.uninove.hospitaisfeedback.Model.Usuario;
import com.uninove.hospitaisfeedback.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<Usuario> criarUsuario(@RequestBody Usuario usuario) {
        Usuario novoUsuario = usuarioService.criarUsuario(usuario); // Passando o objeto Usuario
        return new ResponseEntity<>(novoUsuario, HttpStatus.CREATED);
    }

    // 3. Obter usuário específico
    @GetMapping("/{usuarioId}")
    public ResponseEntity<Usuario> obterUsuario(@PathVariable Long usuarioId) {
        return usuarioService.obterUsuario(usuarioId)
                .map(usuario -> new ResponseEntity<>(usuario, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
