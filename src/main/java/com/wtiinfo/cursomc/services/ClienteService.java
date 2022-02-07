package com.wtiinfo.cursomc.services;

import com.wtiinfo.cursomc.domain.Categoria;
import com.wtiinfo.cursomc.domain.Cliente;
import com.wtiinfo.cursomc.repositories.CategoriaRepository;
import com.wtiinfo.cursomc.repositories.ClienteRepository;
import com.wtiinfo.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepo;

    public Cliente buscar(Integer id) {
        return clienteRepo.findById(id).orElseThrow(() -> new ObjectNotFoundException(
                "A categoria procurada ID: " + id + " não foi encontrada. Tipo: " + Categoria.class.getName()));
    }

}
