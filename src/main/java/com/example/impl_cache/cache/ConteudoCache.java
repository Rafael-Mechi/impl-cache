package com.example.impl_cache.cache;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.example.impl_cache.entity.Conteudo;

@Component
public class ConteudoCache {

	private static final Logger log = LoggerFactory.getLogger(ConteudoCache.class);

	private final Map<Long, EntradaCache> cache = new HashMap<>();
	private final int maxItens;
	private final long ttlMillis;
	private long hits;
	private long misses;
	private long evictions;

	public ConteudoCache(@Value("${app.cache.max-itens}") int maxItens,
			@Value("${app.cache.ttl-segundos}") long ttlSegundos) {
		this.maxItens = maxItens;
		this.ttlMillis = ttlSegundos * 1000;
	}

	public BuscaCache buscarOuCarregar(Long id, Supplier<Conteudo> fonte) {
		long inicio = System.currentTimeMillis();

		EntradaCache entrada = cache.get(id);
		if (entrada != null && !entrada.expirado(ttlMillis)) {
			entrada.atualizarAcesso();
			hits++;
			log.info("Cache HIT  - id={} - {} ms", id, System.currentTimeMillis() - inicio);
			return new BuscaCache(entrada.getConteudo(), true);
		}
		if (entrada != null) {
			cache.remove(id);
		}
		misses++;

		Conteudo conteudo = fonte.get();

		if (cache.size() >= maxItens) {
			removerLru();
			evictions++;
		}
		cache.put(id, new EntradaCache(conteudo));

		log.info("Cache MISS - id={} - {} ms", id, System.currentTimeMillis() - inicio);
		return new BuscaCache(conteudo, false);
	}

	public void remover(Long id) {
		cache.remove(id);
	}

	public void limpar() {
		cache.clear();
	}

	public EstatisticasCache estatisticas() {
		double total = hits + misses;
		double hitRatio = total == 0 ? 0.0 : hits / total;
		return new EstatisticasCache(cache.size(), maxItens, hits, misses, evictions, hitRatio);
	}

	private void removerLru() {
		Long chaveLru = null;
		long maisAntigo = Long.MAX_VALUE;
		for (Map.Entry<Long, EntradaCache> entry : cache.entrySet()) {
			if (entry.getValue().getUltimoAcesso() < maisAntigo) {
				maisAntigo = entry.getValue().getUltimoAcesso();
				chaveLru = entry.getKey();
			}
		}
		if (chaveLru != null) {
			cache.remove(chaveLru);
		}
	}

}
