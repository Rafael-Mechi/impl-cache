package com.example.impl_cache.cache;

import com.example.impl_cache.entity.Conteudo;

public class BuscaCache {

	private final Conteudo conteudo;
	private final boolean hit;

	public BuscaCache(Conteudo conteudo, boolean hit) {
		this.conteudo = conteudo;
		this.hit = hit;
	}

	public Conteudo getConteudo() {
		return conteudo;
	}

	public boolean isHit() {
		return hit;
	}

}
