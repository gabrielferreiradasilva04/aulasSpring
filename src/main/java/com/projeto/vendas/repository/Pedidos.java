package com.projeto.vendas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projeto.vendas.model.Cliente;
import com.projeto.vendas.model.Pedido;

public interface Pedidos extends JpaRepository<Pedido, Integer> {
	
	List<Pedido> findByCliente(Cliente cliente);
}
