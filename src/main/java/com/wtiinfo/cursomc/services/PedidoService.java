package com.wtiinfo.cursomc.services;

import com.wtiinfo.cursomc.domain.Pedido;
import com.wtiinfo.cursomc.domain.Cliente;
import com.wtiinfo.cursomc.repositories.ClienteRepository;
import com.wtiinfo.cursomc.repositories.PedidoRepository;
import com.wtiinfo.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepo;

    public Pedido buscar(Integer id) {
        return pedidoRepo.findById(id).orElseThrow(() -> new ObjectNotFoundException(
                "A Pedido procurada ID: " + id + " não foi encontrada. Tipo: " + Pedido.class.getName()));
    }

}
