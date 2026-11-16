package com.example.impl_cache.cache;

public class EstatisticasCache {

	private final int tamanhoAtual;
	private final int limite;
	private final long hits;
	private final long misses;
	private final long evictions;
	private final double hitRatio;

	public EstatisticasCache(int tamanhoAtual, int limite, long hits, long misses, long evictions,
			double hitRatio) {
		this.tamanhoAtual = tamanhoAtual;
		this.limite = limite;
		this.hits = hits;
		this.misses = misses;
		this.evictions = evictions;
		this.hitRatio = hitRatio;
	}

	public int getTamanhoAtual() {
		return tamanhoAtual;
	}

	public int getLimite() {
		return limite;
	}

	public long getHits() {
		return hits;
	}

	public long getMisses() {
		return misses;
	}

	public long getEvictions() {
		return evictions;
	}

	public double getHitRatio() {
		return hitRatio;
	}

}
