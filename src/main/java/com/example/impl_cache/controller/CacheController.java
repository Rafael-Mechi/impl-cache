package com.example.impl_cache.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.impl_cache.cache.ConteudoCache;
import com.example.impl_cache.cache.EstatisticasCache;

@RestController
@RequestMapping("/cache")
public class CacheController {

	private final ConteudoCache cache;

	public CacheController(ConteudoCache cache) {
		this.cache = cache;
	}

	@GetMapping("/estatisticas")
	public EstatisticasCache estatisticas() {
		return cache.estatisticas();
	}

	@DeleteMapping
	public ResponseEntity<Void> limpar() {
		cache.limpar();
		return ResponseEntity.noContent().build();
	}

}
