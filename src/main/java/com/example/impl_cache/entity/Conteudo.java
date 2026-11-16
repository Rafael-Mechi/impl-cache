package com.example.impl_cache.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "conteudos")
@Getter
@Setter
@NoArgsConstructor
public class Conteudo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String titulo;

	private String corpo;

	private String autor;

	private LocalDateTime atualizadoEm;

	@PrePersist
	@PreUpdate
	void atualizarTimestamp() {
		this.atualizadoEm = LocalDateTime.now();
	}

}
