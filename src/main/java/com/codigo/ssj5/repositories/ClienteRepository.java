package com.codigo.ssj5.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.codigo.ssj5.models.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {}