package com.blog.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.blog.blog.model.Tema;

public interface TemaRepository extends JpaRepository<Tema, Long>{
	
	//O codigo abaixo ira procurar pela descrição mesmo que coloque letas minusculas ou maiusculas 
	public List<Tema> findAllByDescricaoContainingIgnoreCase(@Param("descriçao") String descricao);

}
