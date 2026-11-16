package com.example.impl_cache.mapper;

import org.springframework.stereotype.Component;

import com.example.impl_cache.dto.ConteudoDTO;
import com.example.impl_cache.dto.ConteudoRequestDTO;
import com.example.impl_cache.entity.Conteudo;

@Component
public class ConteudoMapper {

	public ConteudoDTO toDTO(Conteudo conteudo) {
		return new ConteudoDTO(conteudo.getId(), conteudo.getTitulo(), conteudo.getCorpo(),
				conteudo.getAutor(), conteudo.getAtualizadoEm());
	}

	public Conteudo toEntity(ConteudoRequestDTO dto) {
		Conteudo conteudo = new Conteudo();
		conteudo.setTitulo(dto.getTitulo());
		conteudo.setCorpo(dto.getCorpo());
		conteudo.setAutor(dto.getAutor());
		return conteudo;
	}

}
