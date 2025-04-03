package br.com.primeshoes.api.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.primeshoes.api.dtos.UserCreateDTO;
import br.com.primeshoes.api.dtos.UserResponseDTO;
import br.com.primeshoes.api.dtos.UserUpdateDTO;
import br.com.primeshoes.api.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping
	public ResponseEntity<UserResponseDTO> store(UserCreateDTO userCreateDTO) {
		
	    return new ResponseEntity<UserResponseDTO>(userService.store(userCreateDTO), HttpStatus.CREATED);
	}
	@GetMapping
	public List<UserResponseDTO> list() {
		
	    return userService.list();
	}
	
	@GetMapping("/{id_user}")
	public ResponseEntity<UserResponseDTO> show(@PathVariable long id_user){
		
		try {
			return new ResponseEntity<UserResponseDTO>(userService.show(id_user), HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
			
		}
	}
	@PutMapping
	public ResponseEntity<UserResponseDTO> update(UserUpdateDTO userUpdateDTO){
		try {
		    
		    return new ResponseEntity<UserResponseDTO>
		    	(userService.update(userUpdateDTO),HttpStatus.OK);
		}catch(Exception e) {
		    return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
	 
	@DeleteMapping("/{id_user}")
	public ResponseEntity<String> destroy(@PathVariable long id_user){
	    try {
		userService.destroy(id_user);
		return new ResponseEntity<String>("usuario deletado com sucesso",HttpStatus.OK);
	    }catch(Exception e){
		return new ResponseEntity<String>("Usuario não encontrado", HttpStatus.NOT_FOUND);
	    }
	    
	}
}
