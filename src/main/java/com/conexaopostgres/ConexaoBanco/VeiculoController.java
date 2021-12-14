package com.conexaopostgres.ConexaoBanco;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/veiculo")
@CrossOrigin(origins = "http://18.222.216.63:3000")
public class VeiculoController {

	@Autowired
	private VeiculoRepositorio repositorio;
	
	@PostMapping(path="/add")
	public @ResponseBody String novoVeiculo(@RequestParam String placa,
											@RequestParam String marca,
											@RequestParam String modelo,
											@RequestParam int ano,
											@RequestParam String cor,
											@RequestParam String combustivel) {
		
		Veiculo vehicle = new Veiculo();
		vehicle.setPlaca(placa);
		vehicle.setMarca(marca);
		vehicle.setModelo(modelo);
		vehicle.setAno(ano);
		vehicle.setCor(cor);
		vehicle.setCombustivel(combustivel);
		repositorio.save(vehicle);
		
		return "Valores salvos com sucesso!!";
	}
	
	@PostMapping(path="/addvehicle")
	public @ResponseBody String novoVeiculo2(@RequestBody Veiculo newVehicle) {
		repositorio.save(newVehicle);
		
		return "Veículo salvo com sucesso!!";
	}
	
	
	@GetMapping(path="/all")
	public @ResponseBody Iterable<Veiculo> retornaTodos(){
		return repositorio.findAll();
	}
	
	@GetMapping(path="/vehicle")
	public @ResponseBody Optional<Veiculo> retornaVeiculo(@RequestParam Long id){
		return repositorio.findById(id);
	}
	
	@GetMapping(path="locate_vehicle/{id}")
	public @ResponseBody Optional<Veiculo> retornaVeiculo2(@PathVariable (required = true, name = "id")
	Long id){
		return repositorio.findById(id);
	}
	
	//DELETAR VEÍCULO
	@DeleteMapping(path="delete_vehicle/{id}")
	public @ResponseBody String deleteVehicle(@PathVariable(required = true, name="id")
	Long id) {
		Optional<Veiculo> vehicle = repositorio.findById(id);
		
		if(vehicle.isPresent()) {
			repositorio.delete(vehicle.get());
			return "Veículo deletado com sucesso!!";
		}
		
		return "Veículo não encontrado!";
	}
	
	//ATUALIZAR VEICULO
	@PutMapping(path="update_vehicle/{id}")
	public @ResponseBody Optional<Veiculo> updateVehicle(@PathVariable(required = true, name ="id") Long id,
													     @RequestBody Veiculo vehicle){
		Optional<Veiculo> v = repositorio.findById(id);

		if(v.isPresent()) {
			v.get().setPlaca(vehicle.getPlaca());
			v.get().setMarca(vehicle.getMarca());
			v.get().setModelo(vehicle.getModelo());
			v.get().setAno(vehicle.getAno());
			v.get().setCor(vehicle.getCor());
			v.get().setCombustivel(vehicle.getCombustivel());
			repositorio.save(v.get());
			
			return v;
		}
		
		return null;
	}
	
	//ATUALIZAR VEICULO RETORNANDO STATUS 404 (not found) caso nao encontre
	@PutMapping(path="vehicle/{id}")
	public @ResponseBody ResponseEntity<Veiculo> updateVehicle2(@PathVariable(required = true, name ="id") Long id,
													     		@RequestBody Veiculo vehicle){
		Optional<Veiculo> v = repositorio.findById(id);

		if(v.isPresent()) {
			v.get().setPlaca(vehicle.getPlaca());
			v.get().setMarca(vehicle.getMarca());
			v.get().setModelo(vehicle.getModelo());
			v.get().setAno(vehicle.getAno());
			v.get().setCor(vehicle.getCor());
			v.get().setCombustivel(vehicle.getCombustivel());
			
			return ResponseEntity.ok(repositorio.save(v.get()));
		}
		
		return ResponseEntity.status(404).build();
	}
	
	//PESQUISAR POR PLACA
	@GetMapping(path="locate_by_placa/{placa}")
	public @ResponseBody List<Veiculo> locateByPlaca(@PathVariable(required = true, name = "placa") String placa){
		return repositorio.findByPlaca(placa);
	}
	
	//PESQUISAR POR MARCA
	@GetMapping(path="locate_by_marca/{marca}")
	public @ResponseBody List<Veiculo> locateByMarca(@PathVariable(required = true, name = "marca") String marca){
		return repositorio.findByMarca(marca);
	}
	
	//PESQUISAR POR MODELO
	@GetMapping(path="locate_by_modelo/{modelo}")
	public @ResponseBody List<Veiculo> locateByModelo(@PathVariable(required = true, name = "modelo") String modelo){
		return repositorio.findByModelo(modelo);
	}
	
	//PESQUISAR POR ANO
	@GetMapping(path="locate_by_ano/{ano}")
	public @ResponseBody List<Veiculo> locateByAno(@PathVariable(required = true, name = "ano") int ano){
		return repositorio.findByAno(ano);
	}
	
	//PESQUISAR POR COR
	@GetMapping(path="locate_by_cor/{cor}")
	public @ResponseBody List<Veiculo> locateByCor(@PathVariable(required = true, name = "cor") String cor){
		return repositorio.findByCor(cor);
	}
	
	//PESQUISAR POR COMBUSTIVEL
	@GetMapping(path="locate_by_combustivel/{combustivel}")
	public @ResponseBody List<Veiculo> locateByCombustivel(@PathVariable(required = true, name = "combustivel") String combustivel){
		return repositorio.findByCombustivel(combustivel);
	}
			
}
