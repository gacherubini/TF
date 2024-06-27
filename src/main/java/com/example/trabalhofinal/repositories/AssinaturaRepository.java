package com.example.trabalhofinal.repositories;

import com.example.trabalhofinal.models.Assinatura;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;



public interface AssinaturaRepository extends JpaRepository<Assinatura, Long> {
    List<Assinatura> findByClienteId(Long clienteId);
    List<Assinatura> findByAplicativoId(Long aplicativoId);
    List<Assinatura> findByFimVigenciaAfter(LocalDate date);
    List<Assinatura> findByFimVigenciaBefore(LocalDate date);
    Optional<Assinatura> findByClienteIdAndAplicativoId(Long clienteId, Long aplicativoId);

}
