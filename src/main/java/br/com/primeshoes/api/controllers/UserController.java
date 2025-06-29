package br.com.primeshoes.api.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.primeshoes.api.auth.JwtService;
import br.com.primeshoes.api.dtos.AddressResponseDTO;
import br.com.primeshoes.api.dtos.AuthDTO;
import br.com.primeshoes.api.dtos.UserCreateDTO;
import br.com.primeshoes.api.dtos.UserResponseDTO;
import br.com.primeshoes.api.dtos.UserUpdateDTO;
import br.com.primeshoes.api.entities.User;
import br.com.primeshoes.api.mappers.UserMapper;
import br.com.primeshoes.api.services.AddressService;
import br.com.primeshoes.api.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;
	private final AuthenticationManager authenticationManager;
	private final JwtService jwtService;
	
	public UserController(AuthenticationManager authenticationManager, JwtService jwtService,
			AddressService addressService) {
		this.authenticationManager = authenticationManager;
		this.jwtService = jwtService;
	}
	
	@PostMapping("/auth")
	public ResponseEntity<?> auth(@RequestBody AuthDTO authDTO){
		Authentication auth = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authDTO.email(), authDTO.password())
				);
		
		User user = (User) auth.getPrincipal();
		String token = jwtService.generateToken(user);
		
		return new ResponseEntity<>(Map.of("token", token), HttpStatus.OK);
	}
	
	@PostMapping("/find")
	public ResponseEntity<?> findByEmail(@RequestBody Map<String,String> email){
		try{
			User user= userService.findByEmail(email.get("email"));
			
			return new ResponseEntity<>(UserMapper.toDTO(user),HttpStatus.OK);

		}catch(Exception e) {
			return new ResponseEntity<>("Error: "+e.getMessage(),HttpStatus.OK);
		}
	}
	@PostMapping("/register")
	public ResponseEntity<UserResponseDTO> store(@RequestBody UserCreateDTO userCreateDTO) {
		
	    return new ResponseEntity<UserResponseDTO>(userService.store(userCreateDTO), HttpStatus.CREATED);
	}
	@GetMapping
	public List<UserResponseDTO> list() {
		
	    return userService.list();
	}
	
	@GetMapping("/{id_user}")
	public ResponseEntity<?> show(@PathVariable long id_user){
		
		try {
			return new ResponseEntity<UserResponseDTO>(userService.find(id_user), HttpStatus.OK);
		}catch(Exception e){
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);	
		}
	}
	@PutMapping
	public ResponseEntity<?> update(@RequestBody UserUpdateDTO userUpdateDTO){
		try {
		    return new ResponseEntity<UserResponseDTO>
		    	(userService.update(userUpdateDTO),HttpStatus.OK);
		}catch(Exception e) {
		    return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
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
