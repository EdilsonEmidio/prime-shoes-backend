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
import br.com.primeshoes.api.mappers.CartItemMapper;
import br.com.primeshoes.api.mappers.CartMapper;
import br.com.primeshoes.api.repositories.CartRepository;
import br.com.primeshoes.api.repositories.ProductRepository;
import br.com.primeshoes.api.repositories.UserRepository;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;
    
    public CartResponseDTO store(CartCreateDTO cartCreateDTO) {
	
	Cart cart = CartMapper.toEntity(cartCreateDTO);
	
	cart.setUser(userRepository.findById(cartCreateDTO.user()).orElseThrow(
			()-> new RuntimeException("usuario não encontrado"))
		);
	cartRepository.save(cart);
	return CartMapper.toDTO(cart);
    }
    public CartItemResponseDTO teste(CartItemCreateDTO cartItemCreateDTO) {
	
	return null;
    }
    
    public CartItemResponseDTO addCartItem(CartItemCreateDTO cartItemCreateDTO) {
	
	Cart cart = cartRepository.findById(cartItemCreateDTO.cart())
		.orElseThrow(
			()-> new RuntimeException("Carrinho não encontrado")
		);
	ProductVariation productVariation = productRepository.findProductVariation(cartItemCreateDTO.productVariation())
		.orElseThrow( ()-> new RuntimeException("produto não achado com este id"));
		
	CartItem cartItem = CartItemMapper.toEntity(cartItemCreateDTO);
	cartItem.setCart(cart);
	cartItem.setProcuctVariation(productVariation);
	cartItem.setSubtotal(productRepository.findSubtotal(productVariation.getProduct().getId()));
	
	cartRepository.save(cart);
	return CartItemMapper.toDTO(cartItem);
    }
    
    public void removeCartItem(long idCartItem) {
	CartItem cartItem = cartRepository.findCartItem(idCartItem);
	
	cartRepository.deleteCartItem(cartItem.getId());
    }
    
    public List<CartResponseDTO> list(){
	
	return cartRepository.findAll().stream().map(CartMapper::toDTO).toList();
    }
    public List<CartItemResponseDTO> listItems(long id){
	
	return cartRepository.listAllCartItem(id).stream().map(CartItemMapper::toDTO).toList();
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
