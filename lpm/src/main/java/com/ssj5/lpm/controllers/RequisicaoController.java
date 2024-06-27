package com.ssj5.lpm.controllers;

import com.ssj5.lpm.models.Requisicao;
import com.ssj5.lpm.repository.RequisicaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controlador REST para gerenciar requisições.
 */
@RestController
@RequestMapping("/requisicoes")
public class RequisicaoController {

    @Autowired
    private RequisicaoRepository requisicaoRepository;

    @GetMapping
    public List<Requisicao> listarTodas() {
        return requisicaoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Requisicao> buscarPorId(@PathVariable Long id) {
        return requisicaoRepository.findById(id);
    }

    @PostMapping
    public Requisicao criar(@RequestBody Requisicao requisicao) {
        return requisicaoRepository.save(requisicao);
    }

    @PutMapping("/{id}")
    public Requisicao atualizar(@PathVariable Long id, @RequestBody Requisicao requisicaoAtualizada) {
        requisicaoAtualizada.setId(id);
        return requisicaoRepository.save(requisicaoAtualizada);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        requisicaoRepository.deleteById(id);
    }
}
