package com.ssj5.lpm.repository;

import com.ssj5.lpm.models.PedidoAberto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoAbertoRepository extends JpaRepository<PedidoAberto, Long> {
}
