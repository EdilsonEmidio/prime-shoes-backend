package br.com.primeshoes.api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.primeshoes.api.dtos.OrderCreateDTO;
import br.com.primeshoes.api.dtos.OrderItemResponseDTO;
import br.com.primeshoes.api.dtos.OrderResponseDTO;
import br.com.primeshoes.api.entities.Cart;
import br.com.primeshoes.api.entities.CartItem;
import br.com.primeshoes.api.entities.Order;
import br.com.primeshoes.api.entities.OrderItem;
import br.com.primeshoes.api.entities.User;
import br.com.primeshoes.api.enuns.OrderStatus;
import br.com.primeshoes.api.mappers.OrderItemMapper;
import br.com.primeshoes.api.mappers.OrderMapper;
import br.com.primeshoes.api.repositories.CartItemRepository;
import br.com.primeshoes.api.repositories.CartRepository;
import br.com.primeshoes.api.repositories.OrderItemRepository;
import br.com.primeshoes.api.repositories.OrderRepository;
import br.com.primeshoes.api.repositories.UserRepository;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;
    private UserRepository userRepository;
    private CartRepository cartRepository;
    private CartItemRepository itemRepository;
    
    
    public OrderService(CartItemRepository itemRepository, CartRepository cartRepository, UserRepository userRepository) {
		this.cartRepository = cartRepository;
		this.itemRepository = itemRepository;
		this.userRepository = userRepository;
	}
    
    public OrderResponseDTO store(OrderCreateDTO orderCreateDTO) {
		User user = userRepository.findById(orderCreateDTO.user()).orElseThrow(
				()-> new RuntimeException("Usuario não encontrado!")
			);
		Cart cart = cartRepository.findByUser(user).orElseThrow(
				()-> new RuntimeException("Carrinho não encontrado!")
			);
		
		Order order = OrderMapper.toEntity(orderCreateDTO);
		order.setStatus(OrderStatus.PENDING);
		order.setUser(user);
		orderRepository.save(order);
		float totalPrice = 0;
		for(CartItem i : itemRepository.findByCart(cart)) {
		    totalPrice += i.getSubtotal()*i.getQuantity();
		    OrderItem orderItem = OrderItemMapper.toOrderItem(i);
		    orderItem.setOrder(order);
		    orderItemRepository.save(orderItem);
		}
		order.setTotalPrice(totalPrice);
		itemRepository.deleteByCart(cart.getId());
		return OrderMapper.toDTO(orderRepository.save(order));
    }
    
    public List<OrderResponseDTO> list(){
    	return orderRepository.findAll().stream().map(OrderMapper::toDTO).toList();
    }
    
    public List<OrderResponseDTO> listAll(String email){
    	User user = userRepository.findByEmail(email).
    			orElseThrow(()->new RuntimeException("usuario não encontrado: OrderService"));
    	
    	return orderRepository.findByUser(user).stream().map(OrderMapper::toDTO).toList();
    }
    
    public List<OrderItemResponseDTO> listItems(long id) {
	
    	Order order = orderRepository.findById(id).orElseThrow(
    			()-> new RuntimeException("Order não achada"));
    	
    	//List<OrderItem> oi = orderItemRepository.findByOrder(order);
    	//System.out.println(oi.getLast().getProductVariation().getProduct().getImageUrl());
		return orderItemRepository.findByOrder(order).stream().map(OrderItemMapper::toDTO).toList();
	    }
}
