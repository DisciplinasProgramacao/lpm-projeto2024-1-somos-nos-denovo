package codigo.SSJ5.src;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class Restaurante {

    private Requisicao requisicao;
    public List<Mesa> listaDeMesas;
    private Queue<Requisicao> filaDeEspera;
    public List<Requisicao> historicoDeRequisicoes;
    public List<Cliente> listaDeClientes;

    public Restaurante() {
        filaDeEspera = new LinkedList<>();
        listaDeClientes = new ArrayList<>();
        historicoDeRequisicoes = new ArrayList<>();
        listaDeMesas = new ArrayList<>();

        // Adiciona 4 mesas com capacidade para 4 pessoas
        adicionarMesas(4, 4);
        adicionarMesas(4, 6);
        adicionarMesas(2, 8);
    }

    private void adicionarMesas(int quantidade, int capacidade) {
        for (int i = 0; i < quantidade; i++) {
            listaDeMesas.add(new Mesa(capacidade, true));
        }
    }

    public boolean alocarMesa(Requisicao requisicao) {
        for (Mesa mesa : listaDeMesas) {
            if (mesa.isDisponibilidade() && mesa.getCapacidade() >= requisicao.getQuantidade()) {
                mesa.setDisponibilidade(false);
                mesa.setRequisicao(requisicao);
                historicoDeRequisicoes.add(requisicao);
                return true;
            }
        }
        return false;
    }

    public boolean entrarFilaEspera(Requisicao requisicao) {
        return filaDeEspera.offer(requisicao);
    }

    public void exibirListaEspera() {
        System.out.println("Fila de Espera:");
        for (Requisicao req : filaDeEspera) {
            System.out.println(req.getCliente() + " - " + req.getQuantidade() + " pessoas");
        }
    }

    public boolean removerFilaEspera(Requisicao requisicao) {
        return filaDeEspera.remove(requisicao);
    }

    public boolean desocuparMesa(Mesa mesa) {
        if (!mesa.isDisponibilidade()) {
            mesa.setDisponibilidade(true);
            mesa.setRequisicao(null);
            return true;
        }
        return false;
    }

    public boolean fecharConta(Requisicao requisicao) {
        Mesa mesa = encontrarMesaPorRequisicao(requisicao);
        if (mesa != null) {
            desocuparMesa(mesa);
            if (!filaDeEspera.isEmpty()) {
                Requisicao proxRequisicao = filaDeEspera.poll();
                return alocarMesa(proxRequisicao);
            }
            return true;
        }
        return false;
    }

    public Requisicao gerarRequisicao(int quantidade, Cliente cliente, LocalDate data, LocalTime horaEntrada, LocalTime horaSaida) {
        return new Requisicao(quantidade, cliente, data, horaEntrada, horaSaida, this);
    }

    private Mesa encontrarMesaPorRequisicao(Requisicao requisicao) {
        for (Mesa mesa : listaDeMesas) {
            if (!mesa.isDisponibilidade() && mesa.getRequisicao().equals(requisicao)) {
                return mesa;
            }
        }
        return null;
    }

}
