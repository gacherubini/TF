package com.example.trabalhofinal.services;

import com.example.trabalhofinal.dto.AssinaturaDTO;
import com.example.trabalhofinal.helpers.helpers;
import com.example.trabalhofinal.models.Aplicativo;
import com.example.trabalhofinal.models.Assinatura;
import com.example.trabalhofinal.models.Cliente;
import com.example.trabalhofinal.repositories.AplicativoRepository;
import com.example.trabalhofinal.repositories.AssinaturaRepository;
import com.example.trabalhofinal.repositories.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AssinaturaServiceTest {

    @Mock
    private AssinaturaRepository assinaturaRepository;

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private AplicativoRepository aplicativoRepository;

    @InjectMocks
    private AssinaturaService assinaturaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllAssinaturas() {
        Assinatura assinatura1 = new Assinatura();
        assinatura1.setId(1L);
        Assinatura assinatura2 = new Assinatura();
        assinatura2.setId(2L);

        when(assinaturaRepository.findAll()).thenReturn(Arrays.asList(assinatura1, assinatura2));

        List<Assinatura> assinaturas = assinaturaService.getAllAssinaturas();

        assertEquals(2, assinaturas.size());
        verify(assinaturaRepository, times(1)).findAll();
    }

    @Test
    void testSaveAssinaturaWithCliAndAppIDs() {
        Long clienteId = 1L;
        Long aplicativoId = 1L;
        Cliente cliente = new Cliente();
        cliente.setId(clienteId);
        Aplicativo aplicativo = new Aplicativo();
        aplicativo.setId(aplicativoId);

        when(clienteRepository.findById(clienteId)).thenReturn(Optional.of(cliente));
        when(aplicativoRepository.findById(aplicativoId)).thenReturn(Optional.of(aplicativo));
        when(assinaturaRepository.findByClienteIdAndAplicativoId(clienteId, aplicativoId)).thenReturn(Optional.empty());
        when(assinaturaRepository.save(any(Assinatura.class))).thenReturn(new Assinatura());

        Assinatura savedAssinatura = assinaturaService.saveAssinaturaWithCliAndAppIDs(clienteId, aplicativoId);

        assertNotNull(savedAssinatura);
        verify(clienteRepository, times(1)).findById(clienteId);
        verify(aplicativoRepository, times(1)).findById(aplicativoId);
        verify(assinaturaRepository, times(1)).save(any(Assinatura.class));
    }

    @Test
    void testUpdateAssinaturaVigencia() {
        Long assinaturaId = 1L;
        LocalDate inicioVigencia = LocalDate.now();
        LocalDate fimVigencia = LocalDate.now().plusMonths(1);
        Assinatura assinatura = new Assinatura();
        assinatura.setId(assinaturaId);

        when(assinaturaRepository.findById(assinaturaId)).thenReturn(Optional.of(assinatura));
        when(assinaturaRepository.save(assinatura)).thenReturn(assinatura);

        assinaturaService.updateAssinaturaVigencia(assinaturaId, inicioVigencia, fimVigencia);

        assertEquals(inicioVigencia, assinatura.getInicioVigencia());
        assertEquals(fimVigencia, assinatura.getFimVigencia());
        verify(assinaturaRepository, times(1)).findById(assinaturaId);
        verify(assinaturaRepository, times(1)).save(assinatura);
    }
}
