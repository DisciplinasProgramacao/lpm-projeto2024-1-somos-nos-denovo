package com.ssj5.lpm.models;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

/**
 * Representa uma requisição feita por um cliente em um restaurante.
 */
@Entity
public class Requisicao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Cliente cliente;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Pedido> pedidos;

    private LocalDateTime dataHora;
    private int quantidade;
    private boolean foiAtendida;
    private LocalTime horaEntrada;
    private LocalTime horaSaida;
    @ManyToOne
    private Mesa mesa;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Pedido pedido;

    /**
     * Construtor padrão sem parâmetros.
     */
    public Requisicao() {
    }

    /**
     * Construtor para inicializar uma requisição com cliente, data e quantidade.
     *
     * @param cliente    O cliente associado à requisição.
     * @param dataHora   A data e hora em que a requisição foi feita.
     * @param quantidade A quantidade de pessoas na requisição.
     */
    public Requisicao(Cliente cliente, LocalDateTime dataHora, int quantidade) {
        this.quantidade = quantidade;
        this.cliente = cliente;
        this.dataHora = LocalDateTime.now();
        this.horaEntrada = LocalTime.now();
        this.pedidos = new ArrayList<>();
    }

    /**
     * Obtém o ID da requisição.
     *
     * @return O ID da requisição.
     */
    public Long getId() {
        return id;
    }

    /**
     * Define o ID da requisição.
     *
     * @param id O novo ID da requisição.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtém o cliente associado à requisição.
     *
     * @return O cliente associado à requisição.
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * Obtém a data e hora em que a requisição foi feita.
     *
     * @return A data e hora da requisição.
     */
    public LocalDateTime getDataHora() {
        return dataHora;
    }

    /**
     * Obtém a quantidade de pessoas na requisição.
     *
     * @return A quantidade de pessoas na requisição.
     */
    public int getQuantidade() {
        return quantidade;
    }

    /**
     * Verifica se a requisição foi atendida.
     *
     * @return true se a requisição foi atendida, false caso contrário.
     */
    public boolean isFoiAtendida() {
        return foiAtendida;
    }

    /**
     * Obtém a mesa associada à requisição.
     *
     * @return A mesa associada à requisição.
     */
    public Mesa getMesa() {
        return mesa;
    }

    /**
     * Obtém o pedido associado à requisição.
     *
     * @return O pedido associado à requisição.
     */
    public Pedido getPedido() {
        return pedido;
    }

    /**
     * Define o pedido associado à requisição.
     *
     * @param pedido O novo pedido associado à requisição.
     */
    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    /**
     * Define a mesa associada à requisição.
     *
     * @param mesa A nova mesa associada à requisição.
     */
    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

    /**
     * Marca a requisição como atendida, liberando a mesa, se houver.
     */
    public void fecharRequisicao() {
        this.foiAtendida = true;
        if (mesa != null) {
            this.horaSaida = LocalTime.now();
            mesa.setDisponibilidade(true);
        }
    }

    /**
     * Obtém informações formatadas da requisição.
     *
     * @return Uma string com informações formatadas da requisição.
     */
    public String getRequisicaoInfo() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        String horaEntradaFormatada = "N/A";
        String horaSaidaFormatada = "N/A";
        String mesaId = "N/A";

        try {
            if (horaEntrada != null) {
                horaEntradaFormatada = horaEntrada.format(formatter);
            }
            horaSaidaFormatada = (horaSaida != null ? horaSaida.format(formatter) : "N/A");
            mesaId = (mesa != null) ? String.valueOf(mesa.getId()) : "N/A";
        } catch (DateTimeParseException e) {
            // Captura exceção específica para falhas na formatação da data/hora
            System.err.println("Erro ao formatar data/hora: " + e.getMessage());
            horaEntradaFormatada = "Erro ao formatar data/hora";
        } catch (NullPointerException e) {
            // Captura exceção específica para quando cliente, mesa, ou outra variável for nula
            System.err.println("Erro de referência nula: " + e.getMessage());
            mesaId = "Erro de referência nula";
        }

        StringBuilder sb = new StringBuilder();
        sb.append(String.format(
                "ID: %02d\nCliente: %s\nQuantidade: %02d\nData: %s\nHora de Entrada: %s\nHora de Saída: %s\nMesa ID: %s\n",
                id, (cliente != null ? cliente.getNome() : "N/A"), quantidade, dataHora, horaEntradaFormatada,
                horaSaidaFormatada, mesaId));

        return sb.toString();
    }

    /**
     * Obtém a hora de saída estimada da requisição.
     *
     * @return A hora de saída estimada da requisição.
     */
    public LocalDateTime getHoraSaida() {
        return foiAtendida ? dataHora.plusHours(2) : null;
    }

    /**
     * Obtém a lista de pedidos associados à requisição.
     *
     * @return A lista de pedidos associados à requisição.
     */
    public List<Pedido> getPedidos() {
        return pedidos;
    }

    /**
     * Calcula o valor total da conta para a requisição.
     *
     * @return O valor total da conta para a requisição.
     */
    public double valorConta() {
        return pedido.calcularValorTotal();
    }

    /**
     * Obtém informações detalhadas da conta para a requisição.
     *
     * @return Uma string com informações detalhadas da conta para a requisição.
     */
    public String infoConta() {
        double valorTotal = valorConta();
        double valorPorQuantidade = valorTotal / quantidade;
        return "Valor Total: " + valorTotal + ", Valor por pessoa: " + valorPorQuantidade;
    }
}
