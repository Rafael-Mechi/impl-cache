package com.example.impl_cache.cache;

import com.example.impl_cache.entity.Conteudo;

public class EntradaCache {

	private final Conteudo conteudo;
	private final long armazenadoEm;
	private long ultimoAcesso;

	public EntradaCache(Conteudo conteudo) {
		this.conteudo = conteudo;
		this.armazenadoEm = System.currentTimeMillis();
		this.ultimoAcesso = this.armazenadoEm;
	}

	public Conteudo getConteudo() {
		return conteudo;
	}

	public long getArmazenadoEm() {
		return armazenadoEm;
	}

	public long getUltimoAcesso() {
		return ultimoAcesso;
	}

	public void atualizarAcesso() {
		this.ultimoAcesso = System.currentTimeMillis();
	}

	public boolean expirado(long ttlMillis) {
		return System.currentTimeMillis() - armazenadoEm > ttlMillis;
	}

}
