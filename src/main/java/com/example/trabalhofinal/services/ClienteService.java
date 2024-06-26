package com.example.trabalhofinal.services;

import com.example.trabalhofinal.controllers.ClienteController;
import com.example.trabalhofinal.models.Cliente;
import com.example.trabalhofinal.repositories.ClienteRepository;
import com.example.trabalhofinal.repositories.AssinaturaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private AssinaturaRepository assinaturaRepository;


    public List<Cliente> getAllClientes() {
        return clienteRepository.findAll();
    }

    public Cliente saveCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Cliente updateCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public void deleteCliente(Long id) {
        assinaturaRepository.findByClienteId(id).forEach(assinatura -> assinaturaRepository.deleteById(assinatura.getId()));
        clienteRepository.deleteById(id);
    }
}