package codigo.SSJ5.src;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class Restaurante {
    private List<Mesa> listaDeMesas;
    public Queue<Requisicao> filaDeEspera;
    public List<Requisicao> historicoDeRequisicao;
    public List<Cliente> listaDeClientes;

    public Restaurante() {
        filaDeEspera = new LinkedList<>();
        listaDeClientes = new ArrayList<>();
        historicoDeRequisicao = new ArrayList<>();
        listaDeMesas = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            listaDeMesas.add(new Mesa(4 , true));
        }
        for (int i = 0; i < 4; i++) {
            listaDeMesas.add(new Mesa(6 , true));
        }
        for (int i = 0; i < 2; i++) {
            listaDeMesas.add(new Mesa(8, true));
        }
    }

    public List<Mesa> getListaDeMesas() {
        return listaDeMesas;
    }

    public Queue<Requisicao> getFilaDeEspera() {
        return filaDeEspera;
    }

    public List<Requisicao> getHistoricoDeRequisicao() {
        return historicoDeRequisicao;
    }

    public List<Cliente> getListaDeClientes() {
        return listaDeClientes;
    }

    public boolean alocarNaRequisicao(Requisicao requisicao) {
        for (Mesa mesaDisponivel : listaDeMesas) {
            if (mesaDisponivel.getCapacidade() >= requisicao.getQuantidade() && mesaDisponivel.isDisponibilidade()) {
                mesaDisponivel.setDisponibilidade(false);
                requisicao.setMesa(mesaDisponivel);
                return true;
            }
        }
        return false;
    }

    public boolean entrarNaFilaDeEspera(Requisicao requisicao) {
        filaDeEspera.offer(requisicao);
        return true;
    }

    public List<String> exibirListaDeEspera() {
        List<String> listaDeEspera = new ArrayList<>();
        if (filaDeEspera.isEmpty()) {
            listaDeEspera.add("Não há clientes na lista de espera.");
        } else {
            for (Requisicao requisicao : filaDeEspera) {
                listaDeEspera.add(String.format("Cliente: %s, Quantidade: %d", requisicao.getCliente().getNome(), requisicao.getQuantidade()));
            }
        }
        return listaDeEspera;
    }

    public boolean desocuparMesa(Requisicao requisicao, Mesa mesa){
        if (!mesa.isDisponibilidade()) {
            mesa.setDisponibilidade(true);
        }
        return true;
    }

    public boolean fecharConta(Requisicao requisicao) {
        Mesa mesa = requisicao.getMesa();
        mesa.setDisponibilidade(true);
        filaDeEspera.remove(requisicao);

        for (Requisicao r : filaDeEspera) {
            if (mesa.getCapacidade() >= r.getQuantidade()) {
                r.setMesa(mesa);
                mesa.setDisponibilidade(false);
                filaDeEspera.remove(r);
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

    public Pedido fazerPedido(List<Produto> produtos, Requisicao requisicao){
        Pedido pedido = requisicao.getPedido();
        if (pedido == null) {
            pedido = new Pedido(requisicao);
            pedido.setProdutos(produtos);
            requisicao.setPedido(pedido);
        } else {
            pedido.addProdutos(produtos);
        }
        return pedido;
    }

    public String exibirHistoricoDeRequisicoes() {
        StringBuilder sb = new StringBuilder();
        for (Requisicao requisicao : historicoDeRequisicao) {
            sb.append(requisicao.getRequisicaoInfo()).append("\n");
        }
        return sb.toString();
    }

    public List<Pedido> getPedidos() {
        List<Pedido> pedidos = new ArrayList<>();
        for (Requisicao requisicao : historicoDeRequisicao) {
            if (requisicao.getPedido() != null) {
                pedidos.add(requisicao.getPedido());
            }
        }
        return pedidos;
    }
}
