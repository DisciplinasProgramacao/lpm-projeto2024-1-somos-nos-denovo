import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static Restaurante restaurante = new Restaurante(4, 2);
    static Cliente clienteAtual;

    public static void main(String[] args) {
        while (true) {
            exibirMenu();
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            switch (opcao) {
                case 1:
                    cadastrarCliente();
                    break;
                case 2:
                    atenderCliente();
                    break;
                case 3:
                    criarMesa();
                    break;
                case 4:
                    desocuparMesa();
                    break;
                case 5:
                    System.out.println("Saindo do sistema. Bip bip bop.");
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
        System.out.println("3. Criar Mesa");
        System.out.println("4. Desocupar Mesa");
        System.out.println("5. Sair");
    }

    private static void cadastrarCliente() {
        System.out.println("Olá! Bem-vindo ao restaurante! Insira seu nome:");
        String nome = scanner.nextLine();
        clienteAtual = new Cliente(nome,id);
        System.out.println(clienteAtual.toString() + " foi cadastrado com sucesso!");
    }

    private static void atenderCliente() {
        System.out.print("Qual o nome do cliente? ");
        String nomeCliente = scanner.nextLine();
        clienteAtual = Restaurante.localizarCliente(nomeCliente);
        if (clienteAtual == null) {
            System.out.println("Nenhum cliente encontrado");
            return;
        }
        System.out.println("Quantas pessoas? ");
        int qtdePessoas = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        Requisicao novaReq = clienteAtual.gerarRequisicao(qtdePessoas);
        restaurante.alocarNaMesa(novaReq);
    }

    private static void criarMesa() {
        System.out.println("Insira a capacidade da mesa:");
        int capacidade = scanner.nextInt();
        restaurante.criarMesa(capacidade);
    }

    private static void desocuparMesa() {
        System.out.println("Insira o id da mesa que deseja desocupar:");
        int idMesa = scanner.nextInt();
        restaurante.desocupar(idMesa);
    }
}
