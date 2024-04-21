import java.util.Scanner;

/**
 * Esta classe representa um sistema simples de gerenciamento de restaurante.
 */
public class Main {
    static Scanner scanner = new Scanner(System.in);
    static Restaurante restaurante = new Restaurante(4, 2);
    static Cliente clienteAtual;

    /**
     * O método principal do sistema de gerenciamento de restaurante.
     * Ele exibe as opções de menu e lida com a entrada do usuário.
     * 
     * @param args Os argumentos da linha de comando (não utilizados).
     */
    public static void main(String[] args) {
        while (true) {
            exibirMenu();
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir nova linha
            switch (opcao) {
                case 1:
                    cadastrarCliente();
                    break;
                case 2:
                    atenderCliente();
                    break;
                case 3:
                    desocuparMesa(null);
                    break;
                case 4:
                    System.out.println("Saindo do sistema...");
                    return;
                default:
                    System.out.println("Opção inválida");
            }
        }
    }

    /**
     * Exibe o menu de opções para o usuário.
     */
    private static void exibirMenu() {
        System.out.println("\nEscolha uma opção:");
        System.out.println("1. Cadastrar Cliente");
        System.out.println("2. Atender Cliente/Criar Requisição");
        System.out.println("3. Desocupar Mesa");
        System.out.println("4. Sair");
    }

    /**
     * Permite ao usuário cadastrar um novo cliente no restaurante.
     */
    private static void cadastrarCliente() {
        System.out.println("Olá! Bem-vindo ao restaurante! Insira seu nome:");
        String nome = scanner.nextLine();
        System.out.println("Insira o ID do cliente:");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir nova linha
        clienteAtual = new Cliente(nome, id);
        System.out.println(clienteAtual.toString() + " foi cadastrado com sucesso!");
    }

    /**
     * Atende a solicitação de um cliente e aloca uma mesa, se possível.
     */
    private static void atenderCliente() {
        System.out.print("Qual o nome do cliente? ");
        String nomeCliente = scanner.nextLine();
        clienteAtual = restaurante.localizarCliente(nomeCliente);
        if (clienteAtual == null) {
            System.out.println("Nenhum cliente encontrado");
            return;
        }
        System.out.println("Quantas pessoas? ");
        int qtdePessoas = scanner.nextInt();
        scanner.nextLine(); // Consumir nova linha
        Requisicao novaReq = clienteAtual.gerarRequisicao(qtdePessoas);
        Restaurante.alocarNaMesa(novaReq);
    }

    /**
     * Desocupa uma mesa do restaurante.
     * 
     * @param requisicao A requisicao associada à mesa a ser desocupada.
     */
    private static void desocuparMesa(Requisicao requisicao) {
        System.out.println("Insira o id da mesa que deseja desocupar:");
        int idMesa = scanner.nextInt();
        restaurante.desocupar(requisicao);
    }
}
