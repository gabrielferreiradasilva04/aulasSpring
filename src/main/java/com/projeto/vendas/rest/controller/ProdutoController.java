package com.projeto.vendas.rest.controller;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.projeto.vendas.model.Produto;
import com.projeto.vendas.repository.Produtos;


@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {
	
	private Produtos produtos;
	
	public ProdutoController(Produtos produtos) {
		this.produtos = produtos;
	}
	
	@GetMapping("{id}")
	public Produto getProdutoById(@PathVariable Integer id) {
		return produtos.findById(id).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado" ));
	}
	
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public Produto save(@RequestBody Produto produto) {
		return produtos.save(produto);
	}
	
	@PutMapping("{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void update(@PathVariable Integer id, @RequestBody Produto produto) {
		produtos.findById(id).map(produtoExistente -> {
			produto.setId(produtoExistente.getId());
			produtos.save(produto);
			return produtoExistente;
		}).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado" ));
	}
	
	@DeleteMapping("{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Integer id) {
		produtos.findById(id).map(produtoExistente ->{
			produtos.delete(produtoExistente);
			return Void.TYPE;
		}).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado" ));
	}
	
	@GetMapping
	public List<Produto> find(Produto produto){
		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING);
		Example<Produto> example = Example.of(produto, matcher);
		
		return produtos.findAll(example);
	}

	
}
