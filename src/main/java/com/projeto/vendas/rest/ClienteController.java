package com.projeto.vendas.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController /*mais usado para requisições que retornam dados em api rest*/
@RequestMapping("/api/clientes") /*detemina o endpoint raiz usado para acessar a classe de controlador de clientes*/
public class ClienteController {
	
	@GetMapping("/hello/{nome}") /*Determina o endpoint para realizar uma requisição*/
	@ResponseBody /*indica que o metodo retorna um response body como retorno*/
	public String helloCliente(@PathVariable("nome") String nomeCliente) {
		return String.format("Hello %s",nomeCliente);
	}
}
