package com.blog.blog.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.blog.blog.model.Tema;
import com.blog.blog.repository.TemaRepository;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;


@RestController
@RequestMapping("/temas")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TemaContoller {

	@Autowired
	private TemaRepository temaRepository;
	
	//O comando abaixo ira fazer com que mostre todos os temas
	@GetMapping
	public ResponseEntity<List<Tema>> getAll() {
		return ResponseEntity.ok(temaRepository.findAll());
	}
	
	//O comando abaixo ira fazer com que mostre a tamas que com o ID indicado
	@GetMapping("/{id}")
	public ResponseEntity<Tema> getById(@PathVariable Long id) {
		return temaRepository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	//O comando abaixo ira fazer com que mostre a temas que com a descriçao indicado
	@GetMapping("/descricao/{descricao}")
	public ResponseEntity<List<Tema>> getByTitle(@PathVariable String descricao) {
		return ResponseEntity.ok(temaRepository
				.findAllByDescricaoContainingIgnoreCase(descricao));
	}
	
	//O comando abaixo ira fazer com que possa criar um tema
	@PostMapping
	public ResponseEntity<Tema> post(@Valid @RequestBody Tema tema){
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(temaRepository.save(tema));
	}
	
	//O comando abaixo ira fazer com que atualize o tema
	@PutMapping
	public ResponseEntity<Tema> put(@Valid @RequestBody Tema tema) {
		return temaRepository.findById(tema.getId())
				.map(resposta -> ResponseEntity.status(HttpStatus.CREATED)
						.body(temaRepository.save(tema)))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	//O comando abaixo ira fazer com que delete o tema com o ID indicado
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		Optional<Tema> tema = temaRepository.findById(id);
		
		if(tema.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		
		temaRepository.deleteById(id);
	}
}
