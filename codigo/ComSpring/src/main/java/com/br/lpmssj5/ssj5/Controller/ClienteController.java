package com.br.lpmssj5.ssj5.Controller;

import com.br.lpmssj5.ssj5.Model.Cliente;
import com.br.lpmssj5.ssj5.Repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    public void createCliente(Cliente cliente) {
        clienteRepository.save(cliente);
    }

    public Cliente getClienteByNome(String nome) {
        return clienteRepository.findByNome(nome).stream().findFirst().orElse(null);
    }

    public List<Cliente> getAllClientes() {
        return clienteRepository.findAll();
    }
}
