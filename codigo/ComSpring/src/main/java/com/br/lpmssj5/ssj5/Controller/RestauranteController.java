package com.br.lpmssj5.ssj5.Controller;

import com.br.lpmssj5.ssj5.Model.Mesa;
import com.br.lpmssj5.ssj5.Model.Restaurante;
import com.br.lpmssj5.ssj5.Repository.MesaRepository;
import com.br.lpmssj5.ssj5.Repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Controller
public class RestauranteController {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Autowired
    private MesaRepository mesaRepository;

    @Transactional
    public void createRestaurante(Restaurante restaurante) {
        restaurante.getListaDeMesas().forEach(mesaRepository::save);
        restauranteRepository.save(restaurante);
    }

    @Transactional(readOnly = true)
    public Restaurante getRestaurante() {
        return restauranteRepository.findById(1).orElse(null);
    }
}
