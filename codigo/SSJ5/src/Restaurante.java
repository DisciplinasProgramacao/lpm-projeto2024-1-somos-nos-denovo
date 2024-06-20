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

    private Cliente buscarCliente(String nome) {
        Cliente ze = new Cliente(nome);
        int pos = listaDeClientes.indexOf(ze);
        return listaDeClientes.get(pos);
        
    }

    public Requisicao gerarRequisicao(int quantidade, String nome) {
        // if (nome == null || nome.isEmpty()) {
        //     throw new IllegalArgumentException("Nome do cliente não pode ser nulo ou vazio.");
        // }
         if (quantidade <= 0) {
             throw new IllegalArgumentException("Quantidade de pessoas deve ser maior que zero.");
         }

        Cliente cliente = buscarCliente(nome);
        
        Requisicao requisicao = new Requisicao(quantidade, cliente);

        if (alocarNaRequisicao(requisicao)) {s
            historicoDeRequisicao.add(requisicao);
        } else {
            entrarNaFilaDeEspera(requisicao);
            System.out.println("Mesas cheias! Cliente adicionado à lista de espera.");
        }

        return requisicao;
    }

    public boolean alocarNaRequisicao(Requisicao requisicao) {
        for (Mesa mesaDisponivel : listaDeMesas) {
            if (mesaDisponivel.podeAtender(requisicao.getQuantidade())) {
            
                requisicao.iniciarAtendimento(mesaDisponivel);
                return true;
            }
        }
        return false;
    }

    public boolean entrarNaFilaDeEspera(Requisicao requisicao) {
        filaDeEspera.offer(requisicao);
        return true;
    }

    public boolean adicionarProduto(int idRequisicao, int idProdutos, boolean fechado) {
        Requisicao requisicao = localizarRequisicao(idRequisicao);
        Produto p = menu.getProdutoById(idProdutos);
        
        return requisicao.addProduto(p);

        //return false;
    }

    // private boolean validarProdutosFechado(List<Produto> produtos) {
    //     long countComida = produtos.stream().filter(p -> EProdutoMenuFechado.isComida(p.getIdProduto())).count();
    //     long countBebida = produtos.stream().filter(p -> EProdutoMenuFechado.isBebida(p.getIdProduto())).count();
    //     return countComida == 1 && countBebida == 2;
    // }

    public Requisicao localizarRequisicao(int idRequisicao) {
        for (Requisicao r : historicoDeRequisicao) {
            if (r.getId() == idRequisicao) {
                return r;
            }
        }
        return null;
    }

    public boolean fecharConta(int idMesa) {
        Requisicao requisicaoFechar = localizarRequisicao(idMesa);
       
        if (requisicaoFechar != null) {
            requisicaoFechar.fecharRequisicao();
            verificarfila();
            // for (Requisicao r : filaDeEspera) {
            //     if (requisicaoFechar.getMesa().getCapacidade() >= r.getQuantidade()) {
            //         alocarNaRequisicao(r);
            //         filaDeEspera.remove(r);
            //         historicoDeRequisicao.add(r);
            //         break;
            //     }
            // }
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
                listaDeEspera.add(String.format("Cliente: %s, Quantidade: %d", requisicao.algumacoisa()
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
