package br.com.primeshoes.api.mappers;

import br.com.primeshoes.api.dtos.PaymentCreateDTO;
import br.com.primeshoes.api.dtos.PaymentResponseDTO;
import br.com.primeshoes.api.entities.Payment;

public class PaymentMapper {

	
	
	public static PaymentResponseDTO toDTO(Payment payment) {
		PaymentResponseDTO paymentResponseDTO = new PaymentResponseDTO(
				payment.getId(),
				payment.getOrder(),
				payment.getPaymentStatus(),
				payment.getPaymentMethod(),
				payment.getAmount(),
				payment.getCreatedAt());
		return paymentResponseDTO;
		
	}
	
	public static Payment toEntity(PaymentCreateDTO paymentCreateDTO) {
		Payment payment = new Payment();
		payment.setPaymentMethod(paymentCreateDTO.paymentMethod());
		
		return payment;
	}
	
}
