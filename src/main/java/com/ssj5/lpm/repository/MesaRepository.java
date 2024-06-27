package com.ssj5.lpm.repository;

import com.ssj5.lpm.models.Mesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositório para a entidade Mesa.
 */
@Repository
public interface MesaRepository extends JpaRepository<Mesa, Long> {
}
