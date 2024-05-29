package com.br.lpmssj5.ssj5.Controller;

import com.br.lpmssj5.ssj5.Model.Requisicao;
import com.br.lpmssj5.ssj5.Repository.RequisicaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Controller
public class RequisicaoController {

    @Autowired
    private RequisicaoRepository requisicaoRepository;

    @Transactional
    public void createRequisicao(Requisicao requisicao) {
        requisicaoRepository.save(requisicao);
    }

    @Transactional(readOnly = true)
    public Requisicao findRequisicaoByMesaIdAndAberta(int mesaId, boolean aberta) {
        return requisicaoRepository.findByMesaIdAndAberta(mesaId, aberta);
    }

    @Transactional(readOnly = true)
    public List<Requisicao> getAllRequisicoes() {
        return requisicaoRepository.findAll();
    }
}
