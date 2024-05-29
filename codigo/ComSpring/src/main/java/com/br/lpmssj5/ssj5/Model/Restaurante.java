package com.br.lpmssj5.ssj5.Model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Restaurante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Mesa> listaDeMesas = new ArrayList<>();

    @OneToMany(mappedBy = "restaurante", cascade = CascadeType.ALL)
    private List<Requisicao> filaDeEspera = new ArrayList<>();

    @OneToMany(mappedBy = "restaurante", cascade = CascadeType.ALL)
    private List<Requisicao> historicoDeRequisicao = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    private List<Cliente> listaDeClientes = new ArrayList<>();


    public Restaurante() {}

    public Restaurante(List<Mesa> mesas) {
        this.listaDeMesas.addAll(mesas);
    }

    public List<Mesa> getListaDeMesas() {
        return listaDeMesas;
    }

    public List<Requisicao> getFilaDeEspera() {
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
            if (mesaDisponivel.getCapacidade() >= requisicao.getQuantidadePessoas() && mesaDisponivel.isDisponibilidade()) {
                mesaDisponivel.setDisponibilidade(false);
                requisicao.setMesa(mesaDisponivel);
                System.out.println("Requisição alocada na mesa ID: " + mesaDisponivel.getId());
                return true;
            }
        }
        System.out.println("Nenhuma mesa disponível para alocar a requisição.");
        return false;
    }

    public boolean entrarNaFilaDeEspera(Requisicao requisicao) {
        filaDeEspera.add(requisicao);
        return true;
    }

    public void exibirListaDeEspera() {
        if (filaDeEspera.isEmpty()) {
            System.out.println("Não há clientes na lista de espera.");
        } else {
            System.out.println("Lista de espera:");
            for (Requisicao requisicao : filaDeEspera) {
                System.out.println("Cliente: " + requisicao.getCliente().getNome() + ", Quantidade: " + requisicao.getQuantidadePessoas());
            }
        }
    }

    public boolean desocuparMesa(Requisicao requisicao, Mesa mesa){
        if (!mesa.isDisponibilidade()) {
            mesa.setDisponibilidade(true);
        }
        return true;
    }

    public boolean fecharConta(Requisicao requisicao) {
        System.out.println("Sua conta da requisição: "+requisicao.getId()+" foi fechada com sucesso!");
        Mesa mesa = requisicao.getMesa();
        mesa.setDisponibilidade(true);
        filaDeEspera.remove(requisicao);
        for (Requisicao r : filaDeEspera) {
            if (mesa.getCapacidade() >= r.getQuantidadePessoas()) {
                r.setMesa(mesa);
                mesa.setDisponibilidade(false);
                filaDeEspera.remove(r);
                System.out.println("Requisição alocada na mesa ID: " + mesa.getId() + " após fechar a conta.");
                break;
            }
        }
        return true;
    }

    public void gerarRequisicao(Requisicao requisicao) {
        if (alocarNaRequisicao(requisicao)) {
            historicoDeRequisicao.add(requisicao);
        } else {
            entrarNaFilaDeEspera(requisicao);
        }
    }

    public Pedido fazerPedido(List<Produto> produtos, Requisicao requisicao){
        if(requisicao.getPedido().calcularValorFinal() == 0){
            Pedido pedido = new Pedido();
            pedido.setProdutos(produtos);
            requisicao.setPedido(pedido);
            return pedido;
        } else {
            requisicao.getPedido().addProdutos(produtos);
            return requisicao.getPedido();
        }
    }

    public void addCliente(Cliente cliente) {
        listaDeClientes.add(cliente);
    }
}
