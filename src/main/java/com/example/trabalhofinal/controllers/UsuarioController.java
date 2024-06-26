package com.example.trabalhofinal.controllers;

import com.example.trabalhofinal.models.Usuario;
import com.example.trabalhofinal.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/servcad")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/servcad")
    public List<Usuario> getAllUsuarios() {
        return usuarioService.getAllUsuarios();
    }

    @PostMapping("/usuarios")
    public Usuario saveUsuario(@RequestBody Usuario usuario) {
        return usuarioService.saveUsuario(usuario);
    }

    @PutMapping("usuarios/{id}")
    public Usuario updateUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        usuario.setId(id);
        return usuarioService.updateUsuario(usuario);
    }

    @DeleteMapping("usuarios/{id}")
    public void deleteUsuario(@PathVariable Long id) {
        usuarioService.deleteUsuario(id);
    }

    @PostMapping("usuarios/isvalido")
    public boolean isUsuarioValido(@RequestBody String identificador) {
        return usuarioService.isUsuarioValido(identificador);
    }
}
