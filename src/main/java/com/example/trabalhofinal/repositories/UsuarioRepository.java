package com.example.trabalhofinal.repositories;

import com.example.trabalhofinal.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
