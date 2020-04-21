package com.juaka.osworks.api.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.juaka.osworks.domain.model.Cliente;

@RestController
public class ClienteController {

	@GetMapping("/clientes")
	public List<Cliente> listar() {
		Cliente c1 = new Cliente();
		c1.setNome("Jack");
		Cliente c2 = new Cliente();
		c2.setNome("ChanTa");
		
		return Arrays.asList(c1, c2);
	}
}
