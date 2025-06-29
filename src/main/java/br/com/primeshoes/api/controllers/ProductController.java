package br.com.primeshoes.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.primeshoes.api.dtos.ProductCreateDTO;
import br.com.primeshoes.api.dtos.ProductResponseDTO;
import br.com.primeshoes.api.dtos.ProductVariationCreateDTO;
import br.com.primeshoes.api.dtos.ProductVariationResponseDTO;
import br.com.primeshoes.api.services.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;
    
    @PostMapping
    public ResponseEntity<?> store(@RequestBody ProductCreateDTO productCreateDTO) {
    	try {
    		return new ResponseEntity<>(productService.store(productCreateDTO), HttpStatus.CREATED);
    	}catch(Exception e) {
    		return new ResponseEntity<>("Você não é vendedor",HttpStatus.BAD_REQUEST);
    	}
    	
    }
    
    @PostMapping("/variation")
    public ResponseEntity<ProductVariationResponseDTO> storeProductVariation(@RequestBody ProductVariationCreateDTO productVariationCreateDTO) {
	
    	return new ResponseEntity<>(productService.storeProductVariation(productVariationCreateDTO), HttpStatus.CREATED);
    }
    
    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> list(){
    	return new ResponseEntity<>(productService.list(), HttpStatus.OK);
    }
    
    @GetMapping("/variation/{id}")
    public ResponseEntity<List<ProductVariationResponseDTO>> listVariations(@PathVariable long id){
    	return new ResponseEntity<>(productService.listVariations(id), HttpStatus.OK);
    }
    
    @GetMapping("/{id_product}")
    public ResponseEntity<ProductResponseDTO> show(@PathVariable long id) {
		try {
		    return new ResponseEntity<>(productService.show(id), HttpStatus.FOUND);
		}catch(Exception e) {
		    return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
		}
    }
    
    @DeleteMapping("/{id_product}")
    public ResponseEntity<String> destroy(@PathVariable long id) {
		try {
		    productService.destroy(id);
		    return new ResponseEntity<>("deletado com sucesso!",HttpStatus.OK);
		}catch(Exception e) {
		    return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
		}
    }
    @DeleteMapping("/variant/{id_product}")
    public ResponseEntity<String> destroyVariant(@PathVariable long id) {
		try {
		    productService.destroyVariant(id);
		    return new ResponseEntity<>("deletado com sucesso!",HttpStatus.OK);
		}catch(Exception e) {
		    return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
		}
    }
    
}
