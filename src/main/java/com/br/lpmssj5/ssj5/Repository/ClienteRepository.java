package com.br.lpmssj5.ssj5.Repository;

import com.br.lpmssj5.ssj5.Model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    List<Cliente> findByNome(String nome);
}
