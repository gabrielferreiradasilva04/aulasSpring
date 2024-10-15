package com.projeto.vendas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.projeto.vendas.model.Cliente;

import java.util.List;

public interface Clientes extends JpaRepository<Cliente, Integer > {

	@Query(value = " select * from cliente c where c.nome like %:nome%", nativeQuery = true)
    List<Cliente> encontrarPorNome(@Param("nome")String nome);

    boolean existsByNome(String nome); /*O Spring data jpa usa convençoes para trabalhar com métodos, como delete, find, exists, etc*/
    
    @Query(" delete from Cliente c where c.nome =:nome ")
    @Modifying /*notação necessária quando uma query não é usada com intuito de consulta e sim com modificação*/
    void deleteByNome(String nome);
    
    @Query(" select c from Cliente c left join fetch c.pedidos where c.id = :id")
    Cliente findClienteFetchPeidos(@Param("id") Integer id);

}
