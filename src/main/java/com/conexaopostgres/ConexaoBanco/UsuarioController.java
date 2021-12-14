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
@RequestMapping("/usuario")
@CrossOrigin(origins = "*")
public class UsuarioController {

	@Autowired
	private UsuarioRepositorio repositorio;
	
	// ADICIONAR USUARIO VIA POST
	@PostMapping(path="/add")
	public @ResponseBody String novoUsuario (@RequestParam String nome, 
											 @RequestParam String sobrenome,
											 @RequestParam String email) {
		
		Usuario user = new Usuario();
		user.setNome(nome);
		user.setSobrenome(sobrenome);
		user.setEmail(email);
		repositorio.save(user);
		
		return "Valores Salvos Com Sucesso!!";
	}
	
	// ADICIONAR USUARIO VIA POST COM DADOS PASSADOS TIPO JSON
	@PostMapping(path="/adduser")
	public @ResponseBody String novoUsuario2(@RequestBody Usuario newUser) {
		repositorio.save(newUser);
		
		return "Usuário salvo com sucesso!!";
	}

	// LISTAR TODOS OS USUARIOS VIA GET
	@GetMapping(path="/all")
	public @ResponseBody Iterable<Usuario> retornaTodos(){
		return repositorio.findAll();
	}
	
	//LISTAR USUARIO COM ID DE PASSAGEM DE PARAMETRO VIA GET
	@GetMapping(path="/user")
	public @ResponseBody Optional<Usuario> retornaUser(@RequestParam Long id){
		return repositorio.findById(id);
	}
	
	//LISTAR USUARIO COM ID PASSADO VIA PARAMETRO GET SEM UTILIZAR AS -> ?
	@GetMapping(path="locate_user/{id}")
	public @ResponseBody Optional<Usuario> retornaUser2(@PathVariable(required = true, name="id")
	Long id){
		return repositorio.findById(id);
	}
	
	//DELETAR USUARIO
	@DeleteMapping(path="delete_user/{id}")
	public @ResponseBody String deleteUser(@PathVariable(required = true, name="id")
	Long id) {
		Optional<Usuario> user = repositorio.findById(id);
		
		if(user.isPresent()) {
			repositorio.delete(user.get());
			return "Usuário deletado com sucesso!!";
		}
		
		return "Usuário não encontrado!";
	}
	
	//ATUALIZAR USUARIO
	@PutMapping(path="update_user/{id}")
	public @ResponseBody Optional<Usuario> updateUser(@PathVariable(required = true, name ="id") Long id,
													  @RequestBody Usuario user){
		Optional<Usuario> u = repositorio.findById(id);
		
		if(u.isPresent()) {
			u.get().setNome(user.getNome());
			u.get().setSobrenome(user.getSobrenome());
			u.get().setEmail(user.getEmail());
			repositorio.save(u.get());
			
			return u;
		}
		
		return null;
	}
	
	//ATUALIZAR USUARIO RETORNANDO STATUS 404 (not found) caso nao encontre
	@PutMapping(path="user/{id}")
	public @ResponseBody ResponseEntity<Usuario> updateUser2(@PathVariable(required = true, name ="id") Long id,
													  		 @RequestBody Usuario user){
		Optional<Usuario> u = repositorio.findById(id);
		
		if(u.isPresent()) {
			u.get().setNome(user.getNome());
			u.get().setSobrenome(user.getSobrenome());
			u.get().setEmail(user.getEmail());
			
			return ResponseEntity.ok(repositorio.save(u.get()));
		}
		
		return ResponseEntity.status(404).build();
	}
	
	//PESQUISAR POR NOME
	@GetMapping(path="locate_by_nome/{Nome}")
	public @ResponseBody List<Usuario> locateByNome(@PathVariable(required = true, name = "Nome") String nome){
		return repositorio.findByNome(nome);
	}
	
	
	//PESQUICA POR SOBRENOME
	@GetMapping(path="locate_by_sobrenome/{Sobrenome}")
	public @ResponseBody List<Usuario> locateBySobrenome(@PathVariable(required = true, name = "Sobrenome") String sobrenome){
		return repositorio.findBySobrenome(sobrenome);
	}
	
	//PESQUISA POR EMAIL
	@GetMapping(path="locate_by_email/{Email}")
	public @ResponseBody List<Usuario> locateByEmail(@PathVariable(required = true, name = "Email") String email){
		return repositorio.findByEmail(email);
	}
}