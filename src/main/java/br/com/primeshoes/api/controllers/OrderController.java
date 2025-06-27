package br.com.primeshoes.api.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.primeshoes.api.dtos.OrderCreateDTO;
import br.com.primeshoes.api.dtos.OrderResponseDTO;
import br.com.primeshoes.api.services.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    
    @Autowired
    private OrderService orderService;
    
    @PostMapping
    public ResponseEntity<OrderResponseDTO> store(OrderCreateDTO orderCreateDTO) {
	
    	return new ResponseEntity<>(orderService.store(orderCreateDTO), HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<OrderResponseDTO>> list(){
    	return new ResponseEntity<>(orderService.list(),HttpStatus.OK);
    }
    @GetMapping("/user/{email}")
    public ResponseEntity<List<OrderResponseDTO>> listAll(@PathVariable Map<String,String> email){
    	return new ResponseEntity<>(orderService.listAll(email.get("email")),HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDTO> show(@PathVariable long id) {
	
    	return new ResponseEntity<>(orderService.show(id),HttpStatus.OK);
    }
    
}
