package com.projeto.vendas.rest;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
	
	
	@GetMapping(/*Determina o endpoint para realizar uma requisição, pode atender mais de um endpoint especificado no value
			pode atender mais de um tipo de body para requisição, xml ou json, que depende da especificação do cliente 
			da requisição, e o mesmo vale para o produces, que pode retornar tanto um json quanto um xml*/
			value= {"/hello/{nome}","api/hello"},
			consumes = {"application/json", "application/xml"},
			produces = {"application/json", "application/xml"}
	) 
	@ResponseBody /*indica que o metodo retorna um response body como retorno*/
	public String helloCliente(@PathVariable("nome") String nomeCliente) {
		return String.format("Hello %s",nomeCliente);
	}
	
	
	@GetMapping(value = "/api/clientes/{id}")
	@ResponseBody
	public ResponseEntity<Cliente> getClienteById( @PathVariable Integer id ){
		Optional<Cliente> cliente = clientes.findById(id);
		if(cliente.isPresent()) {
			return ResponseEntity.ok(cliente.get()); /*O response entity representa o corpo da resposta e pode receber uma série de configurações*/
			
		}
	}
	
	
	
	
}
