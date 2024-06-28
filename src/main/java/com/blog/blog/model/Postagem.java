package com.blog.blog.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_postagens")
public class Postagem {
	
	// O código abaixo ira criar uma chave unica para cada postagem
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	//O código abaixo ira criar um titulo para ser colocado nas postagem 
	@NotBlank(message = "O atributo titulo não deve estar vazio, preencha por favor!!")
	@Size(min = 01, max = 100, message = "O atributo titulo deve conter no mínimo 05 e no máximo 100 caracteres")
	private String titulo;
	
	//O código abaixo ira criar um texto para ser colocado nas postagem 
	@NotBlank(message = "O atributo texto não deve estar vazio, preencha por favor!!")
	@Size(min = 01, max = 1250, message = "O atributo texto deve conter no mínimo 01 e no máximo 1250 caracteres")
	private String texto;
	
	//O código abaixo ira colocar a data que a postagem foi criada 
	@UpdateTimestamp
	private LocalDateTime data;
	
	// o código abaixo interliga a classe postagem com a tema
	@ManyToOne
	@JsonIgnoreProperties("postagem")
	private Tema tema;

	public Long getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getTexto() {
		return texto;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	public Tema getTema() {
		return tema;
	}

	public void setTema(Tema tema) {
		this.tema = tema;
	}
	
	
}
