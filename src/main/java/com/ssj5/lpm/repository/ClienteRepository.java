package com.ssj5.lpm.repository;

import com.ssj5.lpm.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositório para a entidade Cliente.
 */
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
