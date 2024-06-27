package com.ssj5.lpm.repository;

import com.ssj5.lpm.models.PedidoFechado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoFechadoRepository extends JpaRepository<PedidoFechado, Long> {
}
