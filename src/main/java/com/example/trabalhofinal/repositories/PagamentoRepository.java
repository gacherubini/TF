package com.example.trabalhofinal.repositories;

import com.example.trabalhofinal.models.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {
    List<Pagamento> findByClienteId(Long clienteId);
    List<Pagamento> findByAssinaturaId(Long assinaturaId);


}
