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
                case 1 -> cadastrarCliente();
                case 2 -> gerarRequisicao(restaurante, clienteAtual);
                case 3 -> fecharRequisicao(restaurante, clienteAtual, null);
                case 4 -> fecharConta(restaurante, clienteAtual, null);
                case 5 -> localizarCliente(restaurante, clienteAtual);
                case 6 -> localizarRequisicao(restaurante, null, clienteAtual);
                case 7 -> removerDaFilaDeEspera(restaurante, null);
                case 8 -> System.out.println("Saindo do sistema...");
                default -> System.out.println("Opção inválida");
            }
        }
    }

    private static void exibirMenu() {
        System.out.println("\nEscolha uma opção:");
        System.out.println("1. Cadastrar Cliente");
        System.out.println("2. Atender Cliente");
        System.out.println("3. Desocupar Mesa");
        System.out.println("4. Fechar Conta");
        System.out.println("5. Localizar Cliente");
        System.out.println("6. Localizar Requisição");
        System.out.println("7. Remover da Fila de Espera");
        System.out.println("8. Sair");
    }

    private static void cadastrarCliente() {
        System.out.println("Olá! Insira o nome do Cliente a Ser Cadastrado:");
        String nome = scanner.nextLine();
        clienteAtual = new Cliente(nome);
        System.out.println(clienteAtual.toString() + " foi cadastrado com sucesso!");
    }

    public static void gerarRequisicao(RestauranteV2 restaurante, Cliente cliente) {
        boolean requisicaoGerada = restaurante.gerarRequisicao(cliente);

        if (requisicaoGerada) {
            System.out.println("Requisição gerada com sucesso.");
        } else {
            System.out.println("Falha ao gerar a requisição.");
        }
    }

    public static void fecharRequisicao(RestauranteV2 restaurante, Cliente cliente, Requisicao requisicao) {
        boolean requisicaoFechada = restaurante.fecharRequisicao(requisicao);

        if (requisicaoFechada) {
            System.out.println("Requisição fechada com sucesso.");
        } else {
            System.out.println("Falha ao fechar a requisição.");
        }
    }

    public static void localizarCliente(RestauranteV2 restaurante, Cliente cliente) {
        String resultado = restaurante.localizarCliente(cliente);

        if (!resultado.contains("não encontrado")) {
            System.out.println("Cliente localizado: " + cliente.getNome() + ". " + resultado);
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }

    public static void localizarRequisicao(RestauranteV2 restaurante, Requisicao requisicao, Cliente cliente) {
        String resultado = restaurante.localizarRequisicao(requisicao);

        if (!resultado.contains("não encontrada")) {
            System.out.println("Requisição localizada para o cliente: " + cliente.getNome() + ". " + resultado);
        } else {
            System.out.println("Requisição não encontrada para o cliente: " + cliente.getNome() + ".");
        }
    }

    public static void removerDaFilaDeEspera(RestauranteV2 restaurante, Requisicao requisicao) {
        boolean requisicaoRemovida = restaurante.removerDaFilaDeEspera(requisicao);

        if (requisicaoRemovida) {
            System.out.println("Requisição removida com sucesso.");
        } else {
            System.out.println("Falha ao remover a requisição.");
        }
    }

    private static void fecharConta(RestauranteV2 restaurante, Cliente cliente, Requisicao requisicao) {
        if (clienteAtual != null) {
            boolean contaFechada = restaurante.fecharConta(clienteAtual);
            if (contaFechada) {
                System.out.println("Conta fechada com sucesso.");
            } else {
                System.out.println("Falha ao fechar a conta.");
            }
        } else {
            System.out.println("Nenhum cliente atual para fechar a conta.");
        }
    }

}