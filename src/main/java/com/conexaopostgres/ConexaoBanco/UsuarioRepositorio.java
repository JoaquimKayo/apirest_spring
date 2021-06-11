package com.conexaopostgres.ConexaoBanco;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UsuarioRepositorio extends CrudRepository<Usuario, Long>{

	Usuario findById(long id);
	
	List<Usuario> findByNome(String nome);
	List<Usuario> findBySobrenome(String sobrenome);
	List<Usuario> findByEmail(String email);
	
}
