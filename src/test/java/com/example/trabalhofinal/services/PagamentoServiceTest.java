package com.example.trabalhofinal.services;

import com.example.trabalhofinal.dto.PagamentoRespostaDTO;
import com.example.trabalhofinal.dto.RegistrarPagamentoDTO;
import com.example.trabalhofinal.models.Assinatura;
import com.example.trabalhofinal.models.Aplicativo;
import com.example.trabalhofinal.models.Promocao;
import com.example.trabalhofinal.repositories.AssinaturaRepository;
import com.example.trabalhofinal.repositories.PromocaoRepository;
import com.example.trabalhofinal.repositories.PagamentoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PagamentoServiceTest {

    @InjectMocks
    private PagamentoService pagamentoService;

    @Mock
    private PagamentoRepository pagamentoRepository;

    @Mock
    private AssinaturaRepository assinaturaRepository;

    @Mock
    private PromocaoRepository promocaoRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRegistrarPagamento_AssinaturaNaoEncontrada() {
        RegistrarPagamentoDTO registrarPagamentoDTO = new RegistrarPagamentoDTO();
        registrarPagamentoDTO.setCodass(1L);
        when(assinaturaRepository.findById(1L)).thenReturn(Optional.empty());

        PagamentoRespostaDTO resposta = pagamentoService.registrarPagamento(registrarPagamentoDTO);

        assertEquals("ASSINATURA_NAO_ENCONTRADA", resposta.getStatus());
        assertEquals("", resposta.getData());
        assertEquals(0, resposta.getValorEstornado());
    }

    @Test
    public void testRegistrarPagamento_ValorIncorreto() {
        Aplicativo aplicativo = new Aplicativo("App Teste", 100.0);
        Assinatura assinatura = new Assinatura();
        assinatura.setId(1L);
        assinatura.setAplicativo(aplicativo);

        RegistrarPagamentoDTO registrarPagamentoDTO = new RegistrarPagamentoDTO();
        registrarPagamentoDTO.setCodass(1L);
        registrarPagamentoDTO.setValorPago(50.0);
        registrarPagamentoDTO.setAno(2023);
        registrarPagamentoDTO.setMes(6);
        registrarPagamentoDTO.setDia(25);

        when(assinaturaRepository.findById(1L)).thenReturn(Optional.of(assinatura));

        PagamentoRespostaDTO resposta = pagamentoService.registrarPagamento(registrarPagamentoDTO);

        assertEquals("VALOR_INCORRETO", resposta.getStatus());
        assertEquals("2023-06-25", resposta.getData());
        assertEquals(50.0, resposta.getValorEstornado());
    }

    @Test
    public void testRegistrarPagamento_PromocaoNaoAplicavel() {
        Aplicativo aplicativo = new Aplicativo("App Teste", 100.0);
        Assinatura assinatura = new Assinatura();
        assinatura.setId(1L);
        assinatura.setAplicativo(aplicativo);

        RegistrarPagamentoDTO registrarPagamentoDTO = new RegistrarPagamentoDTO();
        registrarPagamentoDTO.setCodass(1L);
        registrarPagamentoDTO.setValorPago(90.0);
        registrarPagamentoDTO.setAno(2023);
        registrarPagamentoDTO.setMes(6);
        registrarPagamentoDTO.setDia(25);
        registrarPagamentoDTO.setCodPromocao(1L);

        Promocao promocao = new Promocao();
        promocao.setId(1L);
        promocao.setDesconto(0.1);
        promocao.setValidade(LocalDate.of(2023, 6, 20)); // Promoção expirada

        when(assinaturaRepository.findById(1L)).thenReturn(Optional.of(assinatura));
        when(promocaoRepository.findById(1L)).thenReturn(Optional.of(promocao));

        PagamentoRespostaDTO resposta = pagamentoService.registrarPagamento(registrarPagamentoDTO);

        assertEquals("PROMOCAO_NAO_APLICAVEL", resposta.getStatus());
        assertEquals("2023-06-25", resposta.getData());
        assertEquals(90.0, resposta.getValorEstornado());
    }

    @Test
    public void testRegistrarPagamento_PagamentoOk() {
        Aplicativo aplicativo = new Aplicativo("App Teste", 100.0);
        Assinatura assinatura = new Assinatura();
        assinatura.setId(1L);
        assinatura.setAplicativo(aplicativo);
        assinatura.setFimVigencia(LocalDate.of(2023, 6, 30));

        RegistrarPagamentoDTO registrarPagamentoDTO = new RegistrarPagamentoDTO();
        registrarPagamentoDTO.setCodass(1L);
        registrarPagamentoDTO.setValorPago(100.0);
        registrarPagamentoDTO.setAno(2023);
        registrarPagamentoDTO.setMes(6);
        registrarPagamentoDTO.setDia(25);

        when(assinaturaRepository.findById(1L)).thenReturn(Optional.of(assinatura));
        when(pagamentoRepository.save(any())).thenReturn(null);

        PagamentoRespostaDTO resposta = pagamentoService.registrarPagamento(registrarPagamentoDTO);

        assertEquals("PAGAMENTO_OK", resposta.getStatus());
        assertEquals("2023-07-30", resposta.getData());
        assertEquals(0, resposta.getValorEstornado());

        verify(assinaturaRepository, times(1)).save(any(Assinatura.class));
//        verify(pagamentoRepository, times(1)).save(any(Pagamento.class));
    }
}
