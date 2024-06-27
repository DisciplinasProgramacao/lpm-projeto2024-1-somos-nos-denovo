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

    public Requisicao() {
    }

    public Requisicao(Cliente cliente, LocalDateTime dataHora, int quantidade) {
        this.quantidade = quantidade;
        this.cliente = cliente;
        this.dataHora = LocalDateTime.now();
        this.horaEntrada = LocalTime.now();
        this.pedidos = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public boolean isFoiAtendida() {
        return foiAtendida;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

    public void fecharRequisicao() {
        this.foiAtendida = true;
        if (mesa != null) {
            mesa.setDisponibilidade(true);
        }
    }

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

        if (pedidos.isEmpty()) {
            sb.append("Não há pedidos no momento.\n");
        } else {
            sb.append("Pedidos:\n");
            for (Pedido pedido : pedidos) {
                sb.append(pedido.formatPedido()).append("\n");
            }
        }

        return sb.toString();
    }

    public LocalDateTime getHoraSaida() {
        return foiAtendida ? dataHora.plusHours(2) : null;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

}