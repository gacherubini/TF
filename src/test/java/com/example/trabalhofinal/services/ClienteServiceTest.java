package com.example.trabalhofinal.services;

import com.example.trabalhofinal.models.Cliente;
import com.example.trabalhofinal.repositories.ClienteRepository;
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
        doNothing().when(clienteRepository).deleteById(cliente1.getId());

        clienteService.deleteCliente(cliente1.getId());

        verify(clienteRepository, times(1)).deleteById(cliente1.getId());
    }
}
