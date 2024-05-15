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

    /**
     * Adiciona mesas ao restaurante.
     * @param quantidade Quantidade de mesas a serem adicionadas.
     * @param capacidade Capacidade de pessoas por mesa.
     */
    private void adicionarMesas(int quantidade, int capacidade) {
        for (int i = 0; i < quantidade; i++) {
            listaDeMesas.add(new Mesa(capacidade, true));
        }
    }

    /**
     * Aloca uma mesa para uma requisição de cliente.
     * @param requisicao Requisição do cliente.
     * @return true se a mesa foi alocada com sucesso, false caso contrário.
     */
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

    /**
     * Adiciona uma requisição à fila de espera.
     * @param requisicao Requisição do cliente.
     * @return true se a requisição foi adicionada à fila de espera com sucesso, false caso contrário.
     */
    public boolean entrarFilaEspera(Requisicao requisicao) {
        return filaDeEspera.offer(requisicao);
    }

     /**
     * Exibe a lista de espera.
     */
    public void exibirListaEspera() {
        System.out.println("Fila de Espera:");
        for (Requisicao req : filaDeEspera) {
            System.out.println(req.getCliente() + " - " + req.getQuantidade() + " pessoas");
        }
    }

    /**
     * Remove uma requisição da fila de espera.
     * @param requisicao Requisição a ser removida.
     * @return true se a requisição foi removida com sucesso, false caso contrário.
     */
    public boolean removerFilaEspera(Requisicao requisicao) {
        return filaDeEspera.remove(requisicao);
    }

    /**
     * Desocupa uma mesa no restaurante.
     * @param mesa Mesa a ser desocupada.
     * @return true se a mesa foi desocupada com sucesso, false caso contrário.
     */
    public boolean desocuparMesa(Mesa mesa) {
        if (!mesa.isDisponibilidade()) {
            mesa.setDisponibilidade(true);
            mesa.setRequisicao(null);
            return true;
        }
        return false;
    }

    /**
     * Fecha a conta de uma requisição, desocupando a mesa associada e alocando a próxima requisição da fila de espera, se houver.
     * @param requisicao Requisição a ser fechada.
     * @return true se a conta foi fechada com sucesso, false caso contrário.
     */
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

    /**
     * Gera uma nova requisição.
     * @param quantidade Quantidade de pessoas na requisição.
     * @param cliente Cliente associado à requisição.
     * @param data Data da requisição.
     * @param horaEntrada Hora de entrada no restaurante.
     * @param horaSaida Hora de saída do restaurante.
     * @return A requisição gerada.
     */
    public Requisicao gerarRequisicao(int quantidade, Cliente cliente, LocalDate data, LocalTime horaEntrada, LocalTime horaSaida) {
        return new Requisicao(quantidade, cliente, data, horaEntrada, horaSaida, this);
    }

     /**
     * Encontra uma mesa com base na requisição associada.
     * @param requisicao Requisição associada à mesa.
     * @return A mesa encontrada, ou null se não houver uma mesa associada à requisição.
     */
    private Mesa encontrarMesaPorRequisicao(Requisicao requisicao) {
        for (Mesa mesa : listaDeMesas) {
            if (!mesa.isDisponibilidade() && mesa.getRequisicao().equals(requisicao)) {
                return mesa;
            }
        }
        return null;
    }

}
