package com.ssj5.lpm.controllers;

import com.ssj5.lpm.models.Mesa;
import com.ssj5.lpm.repository.MesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controlador REST para gerenciar mesas.
 */
@RestController
@RequestMapping("/mesas")
public class MesaController {

    @Autowired
    private MesaRepository mesaRepository;

    @GetMapping
    public List<Mesa> listarTodas() {
        return mesaRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Mesa> buscarPorId(@PathVariable Long id) {
        return mesaRepository.findById(id);
    }

    @PostMapping
    public Mesa criar(@RequestBody Mesa mesa) {
        return mesaRepository.save(mesa);
    }

    @PutMapping("/{id}")
    public Mesa atualizar(@PathVariable Long id, @RequestBody Mesa mesaAtualizada) {
        mesaAtualizada.setId(id);
        return mesaRepository.save(mesaAtualizada);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        mesaRepository.deleteById(id);
    }
}
