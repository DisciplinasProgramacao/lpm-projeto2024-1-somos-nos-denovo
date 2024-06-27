package com.ssj5.lpm.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ssj5.lpm.repository.ProdutoRepository;

import jakarta.annotation.PostConstruct;
import java.util.ArrayList;

@Component
public class MenuFechado extends Menu {

    @Autowired
    private ProdutoRepository produtoRepository;

    @PostConstruct
    public void initProdutos() {
        menu = new ArrayList<>();
        Produto p1 = new Produto(1L, "FALAFEL_ASSADO", 20D);
        Produto p2 = new Produto(2L, "CACAROLA_LEGUMES", 22D);
        Produto p3 = new Produto(3L,"SALADA_PRIMAVERA", 25D);
        Produto p4 = new Produto(4L, "COPO_DE_SUCO", 7D);
        Produto p5 = new Produto(5L, "REFRIGERANTE_ORGANICO", 7D);
        Produto p6 = new Produto(6L, "TACA_DE_VINHO_VEGANO", 18D);

        menu.add(produtoRepository.save(p1));
        menu.add(produtoRepository.save(p2));
        menu.add(produtoRepository.save(p3));
        menu.add(produtoRepository.save(p4));
        menu.add(produtoRepository.save(p5));
        menu.add(produtoRepository.save(p6));
    }
}
