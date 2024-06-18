package com.example.trabalhofinal.repositories;

import com.example.trabalhofinal.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
