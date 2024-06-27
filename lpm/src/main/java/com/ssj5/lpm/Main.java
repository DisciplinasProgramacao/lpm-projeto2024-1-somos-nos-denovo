package com.ssj5.lpm;

import com.ssj5.lpm.models.*;

import jakarta.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Scanner;

@Component
public class Main implements CommandLineRunner {

    @Autowired
    private Restaurante restaurante;
    @Autowired
    private MenuAberto menuAberto;
    @Autowired
    private MenuFechado menuFechado;

    @Override
public void run(String... args) throws Exception {
    Scanner scanner = new Scanner(System.in);
    int opcao;

    restaurante.initMesas();
    menuAberto.initProdutos();
    menuFechado.initProdutos();
    do {
        exibirMenuPrincipal();
        while (!scanner.hasNextInt()) {
            System.out.println("Entrada inválida. Por favor, insira um número.");
            scanner.next();
        }
        opcao = scanner.nextInt();

        try {
            switch (opcao) {
                case 1 -> cadastrarCliente(scanner);
                case 2 -> criarRequisicao(scanner);
                case 3 -> fecharConta(scanner);
                case 4 -> exibirHistorico();
                case 5 -> exibirListaDeEspera();
                case 6 -> atenderCliente(scanner);
                case 7 -> exibirMenu();
                case 8 -> mostrarValorTotalPedido(scanner);
                case 9 -> System.out.println("Fechando sistema!");
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        } catch (IllegalArgumentException | NoSuchElementException | PersistenceException e) {
            System.out.println("Ocorreu um erro: " + e.getMessage());
        }
    } while (opcao != 9);

    scanner.close();
}


    private void exibirMenuPrincipal() {
        System.out.println("1 - Cadastrar Cliente");
        System.out.println("2 - Criar requisição");
        System.out.println("3 - Fechar conta");
        System.out.println("4 - Exibir histórico de requisições e pedidos");
        System.out.println("5 - Exibir lista de espera");
        System.out.println("6 - Atender cliente");
        System.out.println("7 - Exibir menu");
        System.out.println("8 - Mostrar valor total do pedido (por ID de requisição)");
        System.out.println("9 - Sair");
        System.out.print("Escolha uma opção: ");
    }

    private void cadastrarCliente(Scanner scanner) {
        System.out.print("Digite o nome do cliente: ");
        String nome = scanner.next();
        try {
            restaurante.adicionarCliente(nome);
            System.out.println("Cliente cadastrado com sucesso!");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao cadastrar cliente: Nome inválido.");
        } catch (PersistenceException e) {
            System.out.println("Erro ao cadastrar cliente no banco de dados: " + e.getMessage());
        }
    }

    private void criarRequisicao(Scanner scanner) {
        System.out.print("Digite o nome do cliente: ");
        String nome = scanner.next();
        System.out.print("Digite a quantidade de pessoas: ");
        int quantidade = scanner.nextInt();
        try {
            restaurante.gerarRequisicao(quantidade, nome);
            System.out.println("Requisição criada com sucesso!");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao criar requisição: Dados inválidos.");
        } catch (PersistenceException e) {
            System.out.println("Erro ao salvar requisição no banco de dados: " + e.getMessage());
        }
    }

    private void fecharConta(Scanner scanner) {
        System.out.print("Digite o ID da mesa: ");
        int idMesa = scanner.nextInt();
        try {
            boolean contaFechada = restaurante.fecharConta(idMesa);
            if (contaFechada) {
                System.out.println("Sua conta da requisição foi fechada com sucesso!");
            } else {
                System.out.println("Requisição não encontrada.");
            }
        } catch (NoSuchElementException e) {
            System.out.println("Erro ao fechar conta: Mesa não encontrada.");
        } catch (PersistenceException e) {
            System.out.println("Erro ao salvar fechamento de conta no banco de dados: " + e.getMessage());
        }
    }

    private void exibirHistorico() {
        try {
            String historico = restaurante.exibirHistorico();
            if (historico.isEmpty()) {
                System.out.println("Não há histórico de requisições ou pedidos.");
            } else {
                System.out.println(historico);
            }
        } catch (PersistenceException e) {
            System.out.println("Erro ao recuperar histórico do banco de dados: " + e.getMessage());
        }
    }

    private void exibirListaDeEspera() {
        try {
            String listaDeEspera = String.join("\n", restaurante.exibirListaDeEspera());
            System.out.println(listaDeEspera);
        } catch (PersistenceException e) {
            System.out.println("Erro ao recuperar lista de espera do banco de dados: " + e.getMessage());
        }
    }

    private void atenderCliente(Scanner scanner) {
        System.out.print("Digite o ID da requisição: ");
        int idRequisicao = scanner.nextInt();
    
        if (restaurante.isRequisicaoAtendida(idRequisicao)) {
            System.out.println("Esta requisição já foi atendida. Não é possível processar novamente.");
            return;
        }
    
        System.out.println("1 - Criar Pedido");
        System.out.println("2 - Adicionar Produto (Menu Aberto)");
        System.out.println("3 - Adicionar Produto (Menu Fechado)");
        System.out.print("Escolha uma opção: ");
        int opcao = scanner.nextInt();
    
        try {
            switch (opcao) {
                case 1:
                    criarPedido(scanner, idRequisicao);
                    break;
                case 2:
                    adicionarProdutoAberto(scanner, idRequisicao);
                    break;
                case 3:
                    adicionarProdutoFechado(scanner, idRequisicao);
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } catch (IllegalArgumentException | NoSuchElementException | PersistenceException e) {
            System.out.println("Erro ao atender cliente: " + e.getMessage());
        }
    }
    

    private void criarPedido(Scanner scanner, int idRequisicao) {
        System.out.print("O pedido é fechado? (true/false): ");
        boolean fechado = scanner.nextBoolean();
        try {
            boolean pedidoCriado = restaurante.criarPedido(idRequisicao, fechado);
            if (pedidoCriado) {
                System.out.println("Pedido criado com sucesso!");
            } else {
                System.out.println("Erro ao criar o pedido.");
            }
        } catch (NoSuchElementException e) {
            System.out.println("Erro ao criar pedido: Requisição não encontrada.");
        } catch (PersistenceException e) {
            System.out.println("Erro ao salvar pedido no banco de dados: " + e.getMessage());
        }
    }

    private void adicionarProdutoAberto(Scanner scanner, int idRequisicao) {
        try {
            System.out.println(restaurante.exibirMenuAberto());
            System.out.print("Qual item você deseja? ");
            Long idItem = scanner.nextLong();
            boolean produtoAdicionado = restaurante.adicionarProduto(idRequisicao, idItem, false);
            if (produtoAdicionado) {
                System.out.println("Produto adicionado com sucesso!");
            } else {
                System.out.println("Erro ao adicionar o produto.");
            }
        } catch (NoSuchElementException e) {
            System.out.println("Erro ao adicionar produto: Requisição ou produto não encontrado.");
        } catch (PersistenceException e) {
            System.out.println("Erro ao salvar produto no pedido no banco de dados: " + e.getMessage());
        }
    }

    private void adicionarProdutoFechado(Scanner scanner, int idRequisicao) {
        try {
            System.out.println(restaurante.exibirMenuFechado());
            System.out.print("Qual item você deseja? ");
            Long idItem = scanner.nextLong();
            boolean produtoAdicionado = restaurante.adicionarProduto(idRequisicao, idItem, true);
            if (produtoAdicionado) {
                System.out.println("Produto adicionado com sucesso!");
            } else {
                System.out.println("Erro ao adicionar o produto.");
            }
        } catch (NoSuchElementException e) {
            System.out.println("Erro ao adicionar produto: Requisição ou produto não encontrado.");
        } catch (PersistenceException e) {
            System.out.println("Erro ao salvar produto no pedido no banco de dados: " + e.getMessage());
        }
    }

    private void exibirMenu() {
        try {
            System.out.println("Menu Aberto:");
            System.out.println(restaurante.exibirMenuAberto());
            System.out.println("Menu Fechado:");
            System.out.println(restaurante.exibirMenuFechado());
        } catch (PersistenceException e) {
            System.out.println("Erro ao recuperar menus do banco de dados: " + e.getMessage());
        }
    }

    private void mostrarValorTotalPedido(Scanner scanner) {
        System.out.print("Digite o ID da requisição: ");
        int idRequisicao = scanner.nextInt();
        
        try {
            Optional<Requisicao> requisicaoOpt = restaurante.localizarRequisicao(idRequisicao);
            if (requisicaoOpt.isPresent()) {
                Requisicao requisicao = requisicaoOpt.get();
                Pedido pedido = requisicao.getPedido();
                double valorTotal = pedido.calcularValorTotal();
                System.out.println("Valor total do pedido: R$" + valorTotal);
            } else {
                System.out.println("Requisição não encontrada.");
            }
        } catch (NoSuchElementException e) {
            System.out.println("Erro ao calcular valor total do pedido: Requisição não encontrada.");
        } catch (PersistenceException e) {
            System.out.println("Erro ao calcular valor total do pedido: " + e.getMessage());
        }
    }
    
}