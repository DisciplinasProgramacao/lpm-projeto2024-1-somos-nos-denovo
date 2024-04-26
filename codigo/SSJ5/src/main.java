import java.util.Scanner;
import java.util.HashMap;

public class Main {
    static Scanner teclado = new Scanner(System.in);
    static HashMap<String, Cliente> clientes = new HashMap<>();

    
    

    private static Cliente localizarCliente(String nome) {
        return clientes.getOrDefault(nome, null);
    }

    public static void main(String[] args) {
        Restaurante restaurante = new Restaurante();

        while (true) {
            System.out.println("\nEscolha uma opção:");
            System.out.println("1. Cadastrar Cliente");
            System.out.println("2. Atender Cliente/Criar Requisição");
            System.out.println("3. Criar Mesa");
            System.out.println("4. Desocupar Mesa");
            System.out.println("5. Sair");
            System.out.println("6. Listar mesas cadastradas no sistema");
            int opcao = teclado.nextInt(); 
            teclado.nextLine();

            switch (opcao) {
                case 1:
                    System.out.println("Insira seu nome:");
                    String nome = teclado.nextLine();
                    Cliente novoCliente = new Cliente(nome);
                    clientes.put(nome, novoCliente);
                    System.out.println(novoCliente + " foi cadastrado com sucesso!");
                    break;
                case 2:
                    System.out.println("Qual o nome do cliente?");
                    nome = teclado.nextLine();
                    Cliente clienteAtual = localizarCliente(nome);
                    if (clienteAtual == null) {
                        System.out.println("Nenhum cliente encontrado com esse nome.");
                        break;
                    }
                    System.out.println("Quantas pessoas?");
                    int qtdePessoas = teclado.nextInt();
                    Requisicao novaReq = clienteAtual.gerarRequisicao(qtdePessoas);
                    boolean alocado = restaurante.alocarNaMesa(novaReq);
                    if (!alocado) {
                        System.out.println("Não foi possível alocar uma mesa. Tente novamente mais tarde ou reduza o número de pessoas.");
                    }
                    break;
                    case 3:
                    System.out.println("Insira a capacidade da mesa:");
                    int capacidade = teclado.nextInt();
                    restaurante.criarMesa(capacidade);
                    break;
                
                case 4:
                    System.out.println("Insira o id da mesa que deseja desocupar:");
                    int idMesa = teclado.nextInt();
                    restaurante.desocuparMesa(idMesa);
                    break;
                case 5:
                    System.out.println("Saindo do sistema. Obrigado por usar nosso serviço.");
                    teclado.close();
                    return;
                    case 6:
                    restaurante.listarMesas();
                    break;
                
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }
    }
}
