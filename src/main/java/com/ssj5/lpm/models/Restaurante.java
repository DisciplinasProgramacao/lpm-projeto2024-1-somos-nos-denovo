package com.ssj5.lpm.models;

import com.ssj5.lpm.repository.ClienteRepository;
import com.ssj5.lpm.repository.MesaRepository;
import com.ssj5.lpm.repository.PedidoAbertoRepository;
import com.ssj5.lpm.repository.PedidoFechadoRepository;
import com.ssj5.lpm.repository.RequisicaoRepository;

import jakarta.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Representa um restaurante com funcionalidades para gerenciar mesas, pedidos, clientes e menu.
 */
@Component
public class Restaurante {

    @Autowired
    private MesaRepository mesaRepository;

    @Autowired
    private RequisicaoRepository requisicaoRepository;

    @Autowired
    private PedidoAbertoRepository pedidoAbertoRepository;

    @Autowired
    private PedidoFechadoRepository pedidoFechadoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private MenuAberto menuAberto;

    @Autowired
    private MenuFechado menuFechado;

    private Queue<Requisicao> filaDeEspera;
    private List<Requisicao> historicoDeRequisicao;
    private List<Cliente> listaDeClientes;
    private Map<Mesa, Requisicao> mesas = new HashMap<>();

    /**
     * Inicializa o restaurante, incluindo mesas, fila de espera, histórico de requisições e clientes.
     */
    @PostConstruct
    public void init() {
        filaDeEspera = new LinkedList<>();
        historicoDeRequisicao = new ArrayList<>();
        listaDeClientes = new ArrayList<>();
        initMesasIfNotExists();
        System.out.println("Restaurante inicializado com sucesso.");
    }

    /**
     * Inicializa as mesas se não existirem no banco de dados.
     */
    public void initMesasIfNotExists() {
        if (mesaRepository.count() == 0) {
            int[] capacidades = { 4, 6, 8 };
            int[] quant = { 4, 4, 2 };

            for (int i = 0; i < quant.length; i++) {
                int quantidade = quant[i];
                for (int j = 0; j < quantidade; j++) {
                    Mesa mesa = new Mesa(capacidades[i], true);
                    mesas.put(mesa, null);
                    mesaRepository.save(mesa);
                }
            }
        } else {
            List<Mesa> mesasFromDB = mesaRepository.findAll();
            mesasFromDB.forEach(mesa -> mesas.put(mesa, null));
        }
    }

    /**
     * Adiciona um cliente ao restaurante se não existir.
     *
     * @param nome O nome do cliente a ser adicionado.
     */
    public void adicionarCliente(String nome) {
        Cliente clienteExistente = buscarCliente(nome);
        if (clienteExistente == null) {
            clienteExistente = new Cliente(nome);
            listaDeClientes.add(clienteExistente);
            clienteRepository.save(clienteExistente);
        }
    }

    /**
     * Aloca uma mesa para uma requisição, se houver uma mesa disponível com capacidade suficiente.
     *
     * @param requisicao A requisição para alocar mesa.
     * @return true se a mesa foi alocada com sucesso, false caso contrário.
     */
    public boolean alocarNaRequisicao(Requisicao requisicao) {
        return mesaRepository.findAll().stream()
            .filter(mesa -> mesa.getCapacidade() >= requisicao.getQuantidade() && mesa.isDisponibilidade())
            .findFirst()
            .map(mesa -> {
                mesa.setDisponibilidade(false);
                requisicao.setMesa(mesa);
                mesaRepository.save(mesa);
                return true;
            })
            .orElse(false);
    }

    /**
     * Adiciona uma requisição à fila de espera.
     *
     * @param requisicao A requisição a ser adicionada à fila de espera.
     * @return true se a requisição foi adicionada com sucesso, false caso contrário.
     */
    public boolean entrarNaFilaDeEspera(Requisicao requisicao) {
        return filaDeEspera.offer(requisicao);
    }

    /**
     * Exibe a lista de espera atual.
     *
     * @return Uma lista de strings representando os clientes na lista de espera.
     */
    public List<String> exibirListaDeEspera() {
        return filaDeEspera.isEmpty() ?
            Collections.singletonList("Não há clientes na lista de espera.") :
            filaDeEspera.stream()
                .map(r -> String.format("Cliente: %s, Quantidade: %d", r.getCliente().getNome(), r.getQuantidade()))
                .collect(Collectors.toList());
    }

    /**
     * Desocupa uma mesa especificada, tornando-a disponível.
     *
     * @param mesa A mesa a ser desocupada.
     */
    public void desocuparMesa(Mesa mesa) {
        if (!mesa.isDisponibilidade()) {
            mesa.desocupar();
            mesaRepository.save(mesa);
        }
    }

    /**
     * Fecha a conta de uma mesa especificada, desocupando-a e realocando da lista de espera, se necessário.
     *
     * @param idMesa O ID da mesa cuja conta será fechada.
     * @return true se a conta foi fechada com sucesso, false caso contrário.
     */
    public boolean fecharConta(int idMesa) {
        Optional<Requisicao> requisicaoOpt = requisicaoRepository.findAll().stream()
            .filter(r -> r.getMesa().getId() == idMesa && r.getHoraSaida() == null)
            .findFirst();
    
        if (requisicaoOpt.isPresent()) {
            Requisicao requisicao = requisicaoOpt.get();
            requisicao.fecharRequisicao();
    
            Mesa mesa = requisicao.getMesa();
            mesa.desocupar();
            mesaRepository.save(mesa);
    
            alocarDaListaDeEspera(mesa.getCapacidade());
            requisicaoRepository.save(requisicao);
    
            return true;
        } else {
            return false;
        }
    }

    /**
     * Realoca uma requisição da lista de espera para uma mesa disponível.
     *
     * @param capacidadeMesa A capacidade mínima da mesa para realocar a requisição.
     */
    private void alocarDaListaDeEspera(int capacidadeMesa) {
        filaDeEspera.stream()
            .filter(r -> capacidadeMesa >= r.getQuantidade())
            .findFirst()
            .ifPresent(this::realocarRequisicao);
    }

    /**
     * Realoca uma requisição da lista de espera para uma mesa disponível.
     *
     * @param requisicao A requisição a ser realocada.
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
     * Gera uma nova requisição para o restaurante.
     *
     * @param quantidade A quantidade de pessoas na requisição.
     * @param nome O nome do cliente para a requisição.
     * @return A requisição gerada.
     */
    public Requisicao gerarRequisicao(int quantidade, String nome) {
        Cliente clienteExistente = buscarCliente(nome);
        if (clienteExistente == null) {
            clienteExistente = new Cliente(nome);
            listaDeClientes.add(clienteExistente);
            clienteRepository.save(clienteExistente);
        }
        
        Requisicao requisicao = new Requisicao(clienteExistente, LocalDate.now().atTime(LocalTime.now()), quantidade);
        if (alocarNaRequisicao(requisicao)) {
            requisicaoRepository.save(requisicao);
        } else {
            entrarNaFilaDeEspera(requisicao);
            System.out.println("Mesas cheias! Cliente adicionado à lista de espera.");
        }
        return requisicao;
    }

    /**
     * Busca um cliente pelo nome.
     *
     * @param nome O nome do cliente a ser buscado.
     * @return O cliente encontrado ou null se não encontrado.
     */
    private Cliente buscarCliente(String nome) {
        return listaDeClientes.stream()
                .filter(cliente -> cliente.getNome().equals(nome))
                .findFirst()
                .orElse(null);
    }

    /**
     * Cria um pedido para uma requisição existente.
     *
     * @param idRequisicao O ID da requisição para a qual o pedido será criado.
     * @param fechado true se o pedido é fechado, false se é aberto.
     * @return true se o pedido foi criado com sucesso, false caso contrário.
     */
    public boolean criarPedido(int idRequisicao, boolean fechado) {
        Optional<Requisicao> requisicaoOpt = localizarRequisicao(idRequisicao);
        if (requisicaoOpt.isPresent()) {
            Requisicao requisicao = requisicaoOpt.get();
            if (fechado) {
                PedidoFechado pedidoFechado = new PedidoFechado();
                requisicao.setPedido(pedidoFechado);
                pedidoFechadoRepository.save(pedidoFechado);
            } else {
                PedidoAberto pedidoAberto = new PedidoAberto();
                requisicao.setPedido(pedidoAberto);
                pedidoAbertoRepository.save(pedidoAberto);
            }
            requisicaoRepository.save(requisicao);
            return true;
        }
        return false;
    }

    /**
     * Adiciona um produto a um pedido existente em uma requisição.
     *
     * @param idRequisicao O ID da requisição onde o produto será adicionado.
     * @param idProduto O ID do produto a ser adicionado.
     * @param fechado true se o pedido é fechado, false se é aberto.
     * @return true se o produto foi adicionado com sucesso, false caso contrário.
     */
    public boolean adicionarProduto(int idRequisicao, Long idProduto, boolean fechado) {
        Optional<Requisicao> requisicaoOpt = localizarRequisicao(idRequisicao);
        if (requisicaoOpt.isPresent()) {
            Requisicao requisicao = requisicaoOpt.get();
            Produto produto = (fechado ? menuFechado : menuAberto).getProdutoById(idProduto);
            if (produto != null) {
                Pedido pedido = requisicao.getPedido();
                pedido.addProduto(produto);
                if (fechado) {
                    pedidoFechadoRepository.save((PedidoFechado) pedido);
                } else {
                    pedidoAbertoRepository.save((PedidoAberto) pedido);
                }
                requisicaoRepository.save(requisicao);
                return true;
            }
        }
        return false;
    }

    /**
     * Localiza uma requisição pelo ID.
     *
     * @param idRequisicao O ID da requisição a ser localizada.
     * @return Um Optional contendo a requisição encontrada, ou vazio se não encontrada.
     */
    public Optional<Requisicao> localizarRequisicao(int idRequisicao) {
        return requisicaoRepository.findById((long) idRequisicao);
    }

    /**
     * Exibe o histórico de requisições e pedidos.
     *
     * @return Uma string representando o histórico de requisições e pedidos.
     */
    public String exibirHistorico() {
        List<Requisicao> historicoDeRequisicao = requisicaoRepository.findAll();
        StringBuilder sb = new StringBuilder();
        sb.append("Histórico de Requisições:\n");
        if (historicoDeRequisicao.isEmpty()) {
            sb.append("Não há histórico de requisições ou pedidos.");
        } else {
            for (Requisicao requisicao : historicoDeRequisicao) {
                sb.append(requisicao.getRequisicaoInfo()).append("\n");
            }
        }
        return sb.toString();
    }
    
    /**
     * Exibe o menu especificado (aberto ou fechado).
     *
     * @param tipoMenu O tipo de menu a ser exibido ("aberto" ou "fechado").
     * @return Uma string representando o menu especificado.
     * @throws IllegalArgumentException Se o tipo de menu não for válido.
     */
    public String exibirMenu(String tipoMenu) {
        Menu menu;
        switch (tipoMenu) {
            case "fechado":
                menu = menuFechado;
                break;
            case "aberto":
                menu = menuAberto;
                break;
            default:
                throw new IllegalArgumentException("Tipo de menu inválido: " + tipoMenu);
        }
        return menu.exibirMenu();
    }

    /**
     * Verifica se uma requisição foi atendida.
     *
     * @param idRequisicao O ID da requisição a ser verificada.
     * @return true se a requisição foi atendida, false caso contrário.
     */
    public boolean isRequisicaoAtendida(int idRequisicao) {
        Optional<Requisicao> requisicaoOpt = requisicaoRepository.findById((long) idRequisicao);
        return requisicaoOpt.map(Requisicao::isFoiAtendida).orElse(false);
    }
}
