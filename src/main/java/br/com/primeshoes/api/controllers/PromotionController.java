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

import br.com.primeshoes.api.dtos.PromotionCreateDTO;
import br.com.primeshoes.api.dtos.PromotionResponseDTO;
import br.com.primeshoes.api.dtos.PromotionUpdateDTO;
import br.com.primeshoes.api.services.PromotionService;

@RestController
@RequestMapping("/api/promotions")
public class PromotionController {
	
	
	@Autowired
	private PromotionService promotionService;
	
	@PostMapping
	public ResponseEntity<PromotionResponseDTO> save(@RequestBody PromotionCreateDTO promotionCreateDTO){
		return new ResponseEntity<PromotionResponseDTO>(promotionService.save(promotionCreateDTO),HttpStatus.CREATED); 
	}
	
	@GetMapping("/{id_product}")
	public ResponseEntity<List<PromotionResponseDTO>> listProduct(@RequestParam long id_product){
		return new ResponseEntity<>(promotionService.listProduct(id_product),HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<PromotionResponseDTO>> listAll(){
		return new ResponseEntity<>(promotionService.listAll(),HttpStatus.OK);
	}
	@PutMapping
	public ResponseEntity<PromotionResponseDTO> update(PromotionUpdateDTO promotionUpdateDTO){
		return new ResponseEntity<PromotionResponseDTO>(promotionService.update(promotionUpdateDTO),HttpStatus.ACCEPTED);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<String> destroy(@RequestParam long id){
		try {
			promotionService.destroy(id);
		}catch(Exception e){
			return new ResponseEntity<String>("impossivel deletar",HttpStatus.CONFLICT);
		}
		return new ResponseEntity<String>("deletado com sucesso",HttpStatus.OK);
	}
}
