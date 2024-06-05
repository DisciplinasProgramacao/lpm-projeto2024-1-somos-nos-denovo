package com.codigo.ssj5.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.codigo.ssj5.models.Requisicao;

public interface RequisicaoRepository extends JpaRepository<Requisicao, Integer> {}