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

import br.com.primeshoes.api.dtos.CartCreateDTO;
import br.com.primeshoes.api.dtos.CartItemCreateDTO;
import br.com.primeshoes.api.dtos.CartItemResponseDTO;
import br.com.primeshoes.api.dtos.CartResponseDTO;
import br.com.primeshoes.api.entities.Cart;
import br.com.primeshoes.api.mappers.CartItemMapper;
import br.com.primeshoes.api.services.CartService;

@RestController
@RequestMapping("/api/carts")
public class CartController {
    
    @Autowired
    private CartService cartService;
    
    @PostMapping
    public ResponseEntity<CartResponseDTO> store(@RequestBody CartCreateDTO cartCreateDTO) {
	
		return new ResponseEntity<>(
			cartService.store(cartCreateDTO), HttpStatus.CREATED);
    }
    
    @PostMapping("/item")
    public ResponseEntity<CartItemResponseDTO> addCartItem(@RequestBody CartItemCreateDTO cartItemCreateDTO) {
	
    	return new ResponseEntity<>(cartService.addCartItem(cartItemCreateDTO),
		HttpStatus.CREATED);
    }
    @DeleteMapping("/item/{idCartItem}")
    public ResponseEntity<String> removeCartItem(@PathVariable long idCartItem){
		try {
		    cartService.removeCartItem(idCartItem);
		    return new ResponseEntity<>("Deletado com sucesso",HttpStatus.OK);
		}catch(Exception e) {
		    return new ResponseEntity<>("Carrinho ou item não foi encontrado",HttpStatus.NOT_FOUND);
		}
    }
    
    @GetMapping
    public ResponseEntity<List<CartResponseDTO>> list(){
    	return new ResponseEntity<>(cartService.list(), HttpStatus.OK);
    }
    @GetMapping("/items/{id}")
    public ResponseEntity<List<CartItemResponseDTO>> listItems(@PathVariable long id){
    	return new ResponseEntity<>(cartService.listItems(id), HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<CartResponseDTO> show(@PathVariable long id) {
		try {
		    return new ResponseEntity<>(cartService.show(id), HttpStatus.FOUND);
		}catch(Exception e) {
		    return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
		}
    }
    
    @DeleteMapping("/{id_cart}")
    public ResponseEntity<String> destroy(@PathVariable long id) {
		try {
		    cartService.destroy(id);
		    return new ResponseEntity<>("deletado com sucesso!",HttpStatus.OK);
		}catch(Exception e) {
		    return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
		}
    }
    
}
