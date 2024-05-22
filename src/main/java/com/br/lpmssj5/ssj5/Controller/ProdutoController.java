package com.br.lpmssj5.ssj5.Controller;

import com.br.lpmssj5.ssj5.Model.Produto;
import com.br.lpmssj5.ssj5.Repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    public void createProduto(Produto produto) {
        produtoRepository.save(produto);
    }

    public Produto getProdutoById(int id) {
        return produtoRepository.findById(id).orElse(null);
    }

    public List<Produto> getAllProdutos() {
        return produtoRepository.findAll();
    }
}
