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
                new Cliente("Ana Silva", "ana.silva@gmail.com"),
                new Cliente("Bruno Souza", "bruno.souza@gmail.com"),
                new Cliente("Carla Ferreira", "carla.ferreira@gmail.com"),
                new Cliente("Daniel Lima", "daniel.lima@gmail.com"),
                new Cliente("Eliana Costa", "eliana.costa@gmail.com"),
                new Cliente("Fabio Pereira", "fabio.pereira@gmail.com"),
                new Cliente("Gabriela Oliveira", "gabriela.oliveira@gmail.com"),
                new Cliente("Hugo Martins", "hugo.martins@gmail.com"),
                new Cliente("Isabela Rocha", "isabela.rocha@gmail.com"),
                new Cliente("João Mendes", "joao.mendes@gmail.com")
        };
        clienteRepository.saveAll(Arrays.asList(clientes));

        Aplicativo[] aplicativos = {
                new Aplicativo("Spotify", 19.99),
                new Aplicativo("Prime Vídeo", 9.99),
                new Aplicativo("BlueJ", 4.99),
                new Aplicativo("Disney Plus", 14.99),
                new Aplicativo("Netflix", 45.99)
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
                new Promocao("Promoção Anual 30%", 365, 0.30, LocalDate.now().plusYears(1), true),
                new Promocao("Pague 30 e ganhe 50 dias", 50, 0.0, LocalDate.now().plusMonths(6), true)
        };
        promocaoRepository.saveAll(Arrays.asList(promocoes));
    }
}
