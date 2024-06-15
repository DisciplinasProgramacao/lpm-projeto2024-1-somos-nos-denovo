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
            System.out.println("6 - Atender mesa");
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
                        atenderMesa(scanner, restaurante, menu);
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

    private static void atenderMesa(Scanner scanner, Restaurante restaurante, Menu menu) {
        System.out.print("Digite o ID da mesa: ");
        int idMesa = scanner.nextInt();
        Requisicao requisicao = restaurante.localizarRequisicao(idMesa);

        if (requisicao == null) {
            System.out.println("Mesa não encontrada.");
            return;
        }

        int opcao;
        do {
            System.out.println("1 - Gerar pedido");
            System.out.println("2 - Pedir produto do menu aberto");
            System.out.println("3 - Pedir produto do menu fechado");
            System.out.println("4 - Voltar");
            System.out.print("Escolha uma opção: ");

            while (!scanner.hasNextInt()) {
                System.out.println("Entrada inválida. Por favor, insira um número.");
                scanner.next();
            }
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    gerarPedido(requisicao);
                    break;
                case 2:
                    pedirProdutoAberto(scanner, restaurante, menu, requisicao);
                    break;
                case 3:
                    pedirProdutoFechado(scanner, restaurante, menu, requisicao);
                    break;
                case 4:
                    System.out.println("Voltando ao menu principal.");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        } while (opcao != 4);
    }

    private static void gerarPedido(Requisicao requisicao) {
        if (requisicao.getPedidoAtual() == null) {
            Pedido pedido = new PedidoAberto();
            requisicao.addPedido(pedido);
            System.out.println("Pedido gerado com sucesso!");
        } else {
            System.out.println("Já existe um pedido aberto para esta requisição.");
        }
    }

    private static void pedirProdutoAberto(Scanner scanner, Restaurante restaurante, Menu menu, Requisicao requisicao) {
        System.out.println(menu.exibirMenu());
        System.out.print("Digite o ID do produto: ");
        int idProduto = scanner.nextInt();
        int[] idsProdutos = { idProduto };

        boolean produtoAdicionado = restaurante.adicionarProduto(requisicao.getId(), idsProdutos, false);
        if (produtoAdicionado) {
            System.out.println("Produto adicionado com sucesso!");
        } else {
            System.out.println("Erro ao adicionar o produto.");
        }
    }

    private static void pedirProdutoFechado(Scanner scanner, Restaurante restaurante, Menu menu, Requisicao requisicao) {
        int[] idsProdutos = new int[3];
        System.out.println(menu.exibirMenuFechado());

        System.out.print("Escolha uma comida (ID): ");
        idsProdutos[0] = scanner.nextInt();

        for (int i = 1; i < 3; i++) {
            System.out.print("Escolha uma bebida (ID): ");
            idsProdutos[i] = scanner.nextInt();
        }

        boolean produtoAdicionado = restaurante.adicionarProduto(requisicao.getId(), idsProdutos, true);
        if (produtoAdicionado) {
            System.out.println("Produto adicionado com sucesso!");
        } else {
            System.out.println("Erro ao adicionar o produto.");
        }
    }

    private static void exibirMenu(Menu menu) {
        System.out.println("Menu:");
        System.out.println(menu.exibirMenu());
    }
}
