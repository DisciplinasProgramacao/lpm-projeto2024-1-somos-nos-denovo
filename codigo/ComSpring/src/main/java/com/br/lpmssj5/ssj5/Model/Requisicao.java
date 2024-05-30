package com.br.lpmssj5.ssj5.Model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table
public class Requisicao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int quantidadePessoas;
    private LocalDate data;
    private LocalTime horaEntrada;
    private LocalTime horaSaida;
    private boolean aberta;

    @ManyToOne
    private Cliente cliente;

    @ManyToOne
    private Mesa mesa;

    @ManyToOne
    private Restaurante restaurante;

    @OneToOne(cascade = CascadeType.ALL)
    private Pedido pedido;

    public Requisicao() {}

    public Requisicao(int quantidadePessoas, Cliente cliente, LocalDate data, LocalTime horaEntrada,
                      Restaurante restaurante) {
        this.quantidadePessoas = quantidadePessoas;
        this.cliente = cliente;
        this.data = data;
        this.horaEntrada = horaEntrada;
        this.aberta = true;
        this.restaurante = restaurante;
        this.pedido = new Pedido();
    }

    public void fecharRequisicao(List<Requisicao> historicoRequisicao) {
        this.horaSaida = LocalTime.now();
        this.aberta = false;
        if (mesa != null) {
            restaurante.fecharConta(this);
            restaurante.desocuparMesa(this, mesa);
        } else {
            System.out.println("Erro: A mesa associada à requisição não foi definida.");
        }
        historicoRequisicao.add(this);
    }

    public int getQuantidadePessoas() {
        return quantidadePessoas;
    }

    public void setQuantidadePessoas(int quantidadePessoas) {
        this.quantidadePessoas = quantidadePessoas;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public LocalTime getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(LocalTime horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public LocalTime getHoraSaida() {
        return horaSaida;
    }

    public void setHoraSaida(LocalTime horaSaida) {
        this.horaSaida = horaSaida;
    }

    public int getId() {
        return id;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

    public boolean isAberta() {
        return aberta;
    }

    public void setAberta(boolean aberta) {
        this.aberta = aberta;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public List<Produto> getProdutos() {
        return this.pedido.getProdutos();
    }

    public boolean adicionarPedido(List<Produto> produtos) {
        int tamanhoAntes = this.pedido.getProdutos().size();
        this.pedido.addProdutos(produtos);
        return this.pedido.getProdutos().size() > tamanhoAntes;
    }

    public static String exibirHistoricoDeRequisicoes(List<Requisicao> historicoRequisicoes) {
        if (historicoRequisicoes.isEmpty()) {
            return "Não há requisições no histórico.";
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Histórico de Requisições:\n");
            for (Requisicao requisicao : historicoRequisicoes) {
                sb.append("ID: ").append(requisicao.getId())
                        .append(", Cliente: ").append(requisicao.getCliente().getNome())
                        .append(", Quantidade: ").append(requisicao.getQuantidadePessoas())
                        .append(", Data: ").append(requisicao.getData())
                        .append(", Hora de Entrada: ").append(requisicao.getHoraEntrada())
                        .append(", Hora de Saída: ").append(requisicao.getHoraSaida())
                        .append("\n");
            }
            return sb.toString();
        }
    }
}