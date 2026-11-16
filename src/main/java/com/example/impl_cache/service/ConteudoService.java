package com.example.impl_cache.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.impl_cache.cache.BuscaCache;
import com.example.impl_cache.cache.ConteudoCache;
import com.example.impl_cache.entity.Conteudo;
import com.example.impl_cache.repository.ConteudoRepository;

@Service
public class ConteudoService {

	private final ConteudoRepository repository;
	private final ConteudoCache cache;

	public ConteudoService(ConteudoRepository repository, ConteudoCache cache) {
		this.repository = repository;
		this.cache = cache;
	}

	public BuscaCache buscarPorId(Long id) {
		return cache.buscarOuCarregar(id, () -> buscarNaFonte(id));
	}

	private Conteudo buscarNaFonte(Long id) {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
		return repository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Conteúdo não encontrado"));
	}

	public Conteudo salvar(Conteudo conteudo) {
		return repository.save(conteudo);
	}

	public Conteudo atualizar(Long id, Conteudo dados) {
		Conteudo existente = repository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Conteúdo não encontrado"));
		existente.setTitulo(dados.getTitulo());
		existente.setCorpo(dados.getCorpo());
		existente.setAutor(dados.getAutor());
		Conteudo salvo = repository.save(existente);
		cache.remover(id);
		return salvo;
	}

	public void excluir(Long id) {
		Conteudo existente = repository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Conteúdo não encontrado"));
		repository.delete(existente);
		cache.remover(id);
	}

}
