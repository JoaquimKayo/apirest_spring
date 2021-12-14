package com.conexaopostgres.ConexaoBanco;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/usuario_veiculo")
@CrossOrigin(origins = "http://18.222.216.63:3000")
public class UsuarioVeiculoController {

	@Autowired
	private VeiculoRepositorio repositorioVeiculo;
	@Autowired
	private UsuarioRepositorio repositorioUsuario;
	
	@PostMapping(path="/add")
	public @ResponseBody String novoUsuarioVeiculo(@RequestParam String nome, 
											@RequestParam String sobrenome,
											@RequestParam String email,
											@RequestParam String placa,
											@RequestParam String marca,
											@RequestParam String modelo,
											@RequestParam int ano,
											@RequestParam String cor,
											@RequestParam String combustivel
											) {
	
		
		Usuario user = new Usuario();
		user.setNome(nome);
		user.setSobrenome(sobrenome);
		user.setEmail(email);
		repositorioUsuario.save(user);
		
		Veiculo vehicle = new Veiculo();
		vehicle.setPlaca(placa);
		vehicle.setMarca(marca);
		vehicle.setModelo(modelo);
		vehicle.setAno(ano);
		vehicle.setCor(cor);
		vehicle.setCombustivel(combustivel);
		repositorioVeiculo.save(vehicle);
		
		return "Valores salvos com sucesso!!";
	}
	
	@PostMapping(path="addUserVehicle")
	public @ResponseBody String novoUsuarioVeiculo2(@RequestBody Usuario newUser, @RequestBody Veiculo newVehicle) {
		repositorioUsuario.save(newUser);
		repositorioVeiculo.save(newVehicle);
		
		return "Valores salvos com sucesso!!";
	}
	
	
}
