package codigo.SSJ5.src;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class main {
    /**
     * "Limpa" a tela (códigos de terminal VT-100)
     */
    public static void limparTela() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void main(String[] args) {
        Restaurante restaurante = new Restaurante();
        Menu menu = new Menu();
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("1 - Cadastrar Cliente");
            System.out.println("2 - Criar requisição");
            System.out.println("3 - Fechar conta");
            System.out.println("4 - Exibir histórico de requisições");
            System.out.println("5 - Exibir lista de espera");
            System.out.println("6 - Criar pedido");
            System.out.println("7 - Exibir menu");
            System.out.println("8 - Mostrar pedidos");
            System.out.println("9 - Sair");
            System.out.print("Escolha uma opção: ");

            while (!scanner.hasNextInt()) {
                System.out.println("Entrada inválida. Por favor, insira um número.");
                scanner.next();
            }
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    System.out.print("Digite o nome do cliente: ");
                    String nome = scanner.next();
                    Cliente novoCliente = new Cliente(nome);
                    restaurante.getListaDeClientes().add(novoCliente);
                    System.out.println("Cliente cadastrado com sucesso!");
                    break;
                case 2:
                    System.out.print("Digite o nome do cliente: ");
                    nome = scanner.next();
                    System.out.print("Digite a quantidade de pessoas: ");
                    int quantidade = scanner.nextInt();
                    Requisicao requisicao = restaurante.gerarRequisicao(quantidade, nome);
                    System.out.println("Requisição criada com sucesso!");
                    break;
                case 3:
                    System.out.print("Digite o ID da mesa: ");
                    int idMesa = scanner.nextInt();
                    Requisicao requisicaoFechar = null;
                    for (Requisicao r : restaurante.getHistoricoDeRequisicao()) {
//                        if (r.getMesa().getId() == idMesa) {
//                            requisicaoFechar = r;
//                            break;
//                        }
//                    }
//                    if (requisicaoFechar != null) {
//                        requisicaoFechar.fecharRequisicao(restaurante.getHistoricoDeRequisicao());
//                        System.out.println("Sua conta da requisição: " + requisicaoFechar.getId() + " foi fechada com sucesso!");
//                    } else {
//                        System.out.println("Requisição não encontrada.");
//                    }
                    break;
                case 4:
                    System.out.println(restaurante.exibirHistoricoDeRequisicoes());
                    break;
                case 5:
                    System.out.println(restaurante.exibirListaDeEspera());
//                    List<String> listaDeEspera = restaurante.exibirListaDeEspera();
//                    for (String item : listaDeEspera) {
//                        System.out.println(item);
//                    }
                    break;
                case 6:
                    System.out.print("Digite o ID da requisição: ");
                    int idRequisicao = scanner.nextInt();
                    requisicao = null;
                    System.out.println(menu.exibirMenu());
                    System.out.println("qual iten vc deseja?");
                    int iditen = scanner.nextInt();
                    restaurante.fazerPedido(idRequisicao,iditen);
//                    for (Requisicao r : restaurante.getHistoricoDeRequisicao()) {
//                        if (r.getId() == idRequisicao) {
//                            requisicao = r;
//                            break;
//                        }
//                    }
//                    if (requisicao != null) {
////                        List<Produto> produtos = new ArrayList<>();
////                        String addMore;
//////                        do {
//////                            menu.exibirMenu();
//////                            System.out.print("Digite o ID do produto: ");
//////                            int idProduto = scanner.nextInt();
//////                            Produto produto = menu.getProdutoById(idProduto);
//////                            if (produto != null) {
//////                                produtos.add(produto);
//////                            } else {
//////                                System.out.println("Produto não encontrado.");
//////                            }
//////                            System.out.print("Deseja adicionar mais produtos? (s/n): ");
//////                            addMore = scanner.next();
//////                        } while (addMore.equalsIgnoreCase("s"));
//////
////
////                        Pedido pedido = restaurante.fazerPedido(produtos, requisicao);
////                        System.out.println("Pedido criado com sucesso! Valor total: " + pedido.calcularValorFinal());
////                    } else {
////                        System.out.println("Requisição não encontrada.");
////                    }
                    break;
                case 7:
                    System.out.println("Menu:");
                    System.out.println(menu.exibirMenu());
                    break;
                case 8:
//                    List<Pedido> pedidos = restaurante.getPedidos();
//                    if (pedidos.isEmpty()) {
//                        System.out.println("Não há pedidos no momento.");
//                    } else {
//                        for (Pedido pedido : pedidos) {
//                            System.out.println(pedido.formatPedido());
//                        }
//                    }
                    // opcional
                    break;
                case 9:
                    System.out.println("Fechando sistema!");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        } while (opcao != 9);

        scanner.close();
    }
}
