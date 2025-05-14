package br.com.primeshoes.api.services;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.primeshoes.api.dtos.PaymentCreateDTO;
import br.com.primeshoes.api.dtos.PaymentResponseDTO;
import br.com.primeshoes.api.entities.Order;
import br.com.primeshoes.api.entities.Payment;
import br.com.primeshoes.api.entities.User;
import br.com.primeshoes.api.enuns.PaymentStatus;
import br.com.primeshoes.api.mappers.PaymentMapper;
import br.com.primeshoes.api.repositories.OrderRepository;
import br.com.primeshoes.api.repositories.PaymentRepository;
import br.com.primeshoes.api.repositories.UserRepository;

@Service
public class PaymentService {

	@Autowired
	private PaymentRepository paymentRepository;
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private UserRepository userRepository;
	
	
	public PaymentResponseDTO save(PaymentCreateDTO paymentCreateDTO) {
		Payment payment = PaymentMapper.toEntity(paymentCreateDTO);
		Order order = orderRepository.findById(paymentCreateDTO.order()).orElseThrow(
				()-> new RuntimeException("pedido não encontrado"));
		float amount = order.getTotalPrice();
		
		payment.setPaymentStatus(PaymentStatus.PENDING);
		payment.setOrder(order);
		payment.setAmount(amount);
		
		return PaymentMapper.toDTO(paymentRepository.save(payment));
	}
	
	public List<PaymentResponseDTO> listAll(long idUser){
		List<Payment> listPayments = new LinkedList<>();
		User user = userRepository.findById(idUser).orElseThrow(
				()->new RuntimeException("usuario não encontrado"));
		
		List<Order> order = orderRepository.findByUser(user);
		for(Order o : order) {
			
			listPayments.add(paymentRepository.findByOrder(o));
		}
		
		List<PaymentResponseDTO> listResponse = listPayments.stream().map(PaymentMapper::toDTO).toList();
		
		return listResponse;
	}
	public PaymentResponseDTO findPayment(long id_order) {
		Order order = orderRepository.findById(id_order).orElseThrow(()->new RuntimeException("Pedido não encontrado"));
		
		return PaymentMapper.toDTO(paymentRepository.findByOrder(order));
	}
}
