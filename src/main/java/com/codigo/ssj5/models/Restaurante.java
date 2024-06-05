package com.codigo.ssj5.models;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "restaurante")
public class Restaurante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToMany(cascade = CascadeType.ALL)
    private List<Mesa> listaDeMesas = new ArrayList<>();
    
    private Queue<Requisicao> filaDeEspera = new LinkedList<>();
    
    @OneToMany(cascade = CascadeType.ALL)
    private List<Requisicao> historicoDeRequisicao = new ArrayList<>();
    
    @OneToMany(cascade = CascadeType.ALL)
    private List<Cliente> listaDeClientes = new ArrayList<>();
    
    private Menu menu;

    public Restaurante() {}

    public Restaurante(Menu menu) {
        this.menu = menu;
        for (int i = 0; i < 4; i++) {
            listaDeMesas.add(new Mesa(4, true));
        }
        for (int i = 0; i < 4; i++) {
            listaDeMesas.add(new Mesa(6, true));
        }
        for (int i = 0; i < 2; i++) {
            listaDeMesas.add(new Mesa(8, true));
        }
    }

    public void adicionarCliente(String nome) {
        Cliente novoCliente = new Cliente(nome);
        listaDeClientes.add(novoCliente);
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

    public void desocuparMesa(Mesa mesa) {
        if (!mesa.isDisponibilidade()) {
            mesa.setDisponibilidade(true);
        }
    }

    public boolean fecharConta(int idMesa) {
        Requisicao requisicaoFechar = null;
        for (Requisicao r : historicoDeRequisicao) {
            if (r.getMesa().getId() == idMesa && r.getHoraSaida() == null) {
                requisicaoFechar = r;
                break;
            }
        }
        if (requisicaoFechar != null) {
            requisicaoFechar.fecharRequisicao();
            for (Requisicao r : filaDeEspera) {
                if (requisicaoFechar.getMesa().getCapacidade() >= r.getQuantidade()) {
                    alocarNaRequisicao(r);
                    filaDeEspera.remove(r);
                    historicoDeRequisicao.add(r);
                    break;
                }
            }
            return true;
        }
        return false;
    }

    public Requisicao gerarRequisicao(int quantidade, String nome) {
        Cliente clienteExistente = null;
        for (Cliente cliente : listaDeClientes) {
            if (cliente.getNome().equals(nome)) {
                clienteExistente = cliente;
                break;
            }
        }
        Requisicao requisicao;
        if (clienteExistente != null) {
            requisicao = new Requisicao(quantidade, clienteExistente, LocalDate.now(), LocalTime.now());
        } else {
            Cliente novoCliente = new Cliente(nome);
            listaDeClientes.add(novoCliente);
            requisicao = new Requisicao(quantidade, novoCliente, LocalDate.now(), LocalTime.now());
        }
        if (alocarNaRequisicao(requisicao)) {
            historicoDeRequisicao.add(requisicao);
        } else {
            entrarNaFilaDeEspera(requisicao);
            System.out.println("Mesas cheias! Cliente adicionado à lista de espera.");
        }
        return requisicao;
    }

    public boolean fazerPedido(int idRequisicao, int idProduto) {
        Requisicao requisicao = null;
        for (Requisicao r : historicoDeRequisicao) {
            if (r.getId() == idRequisicao) {
                requisicao = r;
                break;
            }
        }
        if (requisicao != null) {
            Produto produto = menu.getProdutoById(idProduto);
            if (produto != null) {
               requisicao.adicionarItem(produto);
                return true;
            }
        }
        return false;
    }

    public boolean menuFechado(int idRequisicao, int comida, int bebida1, int bebida2) {
        Requisicao requisicao = null;
        for (Requisicao r : historicoDeRequisicao) {
            if (r.getId() == idRequisicao) {
                requisicao = r;
                break;
            }
        }
        if (requisicao != null) {
            Produto comida1 = getComidaByOption(comida);
            Produto drink1 = getDrinkByOption(bebida1);
            Produto drink2 = getDrinkByOption(bebida2);

            if (comida1 != null && drink1 != null && drink2 != null) {
                PedidoFechado pedidoFechado = new PedidoFechado(requisicao);
                pedidoFechado.addProduto(comida1);
                pedidoFechado.addProduto(drink1);
                pedidoFechado.addProduto(drink2);
                pedidoFechado.addMenuFixoPreco(requisicao.getQuantidade());
                requisicao.setPedido(pedidoFechado);
                
                return true;
            } else {
                return false;
            }
        } else {
           return false;
        }
    }

    private Produto getComidaByOption(int option) {
        switch (option) {
            case 1:
                return menu.getProdutoByName("Falafel Assado");
            case 2:
                return menu.getProdutoByName("Caçarola de legumes");
            default:
                return null;
        }
    }

    private Produto getDrinkByOption(int option) {
        switch (option) {
            case 1:
                return menu.getProdutoByName("Copo de suco");
            case 2:
                return menu.getProdutoByName("Refrigerante orgânico");
            case 3:
                return menu.getProdutoByName("Cerveja vegana");
            default:
                return null;
        }
    }

    public String exibirHistorico() {
        StringBuilder sb = new StringBuilder();
        sb.append("Histórico de Requisições:\n");
        for (Requisicao requisicao : historicoDeRequisicao) {
            sb.append(requisicao.getRequisicaoInfo()).append("\n");
        }
        sb.append("Pedidos:\n");
        List<Pedido> pedidos = getPedidos();
        if (pedidos.isEmpty()) {
            sb.append("Não há pedidos no momento.");
        } else {
            for (Pedido pedido : pedidos) {
                sb.append(pedido.formatPedido()).append("\n");
            }
        }
        return sb.toString();
    }

    private List<Pedido> getPedidos() {
        List<Pedido> pedidos = new ArrayList<>();
        for (Requisicao requisicao : historicoDeRequisicao) {
            if (requisicao.getPedido() != null) {
                pedidos.add(requisicao.getPedido());
            }
        }
        return pedidos;
    }
}
