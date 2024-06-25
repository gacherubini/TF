package com.example.trabalhofinal.services;

import com.example.trabalhofinal.models.Aplicativo;
import com.example.trabalhofinal.repositories.AplicativoRepository;
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

class AplicativoServiceTest {

    @Mock
    private AplicativoRepository aplicativoRepository;

    @InjectMocks
    private AplicativoService aplicativoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllAplicativos() {
        Aplicativo app1 = new Aplicativo();
        app1.setId(1L);
        Aplicativo app2 = new Aplicativo();
        app2.setId(2L);

        when(aplicativoService.getAllAplicativos()).thenReturn(Arrays.asList(app1, app2));

        List<Aplicativo> aplicativos = aplicativoService.getAllAplicativos();

        assertEquals(2, aplicativos.size());
    }

    @Test
    void testSaveAplicativo() {
        Aplicativo app = new Aplicativo();
        when(aplicativoRepository.save(app)).thenReturn(app);

        Aplicativo savedApp = aplicativoService.saveAplicativo(app);
 
        assertNotNull(savedApp);
        verify(aplicativoRepository, times(1)).save(app);
    }

    @Test
    void testDeleteAplicativo() {
        Long appId = 1L;
        doNothing().when(aplicativoRepository).deleteById(appId);

        aplicativoService.deleteAplicativo(appId);

        verify(aplicativoRepository, times(1)).deleteById(appId);
    }

    @Test
    void testAtualizarCustoMensal() {
        Long appId = 1L;
        double novoCusto = 10.0;
        Aplicativo app = new Aplicativo();
        app.setId(appId);
        app.setCustoMensal(5.0);

        when(aplicativoRepository.findById(appId)).thenReturn(Optional.of(app));
        when(aplicativoRepository.save(app)).thenReturn(app);

        Aplicativo updatedApp = aplicativoService.atualizarCustoMensal(appId, novoCusto);

        assertNotNull(updatedApp);
        assertEquals(novoCusto, updatedApp.getCustoMensal());
        verify(aplicativoRepository, times(1)).findById(appId);
        verify(aplicativoRepository, times(1)).save(app);
    }
}
