package codigo.SSJ5.src;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class Restaurante {
    private  Requisicao requisicao;
    private List<Mesa> listaDeMesas;
    private Queue<Requisicao> filaDeEspera;
    public List<Requisicao> historicoDeRequisicao;
    public List<Cliente> listaDeClientes;
    public Restaurante() {
        filaDeEspera = new LinkedList<>();
        listaDeClientes = new ArrayList<>();
        historicoDeRequisicao = new ArrayList<>();
        listaDeMesas = new ArrayList<>();
// Adiciona 4 mesas com capacidade para 4 pessoas
        for (int i = 0; i < 4; i++) {
            listaDeMesas.add(new Mesa(4 , true));
        }
// Adiciona 4 mesas com capacidade para 6 pessoas
        for (int i = 0; i < 4; i++) {
            listaDeMesas.add(new Mesa(6 , true));
        }
// Adiciona 2 mesas com capacidade para 8 pessoas
        for (int i = 0; i < 2; i++) {
            listaDeMesas.add(new Mesa(8, true));
        }

    }

    // Método para alocar uma mesa em uma Requisicao
    public boolean alocarNaRequisicao(Requisicao requisicao) {
        for (Mesa mesaDisponivel : listaDeMesas) {
            if (mesaDisponivel.getCapacidade() >= requisicao.getQuantidade() && mesaDisponivel.isDisponibilidade()) {
                mesaDisponivel.setDisponibilidade(false);
                requisicao.setMesa(mesaDisponivel);
                System.out.println("Requisição alocada na mesa ID: " + mesaDisponivel.getId());
                return true;
            }
        }
        System.out.println("Nenhuma mesa disponível para alocar a requisição.");
        return false;
    }

    // Método para adicionar um cliente à lista de espera
    public boolean entrarNaFilaDeEspera(Requisicao requisicao) {
        filaDeEspera.offer(requisicao);
        return true;
    }
    
    public void exibirListaDeEspera() {
        if (filaDeEspera.isEmpty()) {
            System.out.println("Não há clientes na lista de espera.");
        } else {
            System.out.println("Lista de espera:");
            for (Requisicao requisicao : filaDeEspera) {
                System.out.println("Cliente: " + requisicao.getCliente().getNome() + ", Quantidade: " + requisicao.getQuantidade());
            }
        }
    }

    public  boolean desocuparMesa(Requisicao requisicao, Mesa mesa){
        if (!mesa.isDisponibilidade()) {
            mesa.setDisponibilidade(true);
        }
        return true;
    }


    public boolean fecharConta(Requisicao requisicao) {
        System.out.println("Sua conta da requisicao: "+requisicao.getId()+" foi fechada com sucesso!");
        Mesa mesa = requisicao.getMesa();
        mesa.setDisponibilidade(true);
        if (filaDeEspera.contains(requisicao)) {
            filaDeEspera.remove(requisicao);
        }
        // Verifica a fila de espera e aloca a próxima requisição na mesa se possível
        for (Requisicao r : filaDeEspera) {
            if (mesa.getCapacidade() >= r.getQuantidade()) {
                r.setMesa(mesa);
                mesa.setDisponibilidade(false);
                filaDeEspera.remove(r);
                System.out.println("Requisição alocada na mesa ID: " + mesa.getId() + " após fechar a conta.");
                break;
            }
        }
        return true;
    }

    public Requisicao gerarRequisicao(int quantidade, String nome){
        Cliente clienteExistente = null;
        for (Cliente cliente : listaDeClientes) {
            if (cliente.getNome().equals(nome)) {
                clienteExistente = cliente;
                break;
            }
        }
        Requisicao requisicao;
        if (clienteExistente != null) {
            requisicao = new Requisicao(quantidade, clienteExistente, LocalDate.now(), LocalTime.now(), null, this);
        } else {
            System.out.println("Cliente nao cadastrado, favor digitar o nome do novo cliente:");
            Cliente novoCliente = new Cliente(nome);
            listaDeClientes.add(novoCliente);
            requisicao = new Requisicao(quantidade, novoCliente, LocalDate.now(), LocalTime.now(), null, this);
        }
        if (alocarNaRequisicao(requisicao)) {
            historicoDeRequisicao.add(requisicao);
        } else {
            entrarNaFilaDeEspera(requisicao);
        }

        return requisicao;
    }

    public void exibirHistoricoDeRequisicoes() {
        if (historicoDeRequisicao.isEmpty()) {
            System.out.println("O histórico de requisições está vazio.");
        } else {
            System.out.println("Histórico de requisições:");
            for (Requisicao requisicao : historicoDeRequisicao) {
                System.out.println("Cliente: " + requisicao.getCliente().getNome() + ", Quantidade: " + requisicao.getQuantidade() + ", Mesa: " + requisicao.getMesa().getId());
            }}}


}



