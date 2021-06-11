package com.conexaopostgres.ConexaoBanco;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = 
	@UniqueConstraint(columnNames = "placa", name = "veiculo_uk"))
public class Veiculo {

	@Id
	@GeneratedValue (strategy = GenerationType.AUTO)
	
	private long id_veiculo;
	
	private String placa;
	
	private String marca;
	
	private String modelo;
	
	private int ano;
	
	private String cor;
	
	private String combustivel;

	public long getId_veiculo() {
		return id_veiculo;
	}

	public void setId_veiculo(long id_veiculo) {
		this.id_veiculo = id_veiculo;
	}
	

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public String getCombustivel() {
		return combustivel;
	}

	public void setCombustivel(String combustivel) {
		this.combustivel = combustivel;
	}

	@Override
	public String toString() {
		return "Veiculo [id_veiculo=" + id_veiculo + ", placa=" + placa + ", modelo=" + modelo + ", ano=" + ano
				+ ", cor=" + cor + ", combustivel=" + combustivel + "]";
	}
	
}
