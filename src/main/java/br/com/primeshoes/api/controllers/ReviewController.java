package br.com.primeshoes.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.primeshoes.api.dtos.ReviewCreateDTO;
import br.com.primeshoes.api.dtos.ReviewResponseDTO;
import br.com.primeshoes.api.dtos.ReviewUpdateDTO;
import br.com.primeshoes.api.services.ReviewService;

@RestController
@RequestMapping("/api/review")
public class ReviewController {

	@Autowired
	private ReviewService reviewService;
	
	@PostMapping
	public ResponseEntity<ReviewResponseDTO> save(@RequestBody ReviewCreateDTO review){
		
		return new ResponseEntity<ReviewResponseDTO>(reviewService.save(review),HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<ReviewResponseDTO> update(@RequestBody ReviewUpdateDTO review){
		
		return new ResponseEntity<ReviewResponseDTO>(reviewService.update(review),HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/product/{id_product}")
	public ResponseEntity<List<ReviewResponseDTO>> listProduct(@RequestParam long id_product){
		
		return new ResponseEntity<>(reviewService.listProduct(id_product),HttpStatus.FOUND);
	}
	
	@GetMapping("/user/{id_user}")
	public ResponseEntity<List<ReviewResponseDTO>> listUser(@RequestParam long id_user){
		
		return new ResponseEntity<>(reviewService.listProduct(id_user),HttpStatus.FOUND);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> destroy(@RequestParam long id){
		try {
			reviewService.destroy(id);
		}catch(Exception e) {
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.EXPECTATION_FAILED);
		}
		return new ResponseEntity<String>("review deletada!",HttpStatus.OK);
	}
	
}
