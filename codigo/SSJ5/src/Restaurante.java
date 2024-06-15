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
        for (int i = 0;  i < 2; i++) {
            listaDeMesas.add(new Mesa(8, true));
        }
    }

    public void adicionarCliente(String nome) {
        Cliente novoCliente = new Cliente(nome);
        listaDeClientes.add(novoCliente);
    }

    private Optional<Cliente> buscarCliente(String nome) {
        return listaDeClientes.stream()
                              .filter(cliente -> cliente.getNome().equals(nome))
                              .findFirst();
    }

    public Requisicao gerarRequisicao(int quantidade, String nome) {
        if (nome == null || nome.isEmpty()) {
            throw new IllegalArgumentException("Nome do cliente não pode ser nulo ou vazio.");
        }
        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade de pessoas deve ser maior que zero.");
        }

        Cliente cliente = buscarCliente(nome).orElseGet(() -> {
            Cliente novoCliente = new Cliente(nome);
            listaDeClientes.add(novoCliente);
            return novoCliente;
        });

        Requisicao requisicao = new Requisicao(quantidade, cliente, LocalDate.now(), LocalTime.now());

        if (alocarNaRequisicao(requisicao)) {
            historicoDeRequisicao.add(requisicao);
        } else {
            entrarNaFilaDeEspera(requisicao);
            System.out.println("Mesas cheias! Cliente adicionado à lista de espera.");
        }

        return requisicao;
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

    public boolean adicionarProduto(int idRequisicao, int[] idsProdutos, boolean fechado) {
        Requisicao requisicao = localizarRequisicao(idRequisicao);
        if (requisicao != null) {
            List<Produto> produtos = new ArrayList<>();
            for (int idProduto : idsProdutos) {
                Produto produto = menu.getProdutoById(idProduto);
                if (produto == null) {
                    return false;
                }
                produtos.add(produto);
            }

            Pedido pedido = requisicao.getPedidoAtual();
            if (pedido == null) {
                if (fechado) {
                    pedido = new PedidoFechado();
                } else {
                    pedido = new PedidoAberto();
                }
                requisicao.addPedido(pedido);
            }

            if (fechado && pedido instanceof PedidoFechado) {
                if (!validarProdutosFechado(produtos)) {
                    System.out.println("Seleção inválida para pedido fechado.");
                    return false;
                }
            }

            for (Produto produto : produtos) {
                pedido.addProduto(produto);
            }

            return true;
        }
        return false;
    }

    private boolean validarProdutosFechado(List<Produto> produtos) {
        long countComida = produtos.stream().filter(p -> EProdutoMenuFechado.isComida(p.getIdProduto())).count();
        long countBebida = produtos.stream().filter(p -> EProdutoMenuFechado.isBebida(p.getIdProduto())).count();
        return countComida == 1 && countBebida == 2;
    }

    public Requisicao localizarRequisicao(int idRequisicao) {
        for (Requisicao r : historicoDeRequisicao) {
            if (r.getId() == idRequisicao) {
                return r;
            }
        }
        return null;
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

    public String exibirHistorico() {
        StringBuilder sb = new StringBuilder();
        sb.append("Histórico de Requisições:\n");
        for (Requisicao requisicao : historicoDeRequisicao) {
            sb.append(requisicao.getRequisicaoInfo()).append("\n");
        }
        return sb.toString();
    }
}
