package com.example.trabalhofinal.services;

import com.example.trabalhofinal.dto.AssinaturaDTO;
import com.example.trabalhofinal.models.Aplicativo;
import com.example.trabalhofinal.models.Assinatura;
import com.example.trabalhofinal.models.Cliente;
import com.example.trabalhofinal.repositories.AplicativoRepository;
import com.example.trabalhofinal.repositories.AssinaturaRepository;
import com.example.trabalhofinal.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AssinaturaService {
    @Autowired
    private AssinaturaRepository assinaturaRepository;
    private ClienteRepository ClienteRepository;
    private AplicativoRepository AplicativoRepository;


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


    public Assinatura saveAssinaturaWithCliAndAppIDs(Long clienteId, Long aplicativoId) {
        Cliente cliente = ClienteRepository.findById(clienteId).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        Aplicativo aplicativo = AplicativoRepository.findById(aplicativoId).orElseThrow(() -> new RuntimeException("Aplicativo não encontrado"));

        Assinatura assinatura = new Assinatura();
        assinatura.setCliente(cliente);
        assinatura.setAplicativo(aplicativo);
        assinatura.setInicioVigencia(LocalDate.now());
        assinatura.setFimVigencia(LocalDate.now().plusMonths(1));

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
            return a.getCliente().getId().equals(clienteId) && a.getFimVigencia().isAfter(LocalDate.now()); //checar
        }
        return false;
    }

    public boolean isAssinaturaValida(Long codassin) {
        Optional<Assinatura> assinaturaOpt = assinaturaRepository.findById(codassin);
        if (assinaturaOpt.isPresent()) {
            Assinatura assinatura = assinaturaOpt.get();
            return assinatura.getFimVigencia().isAfter(LocalDate.now());
        }
        return false;
    }

    public List<AssinaturaDTO> getAssinaturasPorTipo(String tipo) {
        List<Assinatura> assinaturas;
        switch (tipo.toUpperCase()) {
            case "ATIVAS":
                assinaturas = assinaturaRepository.findByFimVigenciaAfter(LocalDate.now());
                break;
            case "CANCELADAS":
                assinaturas = assinaturaRepository.findByFimVigenciaBefore(LocalDate.now());
                break;
            case "TODAS":
            default:
                assinaturas = assinaturaRepository.findAll();
                break;
        }

        return assinaturas.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private AssinaturaDTO convertToDTO(Assinatura assinatura) {
        String status = assinatura.getFimVigencia().isAfter(LocalDate.now()) ? "ATIVA" : "CANCELADA";
        return new AssinaturaDTO(
                assinatura.getId(),
                assinatura.getCliente().getId(),
                assinatura.getAplicativo().getId(),
                assinatura.getInicioVigencia(),
                assinatura.getFimVigencia(),
                status
        );
    }


    public List<AssinaturaDTO> getAssinaturasPorCliente(Long clienteId) {
        List<Assinatura> assinaturas = assinaturaRepository.findByClienteId(clienteId);
        return assinaturas.stream().map(this::convertToDTO).collect(Collectors.toList()); //checar
    }

    public List<AssinaturaDTO> getAssinaturasPorAplicativo(Long aplicativoId) {
        List<Assinatura> assinaturas = assinaturaRepository.findByAplicativoId(aplicativoId); //checar
        return assinaturas.stream().map(this::convertToDTO).collect(Collectors.toList());
    }
}
