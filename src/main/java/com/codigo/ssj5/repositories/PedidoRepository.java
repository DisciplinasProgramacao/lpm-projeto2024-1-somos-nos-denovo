package com.codigo.ssj5.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.codigo.ssj5.models.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {}