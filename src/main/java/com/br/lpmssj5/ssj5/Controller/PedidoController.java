package com.br.lpmssj5.ssj5.Controller;

import com.br.lpmssj5.ssj5.Model.Pedido;
import com.br.lpmssj5.ssj5.Repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;

    public void createPedido(Pedido pedido) {
        pedidoRepository.save(pedido);
    }

    public List<Pedido> getAllPedidos() {
        return pedidoRepository.findAll();
    }
}
