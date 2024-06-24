package codigo.SSJ5.src.oi;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class Restaurante {
    private List<Mesa> listaDeMesas;
    private Queue<Requisicao> filaDeEspera;
    private List<Requisicao> historicoDeRequisicao;
    private List<Cliente> listaDeClientes;
    private MenuAberto menuAberto;
    private MenuFechado menuFechado;

    public Restaurante() {
        this.menuAberto = new MenuAberto();
        this.menuFechado = new MenuFechado();
        filaDeEspera = new LinkedList<>();
        listaDeClientes = new ArrayList<>();
        historicoDeRequisicao = new ArrayList<>();
        listaDeMesas = new ArrayList<>();
        initMesas();
    }

    private void initMesas() {
        for (int i = 0; i < 4; i++) listaDeMesas.add(new Mesa(4, true));
        for (int i = 0; i < 4; i++) listaDeMesas.add(new Mesa(6, true));
        for (int i = 0; i < 2; i++) listaDeMesas.add(new Mesa(8, true));
    }

    public void adicionarCliente(String nome) {
        listaDeClientes.add(new Cliente(nome));
    }

    public boolean alocarNaRequisicao(Requisicao requisicao) {
        return listaDeMesas.stream()
            .filter(mesa -> mesa.getCapacidade() >= requisicao.getQuantidade() && mesa.isDisponibilidade())
            .findFirst()
            .map(mesa -> {
                mesa.setDisponibilidade(false);
                requisicao.setMesa(mesa);
                return true;
            })
            .orElse(false);
    }

    public boolean entrarNaFilaDeEspera(Requisicao requisicao) {
        return filaDeEspera.offer(requisicao);
    }

    public List<String> exibirListaDeEspera() {
        return filaDeEspera.isEmpty() ?
            Collections.singletonList("Não há clientes na lista de espera.") :
            filaDeEspera.stream()
                .map(r -> String.format("Cliente: %s, Quantidade: %d", r.getCliente().getNome(), r.getQuantidade()))
                .toList();
    }

    public void desocuparMesa(Mesa mesa) {
        if (!mesa.isDisponibilidade()) {
            mesa.setDisponibilidade(true);
        }
    }

    public boolean fecharConta(int idMesa) {
        return historicoDeRequisicao.stream()
            .filter(r -> r.getMesa().getId() == idMesa && r.getHoraSaida() == null)
            .findFirst()
            .map(requisicao -> {
                requisicao.fecharRequisicao();
                alocarDaListaDeEspera(requisicao.getMesa().getCapacidade());
                return true;
            })
            .orElse(false);
    }

    private void alocarDaListaDeEspera(int capacidadeMesa) {
        filaDeEspera.stream()
            .filter(r -> capacidadeMesa >= r.getQuantidade())
            .findFirst()
            .ifPresent(this::realocarRequisicao);
    }

    private void realocarRequisicao(Requisicao requisicao) {
        if (alocarNaRequisicao(requisicao)) {
            filaDeEspera.remove(requisicao);
            historicoDeRequisicao.add(requisicao);
        } else {
            entrarNaFilaDeEspera(requisicao);
        }
    }

    public Requisicao gerarRequisicao(int quantidade, String nome) {
        Cliente clienteExistente = listaDeClientes.stream()
            .filter(cliente -> cliente.getNome().equals(nome))
            .findFirst()
            .orElseGet(() -> {
                Cliente novoCliente = new Cliente(nome);
                listaDeClientes.add(novoCliente);
                return novoCliente;
            });

        Requisicao requisicao = new Requisicao(quantidade, clienteExistente, LocalDate.now(), LocalTime.now());
        if (alocarNaRequisicao(requisicao)) {
            historicoDeRequisicao.add(requisicao);
        } else {
            entrarNaFilaDeEspera(requisicao);
            System.out.println("Mesas cheias! Cliente adicionado à lista de espera.");
        }
        return requisicao;
    }

    public boolean criarPedido(int idRequisicao, boolean fechado) {
        Optional<Requisicao> requisicaoOpt = localizarRequisicao(idRequisicao);
        if (requisicaoOpt.isPresent()) {
            Requisicao requisicao = requisicaoOpt.get();
            IPedido pedido = fechado ? new PedidoFechado() : new PedidoAberto();
            requisicao.setPedido(pedido);
            return true;
        }
        return false;
    }

    public boolean adicionarProduto(int idRequisicao, int idProduto, boolean fechado) {
        Optional<Requisicao> requisicaoOpt = localizarRequisicao(idRequisicao);
        if (requisicaoOpt.isPresent()) {
            Requisicao requisicao = requisicaoOpt.get();
            Produto produto = (fechado ? menuFechado : menuAberto).getProdutoById(idProduto);
            if (produto != null) {
                requisicao.getPedido().addProduto(produto);
                return true;
            }
        }
        return false;
    }

    public Optional<Requisicao> localizarRequisicao(int idRequisicao) {
        return historicoDeRequisicao.stream()
            .filter(r -> r.getId() == idRequisicao)
            .findFirst();
    }

    public String exibirHistorico() {
        return historicoDeRequisicao.stream()
            .map(Requisicao::getRequisicaoInfo)
            .reduce("Histórico de Requisições:\n", (partialString, element) -> partialString + element + "\n");
    }

    public String exibirMenuAberto() {
        return menuAberto.exibirMenu();
    }

    public String exibirMenuFechado() {
        return menuFechado.exibirMenu();
    }
}
