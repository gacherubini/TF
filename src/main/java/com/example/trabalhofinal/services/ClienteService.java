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
        logger.info("Iniciando processo de exclusão para o cliente com ID: {}", id);

        List<Assinatura> assinaturas = assinaturaRepository.findByClienteId(id);
        for (Assinatura assinatura : assinaturas) {
            logger.info("Excluindo pagamentos para a assinatura com ID: {}", assinatura.getId());
            pagamentoRepository.findByAssinaturaId(assinatura.getId()).forEach(pagamento -> {
                logger.info("Excluindo pagamento com ID: {}", pagamento.getId());
                pagamentoRepository.deleteById(pagamento.getId());
            });

            logger.info("Excluindo assinatura com ID: {}", assinatura.getId());
            assinaturaRepository.deleteById(assinatura.getId());
        }

        logger.info("Excluindo cliente com ID: {}", id);
        clienteRepository.deleteById(id);

        logger.info("Processo de exclusão concluído para o cliente com ID: {}", id);
    }
}