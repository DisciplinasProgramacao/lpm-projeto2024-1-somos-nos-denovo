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

    private MenuAberto menuAberto;
    private MenuFechado menuFechado;
    private Queue<Requisicao> filaDeEspera;
    private List<Requisicao> historicoDeRequisicao;
    private List<Cliente> listaDeClientes;
    private Map<Mesa, Requisicao> mesas = new HashMap<>();

    @Autowired
    public Restaurante(MenuAberto menuAberto, MenuFechado menuFechado) {
        this.menuAberto = menuAberto;
        this.menuFechado = menuFechado;
        filaDeEspera = new LinkedList<>();
        historicoDeRequisicao = new ArrayList<>();
        listaDeClientes = new ArrayList<>();
    }

     private void initMesasIfNotExists() {
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

    // Método chamado no construtor para inicializar as mesas
    @PostConstruct
    public void initMesas() {
        initMesasIfNotExists();
    }


    public void adicionarCliente(String nome) {
        Cliente clienteExistente = buscarCliente(nome);
        if (clienteExistente == null) {
            clienteExistente = new Cliente(nome);
            listaDeClientes.add(clienteExistente);
            clienteRepository.save(clienteExistente);
        }
    }

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

    public boolean entrarNaFilaDeEspera(Requisicao requisicao) {
        return filaDeEspera.offer(requisicao);
    }

    public List<String> exibirListaDeEspera() {
        return filaDeEspera.isEmpty() ?
            Collections.singletonList("Não há clientes na lista de espera.") :
            filaDeEspera.stream()
                .map(r -> String.format("Cliente: %s, Quantidade: %d", r.getCliente().getNome(), r.getQuantidade()))
                .collect(Collectors.toList());
    }

    public void desocuparMesa(Mesa mesa) {
        if (!mesa.isDisponibilidade()) {
            mesa.desocupar();
            mesaRepository.save(mesa);
        }
    }

    public boolean fecharConta(int idMesa) {
        Optional<Requisicao> requisicaoOpt = requisicaoRepository.findAll().stream()
            .filter(r -> r.getMesa().getId() == idMesa && r.getHoraSaida() == null)
            .findFirst();
        
        if (requisicaoOpt.isPresent()) {
            Requisicao requisicao = requisicaoOpt.get();
            requisicao.fecharRequisicao();
            desocuparMesa(requisicao.getMesa());
            alocarDaListaDeEspera(requisicao.getMesa().getCapacidade());
            requisicaoRepository.save(requisicao);
            return true;
        } else {
            return false;
        }
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

    private Cliente buscarCliente(String nome) {
        return listaDeClientes.stream()
                .filter(cliente -> cliente.getNome().equals(nome))
                .findFirst()
                .orElse(null);
    }

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

    public boolean adicionarProduto(int idRequisicao, Long idProduto, boolean fechado) {
        Optional<Requisicao> requisicaoOpt = localizarRequisicao(idRequisicao);
        if (requisicaoOpt.isPresent()) {
            Requisicao requisicao = requisicaoOpt.get();
            Produto produto = (fechado ? menuFechado : menuAberto).getProdutoById( idProduto);
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

    public Optional<Requisicao> localizarRequisicao(int idRequisicao) {
        return requisicaoRepository.findById((long) idRequisicao);
    }

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
    
    public String exibirMenuAberto() {
        return menuAberto.exibirMenu();
    }

    public String exibirMenuFechado() {
        return menuFechado.exibirMenu();
    }

    public boolean isRequisicaoAtendida(int idRequisicao) {
        Optional<Requisicao> requisicaoOpt = requisicaoRepository.findById((long) idRequisicao);
        return requisicaoOpt.map(Requisicao::isFoiAtendida).orElse(false);
    }
}
