import java.util.Scanner; 

public class Main {
    static Scanner teclado = new Scanner(System.in);

    private static int proximoIdMesa = 1;

    /**
     * Método para criar uma nova mesa 
     * 
     * @param capacidade  A capacidade da mesa (quantidade de pessoas).
     */
    private static void criarMesa(int capacidade) {
        Mesa novaMesa = new Mesa(capacidade);
        System.out.println("Mesa criada com sucesso.");
    }

    public static void main(String[] args) {
        Cliente cliente1 = null; 
        Restaurante restaurante = new Restaurante(); 

        while (true) { 
            System.out.println("\nEscolha uma opção:"); 
            System.out.println("1. Cadastrar Cliente");
            System.out.println("2. Atender Cliente/Criar Requisição");
            System.out.println("3. Criar Mesa");
            System.out.println("4. Desocupar Mesa");
            System.out.println("5. Sair");
            int opcao = teclado.nextInt(); 
            teclado.nextLine(); 

            switch (opcao) { 
                case 1:
                    String nome; 
                    System.out.println("Olá! Bem-vindo ao restaurante! Insira seu nome:"); 
                    nome = teclado.nextLine(); 
                    cliente1 = new Cliente(nome); 
                    System.out.println(cliente1.toString() + " foi cadastrado com sucesso!"); 
                    break;

                case 2:
                    System.out.print("Qual o nome do cliente? ");
                    String nomeClie = teclado.nextLine();
                    Cliente clienteAtual = localizarCliente();

                    if (clienteAtual == null) {
                        System.out.println("Nenhum cliente encontrado");
                        break;
                    }
                    System.out.println("Quantas pessoas? "); 
                    int qtdePessoas = teclado.nextInt();
                    Requisicao novaReq = clienteAtual.gerarRequisicao(qtdePessoas); 
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
                    System.out.println("Saindo do sistema. Bip bip bop."); 
                    return; 

                default:
                    System.out.println("Opção inválida"); 
                    break;
            }
        }
    }
}