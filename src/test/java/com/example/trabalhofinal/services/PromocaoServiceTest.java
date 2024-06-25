package com.example.trabalhofinal.services;

import com.example.trabalhofinal.models.Promocao;
import com.example.trabalhofinal.repositories.PromocaoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PromocaoServiceTest {

    @InjectMocks
    private PromocaoService promocaoService;

    @Mock
    private PromocaoRepository promocaoRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllPromocoes() {
        Promocao promocao1 = new Promocao();
        Promocao promocao2 = new Promocao();
        List<Promocao> promocoes = Arrays.asList(promocao1, promocao2);

        when(promocaoRepository.findAll()).thenReturn(promocoes);

        List<Promocao> result = promocaoService.getAllPromocoes();

        assertEquals(2, result.size());
        verify(promocaoRepository, times(1)).findAll();
    }

    @Test
    public void testGetPromocaoById() {
        Long id = 1L;
        Promocao promocao = new Promocao();
        when(promocaoRepository.findById(id)).thenReturn(Optional.of(promocao));

        Promocao result = promocaoService.getPromocaoById(id);

        assertEquals(promocao, result);
        verify(promocaoRepository, times(1)).findById(id);
    }

    @Test
    public void testGetPromocaoById_NotFound() {
        Long id = 1L;
        when(promocaoRepository.findById(id)).thenReturn(Optional.empty());

        Promocao result = promocaoService.getPromocaoById(id);

        assertNull(result);
        verify(promocaoRepository, times(1)).findById(id);
    }

    @Test
    public void testCreatePromocao() {
        Promocao promocao = new Promocao();
        when(promocaoRepository.save(promocao)).thenReturn(promocao);

        Promocao result = promocaoService.createPromocao(promocao);

        assertEquals(promocao, result);
        verify(promocaoRepository, times(1)).save(promocao);
    }

    @Test
    public void testUpdatePromocao() {
        Long id = 1L;
        Promocao promocao = new Promocao();
        Promocao existingPromocao = new Promocao();
        when(promocaoRepository.findById(id)).thenReturn(Optional.of(existingPromocao));
        when(promocaoRepository.save(promocao)).thenReturn(promocao);

        Promocao result = promocaoService.updatePromocao(id, promocao);

        assertEquals(promocao, result);
        verify(promocaoRepository, times(1)).findById(id);
        verify(promocaoRepository, times(1)).save(promocao);
    }

    @Test
    public void testUpdatePromocao_NotFound() {
        Long id = 1L;
        Promocao promocao = new Promocao();
        when(promocaoRepository.findById(id)).thenReturn(Optional.empty());

        Promocao result = promocaoService.updatePromocao(id, promocao);

        assertNull(result);
        verify(promocaoRepository, times(1)).findById(id);
        verify(promocaoRepository, never()).save(promocao);
    }

    @Test
    public void testDeletePromocao() {
        Long id = 1L;
        doNothing().when(promocaoRepository).deleteById(id);

        promocaoService.deletePromocao(id);

        verify(promocaoRepository, times(1)).deleteById(id);
    }
}
