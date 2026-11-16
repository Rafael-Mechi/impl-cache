package com.example.impl_cache.dto;

import jakarta.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ConteudoRequestDTO {

	@NotBlank
	private String titulo;

	@NotBlank
	private String corpo;

	@NotBlank
	private String autor;

}
