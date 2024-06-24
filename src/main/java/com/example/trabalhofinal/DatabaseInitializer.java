package com.example.trabalhofinal;

import com.example.trabalhofinal.models.Aplicativo;
import com.example.trabalhofinal.models.Assinatura;
import com.example.trabalhofinal.models.Cliente;
import com.example.trabalhofinal.models.Promocao;
import com.example.trabalhofinal.repositories.AplicativoRepository;
import com.example.trabalhofinal.repositories.AssinaturaRepository;
import com.example.trabalhofinal.repositories.ClienteRepository;
import com.example.trabalhofinal.repositories.PromocaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;

@Component
public class DatabaseInitializer implements CommandLineRunner {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private AplicativoRepository aplicativoRepository;

    @Autowired
    private AssinaturaRepository assinaturaRepository;

    @Autowired
    private PromocaoRepository promocaoRepository;

    @Override
    public void run(String... args) throws Exception {
        Cliente[] clientes = {
                new Cliente("Ana Silva", "ana.silva@example.com"),
                new Cliente("Bruno Souza", "bruno.souza@example.com"),
                new Cliente("Carla Ferreira", "carla.ferreira@example.com"),
                new Cliente("Daniel Lima", "daniel.lima@example.com"),
                new Cliente("Eliana Costa", "eliana.costa@example.com"),
                new Cliente("Fabio Pereira", "fabio.pereira@example.com"),
                new Cliente("Gabriela Oliveira", "gabriela.oliveira@example.com"),
                new Cliente("Hugo Martins", "hugo.martins@example.com"),
                new Cliente("Isabela Rocha", "isabela.rocha@example.com"),
                new Cliente("João Mendes", "joao.mendes@example.com")
        };
        clienteRepository.saveAll(Arrays.asList(clientes));

        Aplicativo[] aplicativos = {
                new Aplicativo("Fitness App", 19.99),
                new Aplicativo("Finance Tracker", 9.99),
                new Aplicativo("Recipe Book", 4.99),
                new Aplicativo("Language Learner", 14.99),
                new Aplicativo("Music Streaming", 29.99)
        };
        aplicativoRepository.saveAll(Arrays.asList(aplicativos));

        Assinatura[] assinaturas = {
                new Assinatura(clientes[0], aplicativos[0], LocalDate.now(), LocalDate.now().plusMonths(1)),
                new Assinatura(clientes[1], aplicativos[1], LocalDate.now(), LocalDate.now().plusMonths(1)),
                new Assinatura(clientes[2], aplicativos[2], LocalDate.now(), LocalDate.now().plusMonths(1)),
                new Assinatura(clientes[3], aplicativos[3], LocalDate.now(), LocalDate.now().plusMonths(1)),
                new Assinatura(clientes[4], aplicativos[4], LocalDate.now(), LocalDate.now().plusMonths(1))
        };
        assinaturaRepository.saveAll(Arrays.asList(assinaturas));

        Promocao[] promocoes = {
                new Promocao("Promoção Anual 40%", 365, 0.40, LocalDate.now().plusYears(1), true),
                new Promocao("Pague 30 e ganhe 45 dias", 45, 0.0, LocalDate.now().plusMonths(6), true)
        };
        promocaoRepository.saveAll(Arrays.asList(promocoes));
    }
}
