package codigo.SSJ5.src;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class Restaurante {
    private List<Mesa> listaDeMesas;
    private Queue<Requisicao> filaDeEspera;
    private List<Requisicao> historicoDeRequisicao;
    private List<Cliente> listaDeClientes;
    private Menu menu;

    public Restaurante(Menu menu) {
        this.menu = menu;
        filaDeEspera = new LinkedList<>();
        listaDeClientes = new ArrayList<>();
        historicoDeRequisicao = new ArrayList<>();
        listaDeMesas = new ArrayList<>();
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

    public void fazerPedido(int idRequisicao, int idProduto) {
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
                Pedido pedido = requisicao.getPedido();
                pedido.addProduto(produto);
                System.out.println("Pedido criado com sucesso!");
            } else {
                System.out.println("Produto não encontrado.");
            }
        } else {
            System.out.println("Requisição não encontrada.");
        }
    }
    //Verificar se essa logica esta correta 
    public void ordenarMenuFechado(int idRequisicao, int comida, int bebida1, int bebida2) {
        Requisicao requisicao = null;
        for (Requisicao r : historicoDeRequisicao) {
            if (r.getId() == idRequisicao) {
                requisicao = r;
                break;
            }
        }
        if (requisicao != null) {
            Produto comida1 = (comida == 1) ? menu.getProdutoByName("Falafel Assado") : (comida == 2) ?menu.getProdutoByName("Caçarola de legumes") : null;
            Produto drink1 = (bebida1 == 1) ? menu.getProdutoByName("Copo de suco") : (bebida1 == 2) ? menu.getProdutoByName("Refrigerante orgânico") : (bebida1 == 3) ? menu.getProdutoByName("Cerveja vegana") : null;
            Produto drink2 = (bebida2 == 1) ? menu.getProdutoByName("Copo de suco") : (bebida2 == 2) ? menu.getProdutoByName("Refrigerante orgânico") : (bebida2 == 3) ? menu.getProdutoByName("Cerveja vegana") : null;

            if (comida1 != null && drink1 != null && drink2 != null) {
                Pedido pedido = requisicao.getPedido();
                pedido.addProduto(comida1);
                pedido.addProduto(drink1);
                pedido.addProduto(drink2);
                pedido.addMenuFixoPreco(requisicao.getQuantidade());
                System.out.println("Menu fechado criado com sucesso!");
            } else {
                System.out.println("Erro ao criar o menu fechado. Selecione os itens corretamente.");
            }
        } else {
            System.out.println("Requisição não encontrada.");
        }
    }

    public String exibirHistoricoDeRequisicoes() {
        StringBuilder sb = new StringBuilder();
        for (Requisicao requisicao : historicoDeRequisicao) {
            sb.append(requisicao.getRequisicaoInfo()).append("\n");
        }
        return sb.toString();
    }

    public String exibirPedidos() {
        StringBuilder sb = new StringBuilder();
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
