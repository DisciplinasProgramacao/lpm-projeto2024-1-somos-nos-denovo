package com.br.lpmssj5.ssj5.Repository;

import com.br.lpmssj5.ssj5.Model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
}
