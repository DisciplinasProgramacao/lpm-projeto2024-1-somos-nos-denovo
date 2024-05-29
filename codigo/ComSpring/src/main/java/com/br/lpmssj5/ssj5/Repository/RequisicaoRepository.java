package com.br.lpmssj5.ssj5.Repository;

import com.br.lpmssj5.ssj5.Model.Requisicao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequisicaoRepository extends JpaRepository<Requisicao, Integer> {
    Requisicao findByMesaIdAndAberta(int mesaId, boolean aberta);
}
