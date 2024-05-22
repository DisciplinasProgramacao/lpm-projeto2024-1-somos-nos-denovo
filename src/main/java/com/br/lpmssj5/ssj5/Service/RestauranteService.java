package com.br.lpmssj5.ssj5.Service;

import com.br.lpmssj5.ssj5.Model.Restaurante;
import com.br.lpmssj5.ssj5.Repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RestauranteService {

    @Autowired
    private RestauranteRepository restauranteRepository;

    @Transactional(readOnly = true)
    public Restaurante getRestaurante() {
        return restauranteRepository.findById(1).orElse(null);
    }
}
