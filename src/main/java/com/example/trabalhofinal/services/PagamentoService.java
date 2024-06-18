package com.example.trabalhofinal.services;

import com.example.trabalhofinal.models.Assinatura;
import com.example.trabalhofinal.models.Pagamento;
import com.example.trabalhofinal.repositories.AssinaturaRepository;
import com.example.trabalhofinal.repositories.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class PagamentoService {
    @Autowired
    private PagamentoRepository pagamentoRepository;

    private AssinaturaRepository assinaturaRepository;


    public List<Pagamento> getAllPagamentos() {
        return pagamentoRepository.findAll();
    }

    public Pagamento savePagamento(Pagamento pagamento) {
        return pagamentoRepository.save(pagamento);
    }

    public Pagamento updatePagamento(Pagamento pagamento) {
        return pagamentoRepository.save(pagamento);
    }

    public void deletePagamento(Long id) {
        pagamentoRepository.deleteById(id);
    }

    public void processarNotificacaoPagamento(Pagamento pagamento) {
        pagamentoRepository.save(pagamento);

        Optional<Assinatura> assinaturaOpt = assinaturaRepository.findById(pagamento.getAssinatura().getId());
        if (assinaturaOpt.isPresent()) {
            Assinatura assinatura = assinaturaOpt.get();
            assinatura.setFimVigencia(assinatura.getFimVigencia().plusMonths(1));
            assinaturaRepository.save(assinatura);
        }
    }
}
