package com.example.trabalhofinal.services;

import com.example.trabalhofinal.dto.PagamentoRespostaDTO;
import com.example.trabalhofinal.dto.RegistrarPagamentoDTO;
import com.example.trabalhofinal.models.Assinatura;
import com.example.trabalhofinal.models.Promocao;
import com.example.trabalhofinal.models.Pagamento;
import com.example.trabalhofinal.repositories.AssinaturaRepository;
import com.example.trabalhofinal.repositories.PromocaoRepository;
import com.example.trabalhofinal.repositories.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class PagamentoService {

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private AssinaturaRepository assinaturaRepository;

    @Autowired
    private PromocaoRepository promocaoRepository;

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

    public PagamentoRespostaDTO registrarPagamento(RegistrarPagamentoDTO registrarPagamentoDTO) {
        Optional<Assinatura> assinaturaOpt = assinaturaRepository.findById(registrarPagamentoDTO.getCodass());
        PagamentoRespostaDTO resposta = new PagamentoRespostaDTO();

        if (!assinaturaOpt.isPresent()) {
            resposta.setStatus("ASSINATURA_NAO_ENCONTRADA");
            resposta.setData("");
            resposta.setValorEstornado(0);
            return resposta;
        }

        Assinatura assinatura = assinaturaOpt.get();
        double valorEsperado = assinatura.getAplicativo().getCustoMensal();
        LocalDate dataPagamento = LocalDate.of(registrarPagamentoDTO.getAno(), registrarPagamentoDTO.getMes(), registrarPagamentoDTO.getDia());

        int diasExtras = 0;
        if (registrarPagamentoDTO.getCodPromocao() != null) {
            Optional<Promocao> promocaoOpt = promocaoRepository.findById(registrarPagamentoDTO.getCodPromocao());
            if (promocaoOpt.isPresent()) {
                Promocao promocao = promocaoOpt.get();
                if (!promocao.isAtiva() || promocao.getValidade().isBefore(dataPagamento)) {
                    resposta.setStatus("PROMOCAO_NAO_APLICAVEL");
                    resposta.setData(dataPagamento.format(DateTimeFormatter.ISO_DATE));
                    resposta.setValorEstornado(registrarPagamentoDTO.getValorPago());
                    return resposta;
                }
                diasExtras = promocao.getDiasExtras();
                valorEsperado = valorEsperado - (valorEsperado * promocao.getDesconto());
            }
        }

        // Comparação de valores monetários com tolerância
        if (Math.abs(registrarPagamentoDTO.getValorPago() - valorEsperado) > 0.01) {
            resposta.setStatus("VALOR_INCORRETO");
            resposta.setData(dataPagamento.format(DateTimeFormatter.ISO_DATE));
            resposta.setValorEstornado(registrarPagamentoDTO.getValorPago());
            return resposta;
        }

        Pagamento pagamento = new Pagamento();
        pagamento.setAssinatura(assinatura);
        pagamento.setValorPago(registrarPagamentoDTO.getValorPago());
        pagamento.setDataPagamento(dataPagamento);
        pagamentoRepository.save(pagamento);

        LocalDate novaDataDeFimDeVigencia;
        if (assinatura.getFimVigencia().isBefore(dataPagamento)) {
            novaDataDeFimDeVigencia = dataPagamento.plusDays(30 + diasExtras);
        } else {
            novaDataDeFimDeVigencia = assinatura.getFimVigencia().plusDays(30 + diasExtras);
        }

        assinatura.setFimVigencia(novaDataDeFimDeVigencia);
        assinaturaRepository.save(assinatura);

        resposta.setStatus("PAGAMENTO_OK");
        resposta.setData(assinatura.getFimVigencia().format(DateTimeFormatter.ISO_DATE));
        resposta.setValorEstornado(0);

        return resposta;
    }
}
