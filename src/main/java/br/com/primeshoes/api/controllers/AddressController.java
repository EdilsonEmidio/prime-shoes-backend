package br.com.primeshoes.api.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.primeshoes.api.dtos.AddressCreateDTO;
import br.com.primeshoes.api.dtos.AddressResponseDTO;
import br.com.primeshoes.api.services.AddressService;

@RestController
@RequestMapping("/api/address")
public class AddressController {

	
	@Autowired
	private AddressService addressService;
	
	@PostMapping("/find")
	public ResponseEntity<AddressResponseDTO> findByUser(@RequestBody Map<String, String> email){
		return new ResponseEntity<>(addressService.findByUser(email.get("email")),HttpStatus.OK);
	}
	@PostMapping
	public ResponseEntity<AddressResponseDTO> update(@RequestBody AddressCreateDTO address) {
		
		return new ResponseEntity<>(addressService.update(address),HttpStatus.ACCEPTED);
	}
}
