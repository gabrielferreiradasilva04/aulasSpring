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

import com.projeto.vendas.model.Cliente;
import com.projeto.vendas.repository.Clientes;

@RestController /* mais usado para requisições que retornam dados em api rest */
@RequestMapping("/api/clientes") /*
									 * detemina o endpoint raiz usado para acessar a classe de controlador de
									 * clientes
									 */
public class ClienteController {

	
	private Clientes clientes;
	
	public ClienteController(Clientes clientes) {
		this.clientes = clientes;
	}
	
	/*Determina o endpoint para realizar uma requisição, pode atender mais de um endpoint especificado no value
	pode atender mais de um tipo de body para requisição, xml ou json, que depende da especificação do cliente 
	da requisição, e o mesmo vale para o produces, que pode retornar tanto um json quanto um xml*/
	@GetMapping(
			value= {"/hello/{nome}","api/hello"}
	) 
	public String helloCliente(@PathVariable("nome") String nomeCliente) {
		return String.format("Hello %s",nomeCliente);
	}
	
	
	@GetMapping("{id}")
	public Cliente getClienteById( @PathVariable Integer id ){
		return clientes.findById(id)
				.orElseThrow( () -> 
				new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
		
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente save (@RequestBody Cliente cliente) {
		return clientes.save(cliente);
	}
	
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete (@PathVariable Integer id){
		clientes.findById(id).map(cliente -> {
								clientes.delete(cliente);
								return cliente;
								})
		.orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
		
	}
	
	@PutMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update (@PathVariable Integer id,  @RequestBody Cliente cliente){
		clientes.findById(id).map(clienteExistente -> {
			cliente.setId(clienteExistente.getId());
			clientes.save(cliente);
			return clienteExistente;
		}).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
	}
	
	
	@GetMapping
	public List<Cliente>find (Cliente filtro){
		ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreCase().withStringMatcher( StringMatcher.CONTAINING );
		Example<Cliente> example = Example.of(filtro, matcher);
		
		return clientes.findAll(example);
	}
	
	
}
