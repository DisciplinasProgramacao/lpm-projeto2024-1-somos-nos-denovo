package com.br.lpmssj5.ssj5;

import com.br.lpmssj5.ssj5.Model.*;
import com.br.lpmssj5.ssj5.Controller.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class App {

    @Autowired
    private ClienteController clienteController;

    @Autowired
    private MesaController mesaController;

    @Autowired
    private PedidoController pedidoController;

    @Autowired
    private ProdutoController produtoController;

    @Autowired
    private RequisicaoController requisicaoController;

    @Autowired
    private RestauranteController restauranteController;

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Bean
    public CommandLineRunner initializer() {
        return args -> {
            if (mesaController.getAllMesas().isEmpty()) {
                List<Mesa> mesas = criaMesas();
                mesas.forEach(mesaController::createMesa);
            }

            if (produtoController.getAllProdutos().isEmpty()) {
                List<Produto> produtos = criaProdutos();
                produtos.forEach(produtoController::createProduto);
            }

            Restaurante restaurante = restauranteController.getRestaurante();
            if (restaurante == null) {
                restaurante = new Restaurante(mesaController.getAllMesas());
            }

            Scanner scanner = new Scanner(System.in);
            int opcao;

            do {
                limparTela();
                System.out.println("1 - Cadastrar Cliente");
                System.out.println("2 - Criar requisição");
                System.out.println("3 - Fechar conta");
                System.out.println("4 - Exibir histórico de requisições");
                System.out.println("5 - Exibir lista de espera");
                System.out.println("6 - Fazer pedido");
                System.out.println("7 - Exibir Menu");
                System.out.println("8 - Sair");
                System.out.print("Escolha uma opção: ");

                while (!scanner.hasNextInt()) {
                    System.out.println("Entrada inválida. Por favor, insira um número.");
                    scanner.next();
                }
                opcao = scanner.nextInt();

                switch (opcao) {
                    case 1:
                        cadastrarCliente(scanner);
                        break;
                    case 2:
                        criarRequisicao(scanner, restaurante);
                        break;
                    case 3:
                        fecharConta(scanner, restaurante);
                        break;
                    case 4:
                        System.out.println(Requisicao.exibirHistoricoDeRequisicoes(restaurante.getHistoricoDeRequisicao()));
                        break;
                    case 5:
                        restaurante.exibirListaDeEspera();
                        break;
                    case 6:
                        fazerPedido(scanner, restaurante);
                        break;
                    case 7:
                        exibirMenu(produtoController.getAllProdutos());
                        break;
                    case 8:
                        System.out.println("Fechando sistema!");
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                        break;
                }
            } while (opcao != 8);

            scanner.close();
        };
    }

    private void cadastrarCliente(Scanner scanner) {
        System.out.print("Digite o nome do cliente: ");
        String nome = scanner.next();
        Cliente novoCliente = new Cliente(nome);
        clienteController.createCliente(novoCliente);
        System.out.println("Cliente cadastrado com sucesso!");
    }

    private void criarRequisicao(Scanner scanner, Restaurante restaurante) {
        System.out.print("Digite o nome do cliente: ");
        String nome = scanner.next();
        Cliente cliente = clienteController.getClienteByNome(nome);
        if (cliente == null) {
            cliente = new Cliente(nome);
            clienteController.createCliente(cliente);
        }
        System.out.print("Digite a quantidade de pessoas: ");
        int quantidade = scanner.nextInt();
        Requisicao novaRequisicao = new Requisicao(quantidade, cliente, LocalDate.now(), LocalTime.now(), restaurante);
        restaurante.gerarRequisicao(novaRequisicao);
        requisicaoController.createRequisicao(novaRequisicao);
        System.out.println("Requisição criada com sucesso!");
    }

    private void fecharConta(Scanner scanner, Restaurante restaurante) {
        System.out.print("Digite o ID da mesa: ");
        int idMesa = scanner.nextInt();
        Requisicao requisicao = requisicaoController.findRequisicaoByMesaIdAndAberta(idMesa, true);
        if (requisicao != null) {
            requisicao.fecharRequisicao(restaurante.getHistoricoDeRequisicao());
            requisicaoController.createRequisicao(requisicao);
        } else {
            System.out.println("Requisição não encontrada ou já fechada.");
        }
    }

    private void fazerPedido(Scanner scanner, Restaurante restaurante) {
        System.out.print("Digite o ID da mesa: ");
        int idMesa = scanner.nextInt();
        Requisicao requisicao = requisicaoController.findRequisicaoByMesaIdAndAberta(idMesa, true);
        if (requisicao != null) {
            List<Produto> produtosPedido = new ArrayList<>();
            String continuar;
            do {
                exibirMenu(produtoController.getAllProdutos());
                System.out.print("Digite o ID do produto: ");
                int idProduto = scanner.nextInt();
                Produto produto = produtoController.getProdutoById(idProduto);
                if (produto != null) {
                    produtosPedido.add(produto);
                    System.out.println("Produto adicionado ao pedido.");
                } else {
                    System.out.println("Produto não encontrado.");
                }
                System.out.print("Deseja adicionar mais produtos ao pedido? (s/n): ");
                continuar = scanner.next();
            } while (continuar.equalsIgnoreCase("s"));
            restaurante.fazerPedido(produtosPedido, requisicao);
            requisicaoController.createRequisicao(requisicao);
        } else {
            System.out.println("Requisição não encontrada ou já fechada.");
        }
    }

    private static void limparTela() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private static List<Mesa> criaMesas() {
        List<Mesa> mesas = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            mesas.add(new Mesa(4));
        }
        for (int i = 0; i < 4; i++) {
            mesas.add(new Mesa(6));
        }
        for (int i = 0; i < 2; i++) {
            mesas.add(new Mesa(8));
        }
        return mesas;
    }

    private static List<Produto> criaProdutos() {
        List<Produto> produtos = new ArrayList<>();
        String[] comidas = { "Moqueca de Palmito", "Falafel Assado", "Salada Primavera com Macarrão Konjac",
                "Escondidinho de Inhame", "Strogonoff de Cogumelos", "Caçarola de legumes" };
        String[] bebidas = { "Água", "Copo de Suco", "Refrigerante orgânico", "Cerveja vegana",
                "Taça de vinho vegano" };
        Integer[] precoComida = { 32, 20, 25, 18, 35, 22 };
        Integer[] precoBebida = { 3, 7, 7, 9, 18 };

        for (int i = 0; i < comidas.length; i++) {
            Produto novoProduto = new Produto(comidas[i], precoComida[i]);
            produtos.add(novoProduto);
        }
        for (int i = 0; i < bebidas.length; i++) {
            Produto novoProduto = new Produto(bebidas[i], precoBebida[i]);
            produtos.add(novoProduto);
        }
        return produtos;
    }

    private static void exibirMenu(List<Produto> produtos) {
        System.out.println("Menu:");
        for (Produto p : produtos) {
            System.out.println(p.getIdProduto() + " " + p.getNomeProduto() + " - R$" + p.getPrecoProduto());
        }
    }
}
