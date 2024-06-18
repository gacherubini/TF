package com.example.trabalhofinal.repositories;

import com.example.trabalhofinal.models.Assinatura;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssinaturaRepository extends JpaRepository<Assinatura, Long> {
    List<Assinatura> findByClienteId(Long clienteId);
    List<Assinatura> findByAplicativoId(Long aplicativoId);
}
