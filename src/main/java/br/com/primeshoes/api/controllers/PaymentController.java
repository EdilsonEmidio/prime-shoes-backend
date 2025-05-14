package br.com.primeshoes.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.primeshoes.api.dtos.PaymentCreateDTO;
import br.com.primeshoes.api.dtos.PaymentResponseDTO;
import br.com.primeshoes.api.services.PaymentService;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

	@Autowired
	private PaymentService paymentService;
	
	@PostMapping
	public ResponseEntity<PaymentResponseDTO> save(@RequestBody PaymentCreateDTO payment){
		return new ResponseEntity<>(paymentService.save(payment),HttpStatus.CREATED);
	}
	
	@GetMapping("/{id_user}")
	public ResponseEntity<List<PaymentResponseDTO>> listAll(@PathVariable long id_user){
		
		return new ResponseEntity<>(paymentService.listAll(id_user),HttpStatus.FOUND);
	}
	@GetMapping("/pedido/{id_order}")
	public ResponseEntity<PaymentResponseDTO> findPayment(@PathVariable long id_order){
		
		return new ResponseEntity<>(paymentService.findPayment(id_order),HttpStatus.FOUND);
	}
}
