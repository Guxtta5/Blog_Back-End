package com.blog.blog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.blog.blog.model.Postagem;


public interface PostagemRepository extends JpaRepository<Postagem, Long>{
	
	//O codigo abaixo ira procurar pelo titulo mesmo que coloque letas minusculas ou maiusculas 
	 public List<Postagem> findAllByTituloContainingIgnoreCase(@Param("titulo") String titulo);
}
