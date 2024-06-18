package com.example.trabalhofinal.services;

import com.example.trabalhofinal.models.Assinatura;
import com.example.trabalhofinal.repositories.AssinaturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

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

    public void updateAssinaturaValidade(Long id, LocalDate validade) {
        Assinatura assinatura = assinaturaRepository.findById(id).orElseThrow();
        assinatura.setValidade(validade);
        assinaturaRepository.save(assinatura);
    }
}
