package com.example.impl_cache.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.impl_cache.cache.BuscaCache;
import com.example.impl_cache.dto.ConteudoDTO;
import com.example.impl_cache.dto.ConteudoRequestDTO;
import com.example.impl_cache.mapper.ConteudoMapper;
import com.example.impl_cache.service.ConteudoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/conteudos")
public class ConteudoController {

	private final ConteudoService service;
	private final ConteudoMapper mapper;

	public ConteudoController(ConteudoService service, ConteudoMapper mapper) {
		this.service = service;
		this.mapper = mapper;
	}

	@GetMapping("/{id}")
	public ResponseEntity<ConteudoDTO> buscar(@PathVariable Long id) {
		BuscaCache resultado = service.buscarPorId(id);
		return ResponseEntity.ok()
				.header("X-Cache", resultado.isHit() ? "HIT" : "MISS")
				.body(mapper.toDTO(resultado.getConteudo()));
	}

	@PostMapping
	public ResponseEntity<ConteudoDTO> criar(@Valid @RequestBody ConteudoRequestDTO dto) {
		ConteudoDTO salvo = mapper.toDTO(service.salvar(mapper.toEntity(dto)));
		return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ConteudoDTO> atualizar(@PathVariable Long id,
			@Valid @RequestBody ConteudoRequestDTO dto) {
		return ResponseEntity.ok(mapper.toDTO(service.atualizar(id, mapper.toEntity(dto))));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluir(@PathVariable Long id) {
		service.excluir(id);
		return ResponseEntity.noContent().build();
	}

}
