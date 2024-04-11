package codigo.codigoori.src;

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
        Mesa.criarMesa(capacidade);
        System.out.println("Mesa criada com sucesso.");
    }

    public static void main(String[] args) {
        System.out.println("Escolha uma opção:\n1 - Criar Mesa\n2 - Desocupar Mesa");
        int opcao = teclado.nextInt();

        switch (opcao) {
            case 1:
                System.out.println("Insira a capacidade da mesa:");
                int capacidade = teclado.nextInt();
                criarMesa(capacidade);
                break;
            case 2:
                System.out.println("Insira o id da mesa que deseja desocupar:");
                int requisicao = teclado.nextInt();
                Mesa.desocupar(requisicao);
                break;
            default:
                System.out.println("Opção inválida.");
        }

        teclado.close();
    }
}
