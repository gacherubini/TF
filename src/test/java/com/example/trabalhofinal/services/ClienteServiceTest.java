package com.example.trabalhofinal.services;

import com.example.trabalhofinal.models.Cliente;
import com.example.trabalhofinal.models.Assinatura;
import com.example.trabalhofinal.models.Pagamento;

import com.example.trabalhofinal.repositories.ClienteRepository;
import com.example.trabalhofinal.repositories.AssinaturaRepository;
import com.example.trabalhofinal.repositories.PagamentoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private PagamentoRepository pagamentoRepository;

    @Mock
    private AssinaturaRepository assinaturaRepository;

    @InjectMocks
    private ClienteService clienteService;

    private Cliente cliente1;
    private Cliente cliente2;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        cliente1 = new Cliente();
        cliente1.setId(1L);
        cliente1.setNome("Cliente 1");

        cliente2 = new Cliente();
        cliente2.setId(2L);
        cliente2.setNome("Cliente 2");
    }

    @Test
    public void testGetAllClientes() {
        List<Cliente> clientes = Arrays.asList(cliente1, cliente2);
        when(clienteRepository.findAll()).thenReturn(clientes);

        List<Cliente> result = clienteService.getAllClientes();
        assertEquals(2, result.size());
        assertEquals("Cliente 1", result.get(0).getNome());
        assertEquals("Cliente 2", result.get(1).getNome());
    }

    @Test
    public void testSaveCliente() {
        when(clienteRepository.save(cliente1)).thenReturn(cliente1);

        Cliente result = clienteService.saveCliente(cliente1);
        assertNotNull(result);
        assertEquals("Cliente 1", result.getNome());
    }

    @Test
    public void testUpdateCliente() {
        when(clienteRepository.save(cliente1)).thenReturn(cliente1);

        Cliente result = clienteService.updateCliente(cliente1);
        assertNotNull(result);
        assertEquals("Cliente 1", result.getNome());
    }

    @Test
    public void testDeleteCliente() {
        Long clienteId = 1L;

        // Mock de assinaturas e pagamentos
        List<Assinatura> mockAssinaturas = List.of(
                new Assinatura() {{ setId(100L); }},
                new Assinatura() {{ setId(101L); }}
        );

        List<Pagamento> mockPagamentosAssinatura1 = List.of(
                new Pagamento() {{ setId(200L); }},
                new Pagamento() {{ setId(201L); }}
        );

        List<Pagamento> mockPagamentosAssinatura2 = List.of(
                new Pagamento() {{ setId(202L); }},
                new Pagamento() {{ setId(203L); }}
        );

        // Mock dos retornos dos repositórios
        when(assinaturaRepository.findByClienteId(clienteId)).thenReturn(mockAssinaturas);
        when(pagamentoRepository.findByAssinaturaId(100L)).thenReturn(mockPagamentosAssinatura1);
        when(pagamentoRepository.findByAssinaturaId(101L)).thenReturn(mockPagamentosAssinatura2);

        doNothing().when(pagamentoRepository).deleteById(anyLong());
        doNothing().when(assinaturaRepository).deleteById(anyLong());
        doNothing().when(clienteRepository).deleteById(clienteId);

        // Chama o método de serviço
        clienteService.deleteCliente(clienteId);

        // Verificações
        verify(assinaturaRepository, times(1)).findByClienteId(clienteId);
        verify(pagamentoRepository, times(2)).findByAssinaturaId(anyLong());
        verify(pagamentoRepository, times(4)).deleteById(anyLong()); // Total de 4 pagamentos
        verify(assinaturaRepository, times(2)).deleteById(anyLong());
        verify(clienteRepository, times(1)).deleteById(clienteId);
    }

}
