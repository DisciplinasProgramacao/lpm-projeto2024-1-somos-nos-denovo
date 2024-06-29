package com.ssj5.lpm.repository;

import com.ssj5.lpm.models.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositório para a entidade Pedido.
 */
@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}
