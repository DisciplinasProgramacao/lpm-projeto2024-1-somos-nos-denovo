package com.ssj5.lpm.controllers;

import com.ssj5.lpm.models.Produto;
import com.ssj5.lpm.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controlador REST para gerenciar produtos.
 */
@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping
    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Produto> buscarPorId(@PathVariable Long id) {
        return produtoRepository.findById(id);
    }

    @PostMapping
    public Produto criar(@RequestBody Produto produto) {
        return produtoRepository.save(produto);
    }

    @PutMapping("/{id}")
    public Produto atualizar(@PathVariable Long id, @RequestBody Produto produtoAtualizado) {
        produtoAtualizado.setId(id);
        return produtoRepository.save(produtoAtualizado);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        produtoRepository.deleteById(id);
    }
}
