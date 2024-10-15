package com.projeto.vendas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projeto.vendas.model.Produto;

public interface Produtos extends JpaRepository<Produto, Integer>{
	
}
