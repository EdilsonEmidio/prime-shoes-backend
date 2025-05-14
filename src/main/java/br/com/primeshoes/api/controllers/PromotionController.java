package br.com.primeshoes.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.primeshoes.api.dtos.PromotionResponseDTO;

@RestController
@RequestMapping("/api/promotions")
public class PromotionController {
	
	/*
	@Autowired
	private PromotionService promotionService;
	

	@GetMapping("/{id_product}")
	public ResponseEntity<PromotionResponseDTO> find(@RequestParam long id_product){
		
	}

	@GetMapping
	public ResponseEntity<listPromotionResponseDTO> listAll(){
		return promotionService.listAll();
	}*/
}
