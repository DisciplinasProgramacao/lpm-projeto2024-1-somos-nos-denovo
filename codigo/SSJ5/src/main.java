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
        List<Requisicao> historicoRequisicoes = new ArrayList<>();
        List<Mesa> listaDeMesas = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("1 - Cadastrar Cliente");
            System.out.println("2 - Criar requisição");
            System.out.println("3 - Fechar conta");
            System.out.println("4 - Exibir histórico de requisições");
            System.out.println("5 - Exibir lista de espera");
            System.out.println("6 - Sair");
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
                    restaurante.listaDeClientes.add(novoCliente);
                    System.out.println("Cliente cadastrado com sucesso!");
                    break;
                case 2:
                    System.out.print("Digite o nome do cliente: ");
                    nome = scanner.next();
                    System.out.print("Digite a quantidade de pessoas: ");
                    int quantidade = scanner.nextInt();
                    restaurante.gerarRequisicao(quantidade, nome);
                    System.out.println("Requisicao criada com sucesso!");
                    break;
                case 3:
                    System.out.print("Digite o ID da mesa: ");
                    int idMesa = scanner.nextInt();
                    Requisicao requisicao = null;
                    for (Requisicao r : restaurante.historicoDeRequisicao) {
                        if (r.getMesa().getId() == idMesa) {
                            requisicao = r;
                            break;
                        }
                    }
                    if (requisicao != null) {
                        restaurante.fecharConta(requisicao);
                    } else {
                        System.out.println("Requisição não encontrada.");
                    }
                    break;
                case 4:
                    restaurante.exibirHistoricoDeRequisicoes();
                    break;
                case 5:
                    restaurante.exibirListaDeEspera();
                    break;
                case 6:
                    System.out.println("Fechando sistema!");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        } while (opcao != 6);

        scanner.close();
    }
}