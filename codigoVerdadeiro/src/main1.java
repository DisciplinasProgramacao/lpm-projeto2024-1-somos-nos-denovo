import java.util.Scanner; //classe Scanner

public class Main {
  static Scanner sc = new Scanner(System.in); // objeto sc do tipo Scanner

  public static void main(String[] args) {
    Cliente cliente1 = null; // objeto cliente1 do tipo Cliente
    while (true){ // Loop infinito para manter o sistema em execução até que o usuário escolha sair
      System.out.println("\nEscolha uma opção:"); // Exibe as opções disponíveis para o usuário
      System.out.println("1. Cadastrar Cliente");
      System.out.println("2. Atender Cliente/Criar Requisição");
      System.out.println("3. Sair");
      int opcao = sc.nextInt(); // Lê a opção escolhida pelo usuário
      sc.nextLine(); // Limpa o buffer do Scanner

      switch(opcao){ // Estrutura de controle switch 
        case 1:
          String nome; // Declaração da variável nome para armazenar o nome do cliente
          System.out.println("Olá! Bem-vindo ao restaurante! Insira seu nome:"); // Solicita o nome do cliente
          nome = sc.nextLine(); // Lê o nome do cliente
          cliente1 = new Cliente(nome); // Cria um novo objeto Cliente com o nome fornecido
          System.out.println(cliente1.toString()+" foi cadastrado com sucesso!"); // Confirma o cadastro do cliente
          break;

        case 2:  
          System.out.print("Qual o nome do cliente?")
          String nomeClie = sc.nextLine
          Cliente clienteAtual = localizarCliente();
          
          if(clienteAtual == null){ 
            System.out.println("Nenhum cliente encontrado");
            break;
          }
          System.out.println("Quantas pessoas? "); // Solicita a quantidade de pessoas
          int qtdePessoas = sc.nextInt(); // Lê a quantidade de pessoas
          Requisicao novaReq = clienteAtual.gerarRequisicao(qtdePessoas); // Gera uma nova requisição com a quantidade de pessoas fornecida
          rest.alocarNaMesa(novaReq);
          break;

        case 3:
          System.out.println("saindo do sistema. bip bip bop"); // Mensagem de saída do sistema
          return; // Termina a execução do programa

        default:
          System.out.println("Opção inválida"); // Mensagem de opção inválida caso o usuário escolha uma opção desconhecida
          break;
      }
    }
  }
}
