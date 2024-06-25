package com.example.trabalhofinal.services;

import com.example.trabalhofinal.models.Usuario;
import com.example.trabalhofinal.repositories.UsuarioRepository;
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

public class UsuarioServiceTest {

    @InjectMocks
    private UsuarioService usuarioService;

    @Mock
    private UsuarioRepository usuarioRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllUsuarios() {
        Usuario usuario1 = new Usuario();
        Usuario usuario2 = new Usuario();
        List<Usuario> usuarios = Arrays.asList(usuario1, usuario2);

        when(usuarioRepository.findAll()).thenReturn(usuarios);

        List<Usuario> result = usuarioService.getAllUsuarios();

        assertEquals(2, result.size());
        verify(usuarioRepository, times(1)).findAll();
    }

    @Test
    public void testSaveUsuario() {
        Usuario usuario = new Usuario();
        when(usuarioRepository.save(usuario)).thenReturn(usuario);

        Usuario result = usuarioService.saveUsuario(usuario);

        assertEquals(usuario, result);
        verify(usuarioRepository, times(1)).save(usuario);
    }

    @Test
    public void testUpdateUsuario() {
        Usuario usuario = new Usuario();
        when(usuarioRepository.save(usuario)).thenReturn(usuario);

        Usuario result = usuarioService.updateUsuario(usuario);

        assertEquals(usuario, result);
        verify(usuarioRepository, times(1)).save(usuario);
    }

    @Test
    public void testDeleteUsuario() {
        Long id = 1L;
        doNothing().when(usuarioRepository).deleteById(id);

        usuarioService.deleteUsuario(id);

        verify(usuarioRepository, times(1)).deleteById(id);
    }

    @Test
    public void testIsUsuarioValido_True() {
        String identificador = "user123";
        Usuario usuario = new Usuario();
        when(usuarioRepository.findByUsuario(identificador)).thenReturn(Optional.of(usuario));

        boolean result = usuarioService.isUsuarioValido(identificador);

        assertTrue(result);
        verify(usuarioRepository, times(1)).findByUsuario(identificador);
    }

    @Test
    public void testIsUsuarioValido_False() {
        String identificador = "user123";
        when(usuarioRepository.findByUsuario(identificador)).thenReturn(Optional.empty());

        boolean result = usuarioService.isUsuarioValido(identificador);

        assertFalse(result);
        verify(usuarioRepository, times(1)).findByUsuario(identificador);
    }
}
