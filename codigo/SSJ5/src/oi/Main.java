package codigo.SSJ5.src.oi;

import java.util.Scanner;

/**
 * A classe Main é a classe principal que executa a aplicação de gerenciamento de um restaurante.
 * Ela oferece um menu interativo para cadastrar clientes, criar requisições, fechar contas,
 * exibir histórico de pedidos, gerenciar lista de espera, atender clientes e exibir menus.
 */
public class Main {

    /**
     * O método main inicia a execução do programa.
     * Ele exibe o menu principal e trata as opções selecionadas pelo usuário.
     *
     * @param args os argumentos da linha de comando (não utilizados).
     */
    public static void main(String[] args) {
        Restaurante restaurante = new Restaurante();
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            exibirMenuPrincipal();
            while (!scanner.hasNextInt()) {
                System.out.println("Entrada inválida. Por favor, insira um número.");
                scanner.next();
            }
            opcao = scanner.nextInt();

            try {
                switch (opcao) {
                    case 1 -> cadastrarCliente(scanner, restaurante);
                    case 2 -> criarRequisicao(scanner, restaurante);
                    case 3 -> fecharConta(scanner, restaurante);
                    case 4 -> exibirHistorico(restaurante);
                    case 5 -> exibirListaDeEspera(restaurante);
                    case 6 -> atenderCliente(scanner, restaurante);
                    case 7 -> exibirMenu(restaurante);
                    case 8 -> System.out.println("Fechando sistema!");
                    default -> System.out.println("Opção inválida. Tente novamente.");
                }
            } catch (Exception e) {
                System.out.println("Ocorreu um erro: " + e.getMessage());
            }
        } while (opcao != 8);

        scanner.close();
    }

    /**
     * Exibe o menu principal do sistema.
     */
    private static void exibirMenuPrincipal() {
        System.out.println("1 - Cadastrar Cliente");
        System.out.println("2 - Criar requisição");
        System.out.println("3 - Fechar conta");
        System.out.println("4 - Exibir histórico de requisições e pedidos");
        System.out.println("5 - Exibir lista de espera");
        System.out.println("6 - Atender cliente");
        System.out.println("7 - Exibir menu");
        System.out.println("8 - Sair");
        System.out.print("Escolha uma opção: ");
    }

    /**
     * Cadastra um novo cliente no restaurante.
     *
     * @param scanner    O scanner para entrada do usuário.
     * @param restaurante O objeto Restaurante onde o cliente será cadastrado.
     */
    private static void cadastrarCliente(Scanner scanner, Restaurante restaurante) {
        System.out.print("Digite o nome do cliente: ");
        String nome = scanner.next();
        restaurante.adicionarCliente(nome);
        System.out.println("Cliente cadastrado com sucesso!");
    }

    /**
     * Cria uma nova requisição para o cliente.
     *
     * @param scanner    O scanner para entrada do usuário.
     * @param restaurante O objeto Restaurante onde a requisição será criada.
     */
    private static void criarRequisicao(Scanner scanner, Restaurante restaurante) {
        System.out.print("Digite o nome do cliente: ");
        String nome = scanner.next();
        System.out.print("Digite a quantidade de pessoas: ");
        int quantidade = scanner.nextInt();
        restaurante.gerarRequisicao(quantidade, nome);
        System.out.println("Requisição criada com sucesso!");
    }

    /**
     * Fecha a conta de uma mesa específica.
     *
     * @param scanner    O scanner para entrada do usuário.
     * @param restaurante O objeto Restaurante onde a conta será fechada.
     */
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

    /**
     * Exibe o histórico de requisições e pedidos do restaurante.
     *
     * @param restaurante O objeto Restaurante do qual o histórico será exibido.
     */
    private static void exibirHistorico(Restaurante restaurante) {
        String historico = restaurante.exibirHistorico();
        if (historico.isEmpty()) {
            System.out.println("Não há histórico de requisições ou pedidos.");
        } else {
            System.out.println(historico);
        }
    }

    /**
     * Exibe a lista de espera do restaurante.
     *
     * @param restaurante O objeto Restaurante do qual a lista de espera será exibida.
     */
    private static void exibirListaDeEspera(Restaurante restaurante) {
        String listaDeEspera = String.join("\n", restaurante.exibirListaDeEspera());
        System.out.println(listaDeEspera);
    }

    /**
     * Atende um cliente específico.
     *
     * @param scanner    O scanner para entrada do usuário.
     * @param restaurante O objeto Restaurante onde o cliente será atendido.
     */
    private static void atenderCliente(Scanner scanner, Restaurante restaurante) {
        System.out.print("Digite o ID da requisição: ");
        int idRequisicao = scanner.nextInt();
        System.out.println("1 - Criar Pedido");
        System.out.println("2 - Adicionar Produto (Menu Aberto)");
        System.out.println("3 - Adicionar Produto (Menu Fechado)");
        System.out.print("Escolha uma opção: ");
        int opcao = scanner.nextInt();

        switch (opcao) {
            case 1 -> criarPedido(scanner, restaurante, idRequisicao);
            case 2 -> adicionarProdutoAberto(scanner, restaurante, idRequisicao);
            case 3 -> adicionarProdutoFechado(scanner, restaurante, idRequisicao);
            default -> System.out.println("Opção inválida.");
        }
    }

    /**
     * Cria um novo pedido para uma requisição específica.
     *
     * @param scanner      O scanner para entrada do usuário.
     * @param restaurante  O objeto Restaurante onde o pedido será criado.
     * @param idRequisicao O ID da requisição para a qual o pedido será criado.
     */
    private static void criarPedido(Scanner scanner, Restaurante restaurante, int idRequisicao) {
        System.out.print("O pedido é fechado? (true/false): ");
        boolean fechado = scanner.nextBoolean();
        boolean pedidoCriado = restaurante.criarPedido(idRequisicao, fechado);
        if (pedidoCriado) {
            System.out.println("Pedido criado com sucesso!");
        } else {
            System.out.println("Erro ao criar o pedido.");
        }
    }

    /**
     * Adiciona um produto a um pedido aberto.
     *
     * @param scanner      O scanner para entrada do usuário.
     * @param restaurante  O objeto Restaurante onde o produto será adicionado.
     * @param idRequisicao O ID da requisição para a qual o produto será adicionado.
     */
    private static void adicionarProdutoAberto(Scanner scanner, Restaurante restaurante, int idRequisicao) {
        System.out.println(restaurante.exibirMenuAberto());
        System.out.print("Qual item você deseja? ");
        int idItem = scanner.nextInt();
        boolean produtoAdicionado = restaurante.adicionarProduto(idRequisicao, idItem, false);
        if (produtoAdicionado) {
            System.out.println("Produto adicionado com sucesso!");
        } else {
            System.out.println("Erro ao adicionar o produto.");
        }
    }

    /**
     * Adiciona um produto a um pedido fechado.
     *
     * @param scanner      O scanner para entrada do usuário.
     * @param restaurante  O objeto Restaurante onde o produto será adicionado.
     * @param idRequisicao O ID da requisição para a qual o produto será adicionado.
     */
    private static void adicionarProdutoFechado(Scanner scanner, Restaurante restaurante, int idRequisicao) {
        System.out.println(restaurante.exibirMenuFechado());
        System.out.print("Qual item você deseja? ");
        int idItem = scanner.nextInt();
        boolean produtoAdicionado = restaurante.adicionarProduto(idRequisicao, idItem, true);
        if (produtoAdicionado) {
            System.out.println("Produto adicionado com sucesso!");
        } else {
            System.out.println("Erro ao adicionar o produto.");
        }
    }

    /**
     * Exibe os menus aberto e fechado do restaurante.
     *
     * @param restaurante O objeto Restaurante cujos menus serão exibidos.
     */
    private static void exibirMenu(Restaurante restaurante) {
        System.out.println("Menu Aberto:");
        System.out.println(restaurante.exibirMenuAberto());
        System.out.println("Menu Fechado:");
        System.out.println(restaurante.exibirMenuFechado());
    }
}
