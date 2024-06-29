package com.ssj5.lpm.controllers;

import com.ssj5.lpm.models.PedidoAberto;
import com.ssj5.lpm.models.PedidoFechado;
import com.ssj5.lpm.models.Produto;
import com.ssj5.lpm.repository.PedidoAbertoRepository;
import com.ssj5.lpm.repository.PedidoFechadoRepository;
import com.ssj5.lpm.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controlador REST para gerenciar pedidos.
 */
@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoAbertoRepository pedidoAbertoRepository;

    @Autowired
    private PedidoFechadoRepository pedidoFechadoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping("/abertos")
    public List<PedidoAberto> listarTodosAbertos() {
        return pedidoAbertoRepository.findAll();
    }

    @GetMapping("/fechados")
    public List<PedidoFechado> listarTodosFechados() {
        return pedidoFechadoRepository.findAll();
    }

    @GetMapping("/abertos/{id}")
    public Optional<PedidoAberto> buscarAbertoPorId(@PathVariable Long id) {
        return pedidoAbertoRepository.findById(id);
    }

    @GetMapping("/fechados/{id}")
    public Optional<PedidoFechado> buscarFechadoPorId(@PathVariable Long id) {
        return pedidoFechadoRepository.findById(id);
    }

    @PostMapping("/abertos")
    public PedidoAberto criarAberto(@RequestBody PedidoAberto pedido) {
        return pedidoAbertoRepository.save(pedido);
    }

    @PostMapping("/fechados")
    public PedidoFechado criarFechado(@RequestBody PedidoFechado pedido) {
        return pedidoFechadoRepository.save(pedido);
    }

    @PutMapping("/abertos/{id}")
    public PedidoAberto atualizarAberto(@PathVariable Long id, @RequestBody PedidoAberto pedidoAtualizado) {
        pedidoAtualizado.setId(id);
        return pedidoAbertoRepository.save(pedidoAtualizado);
    }

    @PutMapping("/fechados/{id}")
    public PedidoFechado atualizarFechado(@PathVariable Long id, @RequestBody PedidoFechado pedidoAtualizado) {
        pedidoAtualizado.setId(id);
        return pedidoFechadoRepository.save(pedidoAtualizado);
    }

    @PostMapping("/abertos/{id}/produtos")
    public PedidoAberto adicionarProdutoAberto(@PathVariable Long id, @RequestBody Produto produto) {
        PedidoAberto pedido = pedidoAbertoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado"));
        Produto prod = produtoRepository.findById(produto.getId())
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));
        pedido.addProduto(prod);
        return pedidoAbertoRepository.save(pedido);
    }

    @PostMapping("/fechados/{id}/produtos")
    public PedidoFechado adicionarProdutoFechado(@PathVariable Long id, @RequestBody Produto produto) {
        PedidoFechado pedido = pedidoFechadoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado"));
        Produto prod = produtoRepository.findById(produto.getId())
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));
        pedido.addProduto(prod);
        return pedidoFechadoRepository.save(pedido);
    }

    @DeleteMapping("/abertos/{id}")
    public void deletarAberto(@PathVariable Long id) {
        pedidoAbertoRepository.deleteById(id);
    }

    @DeleteMapping("/fechados/{id}")
    public void deletarFechado(@PathVariable Long id) {
        pedidoFechadoRepository.deleteById(id);
    }
}
