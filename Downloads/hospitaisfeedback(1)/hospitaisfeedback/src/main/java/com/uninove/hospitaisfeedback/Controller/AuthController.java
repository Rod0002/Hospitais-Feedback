package com.uninove.hospitaisfeedback.Controller;

import com.uninove.hospitaisfeedback.DTO.UsuarioLoginDTO;
import com.uninove.hospitaisfeedback.Model.Usuario;
import com.uninove.hospitaisfeedback.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UsuarioLoginDTO usuarioLoginDTO, HttpSession session) {
        Optional<Usuario> usuario = usuarioService.autenticar(usuarioLoginDTO.getEmail(), usuarioLoginDTO.getSenha());
        if (usuario.isPresent()) {
            // Armazenar o usuário na sessão
            session.setAttribute("usuarioLogado", usuario.get());
            return ResponseEntity.ok("Login bem-sucedido");
        } else {
            return ResponseEntity.status(401).body("Credenciais inválidas");
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        // Remover o usuário da sessão
        session.invalidate();
        return ResponseEntity.ok("Logout realizado com sucesso");
    }
    
    @GetMapping("/usuario-logado")
    public ResponseEntity<Usuario> obterUsuarioLogado(HttpSession session) {
        // Recupera o usuário logado da sessão
        Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");

        // Caso não tenha usuário na sessão, retorna 401
        if (usuarioLogado == null) {
            return ResponseEntity.status(401).build();
        }

        // Retorna o usuário logado
        return ResponseEntity.ok(usuarioLogado);
    }
}
