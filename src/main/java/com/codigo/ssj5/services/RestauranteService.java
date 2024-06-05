package com.codigo.ssj5.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codigo.ssj5.models.Cliente;
import com.codigo.ssj5.models.Mesa;
import com.codigo.ssj5.models.Pedido;
import com.codigo.ssj5.models.Produto;
import com.codigo.ssj5.models.Requisicao;
import com.codigo.ssj5.models.Restaurante;
import com.codigo.ssj5.repositories.ClienteRepository;
import com.codigo.ssj5.repositories.MesaRepository;
import com.codigo.ssj5.repositories.PedidoRepository;
import com.codigo.ssj5.repositories.ProdutoRepository;
import com.codigo.ssj5.repositories.RequisicaoRepository;
import com.codigo.ssj5.repositories.RestauranteRepository;

// TODO: Mapear para puxar essas requisicoes de save corretamente, e avaliar se precisa de controller
@Service
public class RestauranteService {
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private MesaRepository mesaRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private RequisicaoRepository requisicaoRepository;

    @Autowired
    private RestauranteRepository restauranteRepository;

    public Cliente salvarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Mesa salvarMesa(Mesa mesa) {
        return mesaRepository.save(mesa);
    }

    public Pedido salvarPedido(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    public Produto salvarProduto(Produto produto) {
        return produtoRepository.save(produto);
    }

    public Requisicao salvarRequisicao(Requisicao requisicao) {
        return requisicaoRepository.save(requisicao);
    }

    public Restaurante salvarRestaurante(Restaurante restaurante) {
        return restauranteRepository.save(restaurante);
    }

    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    public List<Mesa> listarMesas() {
        return mesaRepository.findAll();
    }

    public List<Pedido> listarPedidos() {
        return pedidoRepository.findAll();
    }

    public List<Produto> listarProdutos() {
        return produtoRepository.findAll();
    }

    public List<Requisicao> listarRequisicoes() {
        return requisicaoRepository.findAll();
    }

    public List<Restaurante> listarRestaurantes() {
        return restauranteRepository.findAll();
    }
}
