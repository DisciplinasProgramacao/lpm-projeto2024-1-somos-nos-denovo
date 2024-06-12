package codigo.SSJ5.src;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Menu menu = new Menu();
        Restaurante restaurante = new Restaurante(menu);
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("1 - Cadastrar Cliente");
            System.out.println("2 - Criar requisição");
            System.out.println("3 - Fechar conta");
            System.out.println("4 - Exibir histórico de requisições e pedidos");
            System.out.println("5 - Exibir lista de espera");
            System.out.println("6 - Pedir um produto");
            System.out.println("7 - Exibir menu");
            System.out.println("8 - Sair");
            System.out.print("Escolha uma opção: ");

            while (!scanner.hasNextInt()) {
                System.out.println("Entrada inválida. Por favor, insira um número.");
                scanner.next();
            }
            opcao = scanner.nextInt();

            try {
                switch (opcao) {
                    case 1:
                        cadastrarCliente(scanner, restaurante);
                        break;
                    case 2:
                        criarRequisicao(scanner, restaurante);
                        break;
                    case 3:
                        fecharConta(scanner, restaurante);
                        break;
                    case 4:
                        exibirHistorico(restaurante);
                        break;
                    case 5:
                        exibirListaDeEspera(restaurante);
                        break;
                    case 6:
                        pedirProduto(scanner, restaurante, menu);
                        break;
                    case 7:
                        exibirMenu(menu);
                        break;
                    case 8:
                        System.out.println("Fechando sistema!");
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Ocorreu um erro: " + e.getMessage());
            }
        } while (opcao != 8);

        scanner.close();
    }

    private static void cadastrarCliente(Scanner scanner, Restaurante restaurante) {
        System.out.print("Digite o nome do cliente: ");
        String nome = scanner.next();
        restaurante.adicionarCliente(nome);
        System.out.println("Cliente cadastrado com sucesso!");
    }

    private static void criarRequisicao(Scanner scanner, Restaurante restaurante) {
        System.out.print("Digite o nome do cliente: ");
        String nome = scanner.next();
        System.out.print("Digite a quantidade de pessoas: ");
        int quantidade = scanner.nextInt();
        restaurante.gerarRequisicao(quantidade, nome);
        System.out.println("Requisição criada com sucesso!");
    }

    private static void fecharConta(Scanner scanner, Restaurante restaurante) {
        System.out.print("Digite o ID da mesa: ");
        int idMesa = scanner.nextInt();
        boolean contaFechada = restaurante.fecharConta(idMesa);
        if (contaFechada) {
            System.out.println("Sua conta da requisição foi fechada com sucesso!");
        } else {
            System.out.println("Requisição não encontrada.");
        }
    }

    private static void exibirHistorico(Restaurante restaurante) {
        if (restaurante.exibirHistorico().isEmpty()) {
            System.out.println("Não há histórico de requisições ou pedidos.");
        } else {
            System.out.println(restaurante.exibirHistorico());
        }
    }

    private static void exibirListaDeEspera(Restaurante restaurante) {
        System.out.println(restaurante.exibirListaDeEspera());
    }

    private static void pedirProduto(Scanner scanner, Restaurante restaurante, Menu menu) {
        System.out.print("Digite o ID da requisição: ");
        int idRequisicao = scanner.nextInt();
        System.out.print("O pedido é fechado? (true/false): ");
        boolean fechado = scanner.nextBoolean();

        if (fechado) {
            System.out.println(menu.exibirMenuFechado());
        } else {
            System.out.println(menu.exibirMenu());
        }

        System.out.print("Qual item você deseja? ");
        int idItem = scanner.nextInt();

        boolean pedidoCriado = restaurante.fazerPedido(idRequisicao, idItem, fechado);
        if (pedidoCriado) {
            System.out.println("Pedido criado com sucesso!");
        } else {
            System.out.println("Erro ao criar o pedido.");
        }
    }

    private static void exibirMenu(Menu menu) {
        System.out.println("Menu:");
        System.out.println(menu.exibirMenu());
    }
}
