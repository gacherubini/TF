package com.example.trabalhofinal.services;

import com.example.trabalhofinal.models.Promocao;
import com.example.trabalhofinal.repositories.PromocaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PromocaoService {

    @Autowired
    private PromocaoRepository promocaoRepository;

    public List<Promocao> getAllPromocoes() {
        return promocaoRepository.findAll();
    }

    public Promocao getPromocaoById(Long id) {
        Optional<Promocao> promocao = promocaoRepository.findById(id);
        return promocao.orElse(null);
    }

    public Promocao createPromocao(Promocao promocao) {
        return promocaoRepository.save(promocao);
    }

    public Promocao updatePromocao(Long id, Promocao promocao) {
        Promocao existingPromocao = getPromocaoById(id);
        if (existingPromocao != null) {
            promocao.setId(id);
            return promocaoRepository.save(promocao);
        }
        return null;
    }

    public void deletePromocao(Long id) {
        promocaoRepository.deleteById(id);
    }
}