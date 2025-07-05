package br.com.primeshoes.api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.primeshoes.api.dtos.CartCreateDTO;
import br.com.primeshoes.api.dtos.CartItemCreateDTO;
import br.com.primeshoes.api.dtos.CartItemResponseDTO;
import br.com.primeshoes.api.dtos.CartResponseDTO;
import br.com.primeshoes.api.entities.Cart;
import br.com.primeshoes.api.entities.CartItem;
import br.com.primeshoes.api.entities.ProductVariation;
import br.com.primeshoes.api.entities.User;
import br.com.primeshoes.api.mappers.CartItemMapper;
import br.com.primeshoes.api.mappers.CartMapper;
import br.com.primeshoes.api.repositories.CartItemRepository;
import br.com.primeshoes.api.repositories.CartRepository;
import br.com.primeshoes.api.repositories.ProductRepository;
import br.com.primeshoes.api.repositories.ProductVariationRepository;
import br.com.primeshoes.api.repositories.UserRepository;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;
        
    private UserRepository userRepository;
    
    private ProductVariationRepository variationRepository;
    private CartItemRepository itemRepository;
    
    public CartService(ProductVariationRepository variationRepository, UserRepository userRepository, CartItemRepository itemRepository) {
    	this.variationRepository = variationRepository;
    	this.userRepository = userRepository;
    	this.itemRepository = itemRepository;
    }
    
    public CartResponseDTO store(CartCreateDTO cartCreateDTO) {
	
    	Cart cart = CartMapper.toEntity(cartCreateDTO);
    	
		cart.setUser(userRepository.findById(cartCreateDTO.user()).orElseThrow(
				()-> new RuntimeException("usuario não encontrado"))
			);
		cartRepository.save(cart);
		return CartMapper.toDTO(cart);
    }
    public void existe(long id) {
    	User user = userRepository.findById(id).orElseThrow(()->new RuntimeException("Usuario não encontrado"));
    	
    	Cart cart = cartRepository.findByUser(user).orElseThrow(()->new RuntimeException("ERRO!!"));
 
    }
    
    public CartItemResponseDTO addCartItem(CartItemCreateDTO cartItemCreateDTO) {
    	
    	User user = userRepository.findById(cartItemCreateDTO.user()).orElseThrow(
    			()->new RuntimeException("não achado"));
    	
		Cart cart = cartRepository.findByUser(user)
			.orElseThrow(
				()-> new RuntimeException("Carrinho não encontrado")
			);
		ProductVariation productVariation = variationRepository.findById(cartItemCreateDTO.productVariation())
			.orElseThrow( ()-> new RuntimeException("produto não achado com este id"));
		
		List<CartItem> listItems = itemRepository.findByCart(cart);
		
		CartItem cartItem = null;
		boolean flag = false;
		for(CartItem ci : listItems) {
			if(ci.getProcuctVariation().getId() == cartItemCreateDTO.productVariation()) {
				flag = true;
				cartItem = ci;
				cartItem.setQuantity(cartItem.getQuantity()+1);
			}
		}
		if(flag == false) {
			cartItem = CartItemMapper.toEntity(cartItemCreateDTO);
			cartItem.setCart(cart);
			cartItem.setProcuctVariation(productVariation);
			cartItem.setSubtotal(productVariation.getProduct().getPrice());
		}
		
		
		itemRepository.save(cartItem);
		return CartItemMapper.toDTO(cartItem);
    }
    
    public void removeCartItem(long idCartItem) {
		CartItem cartItem = itemRepository.findById(idCartItem).orElseThrow(
				()-> new RuntimeException("Carrinho não encontrado"));
		
		itemRepository.delete(cartItem);
    }
    
    public List<CartResponseDTO> list(){
	
    	return cartRepository.findAll().stream().map(CartMapper::toDTO).toList();
    }
    
    public List<CartItemResponseDTO> listItemsCart(long id){
    	User user = userRepository.findById(id).orElseThrow(()->new RuntimeException("usuario não achado"));
    	Cart cart = cartRepository.findByUser(user).orElseThrow(()->new RuntimeException("carrinho não achado"));
    	
    	return itemRepository.findByCart(cart).stream().map(CartItemMapper::toDTO).toList();
    }
    
    public CartResponseDTO show(long id) {
		
		return CartMapper.toDTO(cartRepository.findById(id).orElseThrow(
			()-> new RuntimeException("Usuario não encontrado com este id")
			));
    }
    
    public void destroy(long id) {
		Cart cart = cartRepository.findById(id).orElseThrow(
			()-> new RuntimeException("Usuario não encontrado com este id")
			);
		cartRepository.delete(cart);
    }
}
