import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static Cliente clienteAtual;
    private static RestauranteV2 restaurante = new RestauranteV2();

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
                    desocuparMesa();
                    break;
                case 4:
                    System.out.println("Saindo do sistema...");
                    return;
                default:
                    System.out.println("Opção inválida");
            }
        }
    }

    private static void exibirMenu() {
        System.out.println("\nEscolha uma opção:");
        System.out.println("1. Cadastrar Cliente");
        System.out.println("2. Atender Cliente/Criar Requisição");
        System.out.println("3. Desocupar Mesa");
        System.out.println("4. Sair");
    }

    private static void cadastrarCliente() {
        System.out.println("Olá! Insira o nome do Cliente a Ser Cadastrado:");
        String nome = scanner.nextLine();
        clienteAtual = new Cliente(nome);
        System.out.println(clienteAtual.toString() + " foi cadastrado com sucesso!");
    }

    private static void atenderCliente() {
        // Implementação do método atenderCliente
    }

    private static void desocuparMesa() {
        // Implementação do método desocuparMesa
    }
}