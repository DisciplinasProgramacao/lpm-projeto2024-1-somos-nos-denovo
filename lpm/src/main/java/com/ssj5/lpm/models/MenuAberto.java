package com.ssj5.lpm.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ssj5.lpm.repository.ProdutoRepository;

import jakarta.annotation.PostConstruct;
import java.util.ArrayList;

@Component
public class MenuAberto extends Menu {

    @Autowired
    private ProdutoRepository produtoRepository;

    @PostConstruct
    public void initProdutos() {
        menu = new ArrayList<>();
        Produto p1 = new Produto(7L, "FALAFEL_ASSADO", 20D);
        Produto p2 = new Produto(8L, "CACAROLA_LEGUMES", 22D);
        Produto p3 = new Produto(9L, "SALADA_PRIMAVERA", 25D);
        Produto p4 = new Produto(10L, "ESCONDIDINHO_INHAME", 18D);
        Produto p5 = new Produto(11L, "STROGONOFF_COGUMELOS", 35D);
        Produto p6 = new Produto(12L, "MOQUECA_PALMITO", 32D);
        Produto p7 = new Produto(13L, "AGUA", 3D);
        Produto p8 = new Produto(14L, "COPO_DE_SUCO", 7D);
        Produto p9 = new Produto(15L, "REFRIGERANTE_ORGANICO", 7D);
        Produto p10 = new Produto(16L, "CERVEJA_VEGANA", 9D);
        Produto p11 = new Produto(17L, "TACA_DE_VINHO_VEGANO", 18D);
    
        menu.add(produtoRepository.save(p1));
        menu.add(produtoRepository.save(p2));
        menu.add(produtoRepository.save(p3));
        menu.add(produtoRepository.save(p4));
        menu.add(produtoRepository.save(p5));
        menu.add(produtoRepository.save(p6));
        menu.add(produtoRepository.save(p7));
        menu.add(produtoRepository.save(p8));
        menu.add(produtoRepository.save(p9));
        menu.add(produtoRepository.save(p10));
        menu.add(produtoRepository.save(p11));
    }
    
}
