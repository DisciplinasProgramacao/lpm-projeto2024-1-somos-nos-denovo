package com.ssj5.lpm.repository;

import com.ssj5.lpm.models.Requisicao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositório para a entidade Requisicao.
 */
@Repository
public interface RequisicaoRepository extends JpaRepository<Requisicao, Long> {
}
