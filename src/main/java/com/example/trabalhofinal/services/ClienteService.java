package com.example.trabalhofinal.services;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.trabalhofinal.models.Cliente;
import com.example.trabalhofinal.models.Assinatura;

import com.example.trabalhofinal.repositories.ClienteRepository;
import com.example.trabalhofinal.repositories.AssinaturaRepository;
import com.example.trabalhofinal.repositories.PagamentoRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {
    private static final Logger logger = LoggerFactory.getLogger(ClienteService.class);

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private AssinaturaRepository assinaturaRepository;

    @Autowired
    private PagamentoRepository pagamentoRepository;


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

        List<Assinatura> assinaturas = assinaturaRepository.findByClienteId(id);
        for (Assinatura assinatura : assinaturas) {
            pagamentoRepository.findByAssinaturaId(assinatura.getId()).forEach(pagamento -> {
                pagamentoRepository.deleteById(pagamento.getId());
            });

            assinaturaRepository.deleteById(assinatura.getId());
        }

        clienteRepository.deleteById(id);
    }
}