package codigo.SSJ5.src;

import java.util.Scanner;

public class Main {
   
    // TODO: Implementar esse metodo para limpar a tela corretamente 
    public static void limparTela() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void main(String[] args) {
        Menu menu = new Menu();
        Restaurante restaurante = new Restaurante(menu);
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
                restaurante.adicionarCliente(nome);
                    System.out.println("Cliente cadastrado com sucesso!");
                    break;
                    case 2:
                    System.out.print("Digite o nome do cliente: ");
                    nome = scanner.next();
                    System.out.print("Digite a quantidade de pessoas: ");
                    int quantidade = scanner.nextInt();
                    restaurante.gerarRequisicao(quantidade, nome);
                    System.out.println("Requisição criada com sucesso!");
                    break;
                    case 3:
                    System.out.print("Digite o ID da mesa: ");
                    int idMesa = scanner.nextInt();
                    boolean contaFechada = restaurante.fecharConta(idMesa);
                    if (contaFechada) {
                        System.out.println("Sua conta da requisição foi fechada com sucesso!");
                    } else {
                        System.out.println("Requisição não encontrada.");
                    }
                    break;
                    case 4:
                    if (restaurante.exibirHistoricoDeRequisicoes().isEmpty()) {
                        System.out.println("Não há histórico de requisições.");
                    }
                    System.out.println(restaurante.exibirHistoricoDeRequisicoes());
                    break;
                    case 5:
                    System.out.println(restaurante.exibirListaDeEspera());
                    break;
                    case 6:
                    System.out.print("Digite o ID da requisição: ");
                    int idRequisicao = scanner.nextInt();
                    System.out.println(menu.exibirMenu());
                    System.out.print("Qual item você deseja? ");
                    int idItem = scanner.nextInt();
                    restaurante.fazerPedido(idRequisicao, idItem);
                    break;
                    case 7:
                    System.out.println("Menu:");
                    System.out.println(menu.exibirMenu());
                    break;
                    case 8:
                    System.out.println(restaurante.exibirPedidos());
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
