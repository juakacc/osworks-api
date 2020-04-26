package com.juaka.osworks.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juaka.osworks.domain.exception.NegocioException;
import com.juaka.osworks.domain.model.Cliente;
import com.juaka.osworks.domain.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;
	
	public Cliente salvar(Cliente cliente) {
		Cliente clienteExistente = repository.findByEmail(cliente.getEmail());
		
		if (clienteExistente != null && !clienteExistente.equals(cliente)) {
			throw new NegocioException("Já existe um cliente com esse e-mail");
		}
		return repository.save(cliente);
	}
	
	public void remover(Long id) {
		repository.deleteById(id);
	}
	
}
