package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projeto.vendas.model.Cliente;

import java.util.List;

@Repository
public interface Clientes extends JpaRepository<Cliente, Integer > {

    List<Cliente> findByNomeLike(String nome);

    boolean existsByNome(String nome);

}
