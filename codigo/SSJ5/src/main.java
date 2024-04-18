import java.util.Scanner; //classe Scanner

public class Main {
    static Scanner teclado = new Scanner(System.in); // objeto teclado do tipo Scanner

    private static int proximoIdMesa = 1;

    /**
     * Método para criar uma nova mesa 
     * 
     * @param capacidade  A capacidade da mesa (quantidade de pessoas).
     */
    private static void criarMesa(int capacidade) {
        Mesa novaMesa = new Mesa(4, true);
        System.out.println("Mesa criada com sucesso.");
    }

    public static void main(String[] args) {
        Cliente cliente1 = null; // objeto cliente1 do tipo Cliente
        Restaurante restaurante = new Restaurante(4,2); // objeto restaurante do tipo Restaurante

        while (true) { // Loop infinito para manter o sistema em execução até que o usuário escolha sair
            System.out.println("\nEscolha uma opção:"); // Exibe as opções disponíveis para o usuário
            System.out.println("1. Cadastrar Cliente");
            System.out.println("2. Atender Cliente/Criar Requisição");
            System.out.println("3. Criar Mesa");
            System.out.println("4. Desocupar Mesa");
            System.out.println("5. Sair");
            int opcao = teclado.nextInt(); // Lê a opção escolhida pelo usuário
            teclado.nextLine(); // Limpa o buffer do Scanner

            switch (opcao) { // Estrutura de controle switch 
                case 1:
                    String nome; // Declaração da variável nome para armazenar o nome do cliente
                    System.out.println("Olá! Bem-vindo ao restaurante! Insira seu nome:"); // Solicita o nome do cliente
                    nome = teclado.nextLine(); // Lê o nome do cliente
                    cliente1 = new Cliente(nome); // Cria um novo objeto Cliente com o nome fornecido
                    System.out.println(cliente1.toString() + " foi cadastrado com sucesso!"); // Confirma o cadastro do cliente
                    break;

                case 2:
                    System.out.print("Qual o nome do cliente? ");
                    String nomeClie = teclado.nextLine();
                    Cliente clienteAtual = localizarCliente();

                    if (clienteAtual == null) {
                        System.out.println("Nenhum cliente encontrado");
                        break;
                    }
                    System.out.println("Quantas pessoas? "); // Solicita a quantidade de pessoas
                    int qtdePessoas = teclado.nextInt(); // Lê a quantidade de pessoas
                    Requisicao novaReq = clienteAtual.gerarRequisicao(qtdePessoas); // Gera uma nova requisição com a quantidade de pessoas fornecida
                    restaurante.alocarNaMesa(novaReq);
                    break;

                case 3:
                    System.out.println("Insira a capacidade da mesa:");
                    int capacidade = teclado.nextInt();
                    criarMesa(capacidade);
                    break;

                case 4:
                    System.out.println("Insira o id da mesa que deseja desocupar:");
                    int requisicao = teclado.nextInt();
                    Mesa.desocupar(requisicao);
                    break;

                case 5:
                    System.out.println("Saindo do sistema. Bip bip bop."); // Mensagem de saída do sistema
                    return; // Termina a execução do programa

                default:
                    System.out.println("Opção inválida"); // Mensagem de opção inválida caso o usuário escolha uma opção desconhecida
                    break;
            }
        }
    }
}
