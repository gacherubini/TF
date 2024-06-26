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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class PagamentoService {

    private static final Logger logger = LoggerFactory.getLogger(PagamentoService.class);


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
        logger.info("Iniciando registro de pagamento com dados: {}", registrarPagamentoDTO);

        Optional<Assinatura> assinaturaOpt = assinaturaRepository.findById(registrarPagamentoDTO.getCodass());
        PagamentoRespostaDTO resposta = new PagamentoRespostaDTO();

        if (!assinaturaOpt.isPresent()) {
            logger.error("Assinatura não encontrada com ID: {}", registrarPagamentoDTO.getCodass());
            resposta.setStatus("ASSINATURA_NAO_ENCONTRADA");
            resposta.setData("");
            resposta.setValorEstornado(0);
            return resposta;
        }

        Assinatura assinatura = assinaturaOpt.get();
        double valorEsperado = assinatura.getAplicativo().getCustoMensal();
        LocalDate dataPagamento = LocalDate.of(registrarPagamentoDTO.getAno(), registrarPagamentoDTO.getMes(), registrarPagamentoDTO.getDia());
        logger.info("Valor esperado: {}, Data de pagamento: {}", valorEsperado, dataPagamento);

        int diasExtras = 0;
        if (registrarPagamentoDTO.getCodPromocao() != null) {
            Optional<Promocao> promocaoOpt = promocaoRepository.findById(registrarPagamentoDTO.getCodPromocao());
            if (promocaoOpt.isPresent()) {
                Promocao promocao = promocaoOpt.get();
                if (!promocao.isAtiva() || promocao.getValidade().isBefore(dataPagamento)) {
                    logger.error("Promoção não aplicável. Promoção ativa: {}, Validade: {}", promocao.isAtiva(), promocao.getValidade());
                    resposta.setStatus("PROMOCAO_NAO_APLICAVEL");
                    resposta.setData(dataPagamento.format(DateTimeFormatter.ISO_DATE));
                    resposta.setValorEstornado(registrarPagamentoDTO.getValorPago());
                    return resposta;
                }
                diasExtras = promocao.getDiasExtras();
                valorEsperado = valorEsperado * (1 - promocao.getDesconto() / 100); // Corrigido o cálculo do desconto
                logger.info("Promoção aplicada. Dias extras: {}, Novo valor esperado: {}", diasExtras, valorEsperado);
            } else {
                logger.error("Promoção não encontrada com ID: {}", registrarPagamentoDTO.getCodPromocao());
            }
        }

        if (Math.abs(registrarPagamentoDTO.getValorPago() - valorEsperado) > 0.01) {
            logger.error("Valor pago incorreto. Valor pago: {}, Valor esperado: {}", registrarPagamentoDTO.getValorPago(), valorEsperado);
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
        logger.info("Pagamento salvo com sucesso: {}", pagamento);

        LocalDate novaDataDeFimDeVigencia;
        if (assinatura.getFimVigencia().isBefore(dataPagamento)) {
            novaDataDeFimDeVigencia = dataPagamento.plusDays(30 + diasExtras);
        } else {
            novaDataDeFimDeVigencia = assinatura.getFimVigencia().plusDays(30 + diasExtras);
        }
        assinatura.setFimVigencia(novaDataDeFimDeVigencia);
        assinaturaRepository.save(assinatura);
        logger.info("Assinatura atualizada com nova data de fim de vigência: {}", novaDataDeFimDeVigencia);

        resposta.setStatus("PAGAMENTO_OK");
        resposta.setData(assinatura.getFimVigencia().format(DateTimeFormatter.ISO_DATE));
        resposta.setValorEstornado(0);

        return resposta;
    }
}