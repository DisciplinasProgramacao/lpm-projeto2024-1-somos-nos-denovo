package codigo.SSJ5.src.oi;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

/**
 * Representa um restaurante com funcionalidades de gestão de mesas, clientes, menus,
 * requisições e histórico de pedidos.
 */
public class Restaurante {
    private List<Mesa> listaDeMesas;
    private Queue<Requisicao> filaDeEspera;
    private List<Requisicao> historicoDeRequisicao;
    private List<Cliente> listaDeClientes;
    private MenuAberto menuAberto;
    private MenuFechado menuFechado;

    /**
     * Construtor que inicializa o restaurante com mesas, menus e estruturas de dados vazias.
     */
    public Restaurante() {
        this.menuAberto = new MenuAberto();
        this.menuFechado = new MenuFechado();
        filaDeEspera = new LinkedList<>();
        listaDeClientes = new ArrayList<>();
        historicoDeRequisicao = new ArrayList<>();
        listaDeMesas = new ArrayList<>();
        initMesas();
    }

    /**
     * Inicializa as mesas do restaurante com capacidades pré-definidas.
     */
    private void initMesas() {
        for (int i = 0; i < 4; i++) listaDeMesas.add(new Mesa(4, true));
        for (int i = 0; i < 4; i++) listaDeMesas.add(new Mesa(6, true));
        for (int i = 0; i < 2; i++) listaDeMesas.add(new Mesa(8, true));
    }

    /**
     * Adiciona um novo cliente à lista de clientes do restaurante.
     *
     * @param nome Nome do cliente a ser adicionado.
     */
    public void adicionarCliente(String nome) {
        listaDeClientes.add(new Cliente(nome));
    }

    /**
     * Tenta alocar uma requisição em uma mesa disponível no restaurante.
     *
     * @param requisicao Requisição a ser alocada.
     * @return true se a requisição foi alocada com sucesso, false caso contrário.
     */
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

    /**
     * Coloca uma requisição na fila de espera do restaurante.
     *
     * @param requisicao Requisição a ser colocada na fila de espera.
     * @return true se a requisição foi adicionada à fila com sucesso, false caso contrário.
     */
    public boolean entrarNaFilaDeEspera(Requisicao requisicao) {
        return filaDeEspera.offer(requisicao);
    }

    /**
     * Retorna uma lista formatada dos clientes na fila de espera.
     *
     * @return Lista de clientes na fila de espera ou mensagem informando que não há clientes na fila.
     */
    public List<String> exibirListaDeEspera() {
        return filaDeEspera.isEmpty() ?
            Collections.singletonList("Não há clientes na lista de espera.") :
            filaDeEspera.stream()
                .map(r -> String.format("Cliente: %s, Quantidade: %d", r.getCliente().getNome(), r.getQuantidade()))
                .toList();
    }

    /**
     * Libera uma mesa específica do restaurante, marcando-a como disponível.
     *
     * @param mesa Mesa a ser desocupada.
     */
    public void desocuparMesa(Mesa mesa) {
        if (!mesa.isDisponibilidade()) {
            mesa.setDisponibilidade(true);
        }
    }

    /**
     * Fecha a conta de uma mesa específica, marcando a requisição associada como atendida e
     * realocando clientes da lista de espera, se houver capacidade disponível.
     *
     * @param idMesa ID da mesa cuja conta será fechada.
     * @return true se a conta foi fechada com sucesso, false caso contrário.
     */
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

    /**
     * Tenta realocar uma requisição da fila de espera para uma mesa disponível no restaurante.
     *
     * @param capacidadeMesa Capacidade mínima da mesa necessária para alocar a requisição.
     */
    private void alocarDaListaDeEspera(int capacidadeMesa) {
        filaDeEspera.stream()
            .filter(r -> capacidadeMesa >= r.getQuantidade())
            .findFirst()
            .ifPresent(this::realocarRequisicao);
    }

    /**
     * Realoca uma requisição da fila de espera para uma mesa disponível no restaurante.
     * Se não houver mesas disponíveis, a requisição continua na fila de espera.
     *
     * @param requisicao Requisição a ser realocada.
     */
    private void realocarRequisicao(Requisicao requisicao) {
        if (alocarNaRequisicao(requisicao)) {
            filaDeEspera.remove(requisicao);
            historicoDeRequisicao.add(requisicao);
        } else {
            entrarNaFilaDeEspera(requisicao);
        }
    }

    /**
     * Gera uma nova requisição para um cliente com a quantidade especificada,
     * alocando-a em uma mesa disponível ou colocando-a na fila de espera.
     *
     * @param quantidade Quantidade de pessoas na requisição.
     * @param nome       Nome do cliente associado à requisição.
     * @return A requisição criada.
     */
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

    /**
     * Cria um pedido associado a uma requisição específica, aberto ou fechado.
     *
     * @param idRequisicao ID da requisição à qual o pedido será associado.
     * @param fechado      true se o pedido for fechado, false se for aberto.
     * @return true se o pedido foi criado com sucesso, false caso contrário.
     */
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

    /**
     * Adiciona um produto ao pedido de uma requisição específica.
     *
     * @param idRequisicao ID da requisição à qual o produto será adicionado.
     * @param idProduto    ID do produto a ser adicionado.
     * @param fechado      true se o produto pertence ao menu fechado, false se pertence ao menu aberto.
     * @return true se o produto foi adicionado com sucesso, false caso contrário.
     */
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

    /**
     * Localiza uma requisição pelo seu ID no histórico de requisições.
     *
     * @param idRequisicao ID da requisição a ser localizada.
     * @return Optional contendo a requisição se encontrada, ou Optional vazio se não encontrada.
     */
    public Optional<Requisicao> localizarRequisicao(int idRequisicao) {
        return historicoDeRequisicao.stream()
            .filter(r -> r.getId() == idRequisicao)
            .findFirst();
    }

    /**
     * Retorna uma representação textual do histórico de requisições do restaurante.
     *
     * @return String contendo todas as requisições do histórico formatadas.
     */
    public String exibirHistorico() {
        return historicoDeRequisicao.stream()
            .map(Requisicao::getRequisicaoInfo)
            .reduce("Histórico de Requisições:\n", (partialString, element) -> partialString + element + "\n");
    }

    /**
     * Exibe o menu aberto do restaurante.
     *
     * @return String contendo a lista de produtos do menu aberto.
     */
    public String exibirMenuAberto() {
        return menuAberto.exibirMenu();
    }

    /**
     * Exibe o menu fechado do restaurante.
     *
     * @return String contendo a lista de produtos do menu fechado.
     */
    public String exibirMenuFechado() {
        return menuFechado.exibirMenu();
    }
}
