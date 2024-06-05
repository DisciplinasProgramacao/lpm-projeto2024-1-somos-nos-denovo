package com.codigo.ssj5.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.codigo.ssj5.models.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {}