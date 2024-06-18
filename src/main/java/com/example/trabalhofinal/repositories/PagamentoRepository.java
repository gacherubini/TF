package com.example.trabalhofinal.repositories;

import com.example.trabalhofinal.models.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {
}
