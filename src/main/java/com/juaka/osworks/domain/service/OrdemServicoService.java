package com.juaka.osworks.domain.service;

import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.juaka.osworks.api.model.Comentario;
import com.juaka.osworks.domain.exception.EntidadeNaoEncontradaException;
import com.juaka.osworks.domain.exception.NegocioException;
import com.juaka.osworks.domain.model.Cliente;
import com.juaka.osworks.domain.model.OrdemServico;
import com.juaka.osworks.domain.model.StatusOrdemServico;
import com.juaka.osworks.domain.repository.ClienteRepository;
import com.juaka.osworks.domain.repository.ComentarioRepository;
import com.juaka.osworks.domain.repository.OrdemServicoRepository;

@Service
public class OrdemServicoService {
	
	@Autowired
	private OrdemServicoRepository repository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ComentarioRepository comentarioRepository;
	
	public OrdemServico criar(OrdemServico ordemServico) {
		Cliente cliente = clienteRepository.findById(ordemServico.getCliente().getId())
				.orElseThrow(() -> new NegocioException("Cliente não encontrado"));
		
		ordemServico.setCliente(cliente);
		ordemServico.setStatus(StatusOrdemServico.ABERTA);
		ordemServico.setDataAbertura(OffsetDateTime.now());
		
		return repository.save(ordemServico);
	}
	
	public void finalizar(Long ordemServicoId) {
		OrdemServico ordemServico = buscar(ordemServicoId);
		
		ordemServico.finalizar();
		repository.save(ordemServico);
	}
	
	
	public Comentario adicionarComentario(Long ordemServicoId, String descricao) {
		OrdemServico ordemServico = buscar(ordemServicoId);
				
		Comentario c = new Comentario();
		c.setDataEnvio(OffsetDateTime.now());
		c.setDescricao(descricao);
		c.setOrdemServico(ordemServico);
		
		return comentarioRepository.save(c);
	}

	private OrdemServico buscar(Long ordemServicoId) {
		return repository.findById(ordemServicoId)
				.orElseThrow(() -> new EntidadeNaoEncontradaException("Ordem de serviço não encontrada"));
	}

}
