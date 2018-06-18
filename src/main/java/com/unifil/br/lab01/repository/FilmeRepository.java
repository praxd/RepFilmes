package com.unifil.br.lab01.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unifil.br.lab01.entity.Filme;

@Repository
public interface FilmeRepository extends JpaRepository<Filme, Long>{
	
}
