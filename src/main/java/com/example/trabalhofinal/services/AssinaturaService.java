package com.example.trabalhofinal.services;

import com.example.trabalhofinal.models.Assinatura;
import com.example.trabalhofinal.repositories.AssinaturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class AssinaturaService {
    @Autowired
    private AssinaturaRepository assinaturaRepository;

    public List<Assinatura> getAllAssinaturas() {
        return assinaturaRepository.findAll();
    }

    public List<Assinatura> getAssinaturasByClienteId(Long clienteId) {
        return assinaturaRepository.findByClienteId(clienteId);
    }

    public List<Assinatura> getAssinaturasByAplicativoId(Long aplicativoId) {
        return assinaturaRepository.findByAplicativoId(aplicativoId);
    }

    public Assinatura saveAssinatura(Assinatura assinatura) {
        return assinaturaRepository.save(assinatura);
    }

    public Assinatura updateAssinatura(Assinatura assinatura) {
        return assinaturaRepository.save(assinatura);
    }

    public void deleteAssinatura(Long id) {
        assinaturaRepository.deleteById(id);
    }

    public void updateAssinaturaVigencia(Long id, LocalDate inicioVigencia, LocalDate fimVigencia) {
        Assinatura assinatura = assinaturaRepository.findById(id).orElseThrow();
        assinatura.setInicioVigencia(inicioVigencia);
        assinatura.setFimVigencia(fimVigencia);
        assinaturaRepository.save(assinatura);
    }

    public boolean isAssinaturaValida(Long clienteId, Long assinaturaId) {
        Optional<Assinatura> assinatura = assinaturaRepository.findById(assinaturaId);
        if (assinatura.isPresent()) {
            Assinatura a = assinatura.get();
            return a.getCliente().getId().equals(clienteId) && a.getFimVigencia().isAfter(LocalDate.now());
        }
        return false;
    }
}
