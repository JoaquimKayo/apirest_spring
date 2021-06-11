package com.conexaopostgres.ConexaoBanco;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface VeiculoRepositorio extends CrudRepository<Veiculo, Long>{
	Veiculo findById(long id);
	
	List<Veiculo> findByPlaca(String placa);
	List<Veiculo> findByMarca(String marca);
	List<Veiculo> findByModelo(String modelo);
	List<Veiculo> findByAno(int ano);
	List<Veiculo> findByCor(String cor);
	List<Veiculo> findByCombustivel(String combustivel);
}
