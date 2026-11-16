package com.example.impl_cache.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConteudoDTO {

	private Long id;
	private String titulo;
	private String corpo;
	private String autor;
	private LocalDateTime atualizadoEm;

}
