package com.wtiinfo.cursomc.resources;

import com.wtiinfo.cursomc.domain.Categoria;
import com.wtiinfo.cursomc.domain.Cliente;
import com.wtiinfo.cursomc.services.CategoriaService;
import com.wtiinfo.cursomc.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clientes")
public class ClienteResource {

	@Autowired
	private ClienteService clienteServ;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		Cliente c1 = clienteServ.buscar(id);
		return ResponseEntity.ok().body(c1);
	}
}
