package com.example.trabalhofinal.services;

import com.example.trabalhofinal.models.Aplicativo;
import com.example.trabalhofinal.repositories.AplicativoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AplicativoService {
   
    @Autowired
    private AplicativoRepository aplicativoRepository;

    public List<Aplicativo> getAllAplicativos() {
        return aplicativoRepository.findAll();
    }

    public Aplicativo saveAplicativo(Aplicativo aplicativo) {
        return aplicativoRepository.save(aplicativo);
    }

    public Aplicativo updateAplicativo(Aplicativo aplicativo) {
        return aplicativoRepository.save(aplicativo);
    }

    public void deleteAplicativo(Long id) {
        aplicativoRepository.deleteById(id);
    }

    public Aplicativo atualizarCustoMensal(Long idAplicativo, double novoCusto) {
        Aplicativo aplicativo = aplicativoRepository.findById(idAplicativo) 
        .orElseThrow(() -> new RuntimeException("Aplicativo não encontrado"));
        aplicativo.setCustoMensal(novoCusto);
        return aplicativoRepository.save(aplicativo);
    }
}
