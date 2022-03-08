package com.generation.games.lojaGames.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.games.lojaGames.model.Usuario;
import com.generation.games.lojaGames.service.UsuarioService;
import com.generation.games.lojaGames.model.UsuarioLogin;
import com.generation.games.lojaGames.controller.UsuarioController;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin( origins = "*", allowedHeaders= "*")

	public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;
		
	@GetMapping("/all")
	public ResponseEntity <List<Usuario>> getAll(){
		return (usuarioService.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Usuario> getById(@PathVariable Long id){
		return usuarioService.getById(id);
	}
	
	@PostMapping("/logar")
	public ResponseEntity<UsuarioLogin> login(@RequestBody Optional<UsuarioLogin> usuarioLogin){
		
	return usuarioService.autenticarUsuario(usuarioLogin)
		.map(resposta -> ResponseEntity.status(HttpStatus.OK).body(resposta))
     	.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
   }
	
	@PostMapping("/cadastrar")
	public ResponseEntity<Usuario> postUsuario(@Valid @RequestBody Usuario usuario){
		
		return usuarioService.cadastrarUsuario(usuario)
			.map(resposta -> ResponseEntity.status(HttpStatus.CREATED).body(resposta))
			.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
		}
	
	@PutMapping("/atualizar")
	public ResponseEntity<Usuario> putUsuario(@Valid @RequestBody Usuario usuario){
		
		return usuarioService.atualizarUsuario(usuario)
			.map(resposta -> ResponseEntity.status(HttpStatus.OK).body(resposta))
			.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
}
