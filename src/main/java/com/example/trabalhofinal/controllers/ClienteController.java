package com.example.trabalhofinal.controllers;

import com.example.trabalhofinal.models.Cliente;
import com.example.trabalhofinal.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/servcad")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/clientes")
    public List<Cliente> getAllClientes() {
        return clienteService.getAllClientes();
    }

    @PostMapping("/clientes")
    public Cliente saveCliente(@RequestBody Cliente cliente) {
        return clienteService.saveCliente(cliente);
    }

    @PutMapping("clientes/{id}")
    public Cliente updateCliente(@PathVariable Long id, @RequestBody Cliente cliente) { //checar
        cliente.setId(id);
        return clienteService.updateCliente(cliente);
    }

    @DeleteMapping("clientes/{id}")
    public void deleteCliente(@PathVariable Long id) {
        clienteService.deleteCliente(id);
    }
}
