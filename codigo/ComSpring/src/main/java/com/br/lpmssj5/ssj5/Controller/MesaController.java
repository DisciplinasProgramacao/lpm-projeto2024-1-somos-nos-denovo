package com.br.lpmssj5.ssj5.Controller;

import com.br.lpmssj5.ssj5.Model.Mesa;
import com.br.lpmssj5.ssj5.Repository.MesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class MesaController {

    @Autowired
    private MesaRepository mesaRepository;

    public void createMesa(Mesa mesa) {
        mesaRepository.save(mesa);
    }

    public List<Mesa> getAllMesas() {
        return mesaRepository.findAll();
    }
}
