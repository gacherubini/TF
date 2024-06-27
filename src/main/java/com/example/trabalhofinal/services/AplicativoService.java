package com.example.trabalhofinal.services;

import com.example.trabalhofinal.models.Aplicativo;
import com.example.trabalhofinal.models.Assinatura;
import com.example.trabalhofinal.models.Pagamento;
import com.example.trabalhofinal.repositories.AplicativoRepository;
import com.example.trabalhofinal.repositories.AssinaturaRepository;
import com.example.trabalhofinal.repositories.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AplicativoService {

    @Autowired
    private AssinaturaRepository assinaturaRepository;

    @Autowired
    private PagamentoRepository pagamentoRepository;
   
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
        List<Assinatura> assinaturas = assinaturaRepository.findByAplicativoId(id);
        for (Assinatura assinatura : assinaturas) {
            List<Pagamento> pagamentos = pagamentoRepository.findByAssinaturaId(assinatura.getId());
            for (Pagamento pagamento : pagamentos) {
                pagamentoRepository.deleteById(pagamento.getId());
            }
            assinaturaRepository.deleteById(assinatura.getId());
        }
        aplicativoRepository.deleteById(id);
    }
    public Aplicativo atualizarCustoMensal(Long idAplicativo, double novoCusto) {
        Aplicativo aplicativo = aplicativoRepository.findById(idAplicativo) 
        .orElseThrow(() -> new RuntimeException("Aplicativo não encontrado"));
        aplicativo.setCustoMensal(novoCusto);
        return aplicativoRepository.save(aplicativo);
    }
}
