package br.com.primeshoes.api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.primeshoes.api.dtos.ProductCreateDTO;
import br.com.primeshoes.api.dtos.ProductResponseDTO;
import br.com.primeshoes.api.dtos.ProductVariationCreateDTO;
import br.com.primeshoes.api.dtos.ProductVariationResponseDTO;
import br.com.primeshoes.api.entities.Product;
import br.com.primeshoes.api.entities.ProductVariation;
import br.com.primeshoes.api.entities.User;
import br.com.primeshoes.api.enuns.Role;
import br.com.primeshoes.api.mappers.ProductMapper;
import br.com.primeshoes.api.mappers.ProductVariationMapper;
import br.com.primeshoes.api.repositories.ProductRepository;
import br.com.primeshoes.api.repositories.ProductVariationRepository;
import br.com.primeshoes.api.repositories.UserRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private ProductVariationRepository productVariationRepository;
    
    private UserRepository userRepository;
    
    public ProductService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
    
    public ProductResponseDTO store(ProductCreateDTO productCreateDTO) {
    	User user = userRepository.findById(productCreateDTO.user()).orElseThrow(
    			()-> new RuntimeException("usuario não encontrado"));
    
    	if (user.getRole() == Role.BUYER){
    		throw new RuntimeException("Usuario não é comprador!");
    	}
		Product product = ProductMapper.toEntity(productCreateDTO);
		//mexe aqui oh
		//mexe mais nao
		product.setImageUrl(productCreateDTO.image());
		
		product.setUser(user);
		return ProductMapper.toDTO(productRepository.save(product));
    }
    
    public ProductVariationResponseDTO storeProductVariation(ProductVariationCreateDTO productVariationCreateDTO) {
	
		ProductVariation productVariation = ProductVariationMapper.toEntity(productVariationCreateDTO);
		
		Product product = productRepository.findById(productVariationCreateDTO.product()).orElseThrow(
			()-> new RuntimeException("Producto não encontrado")
			);
			
		productVariation.setProduct(product);
		
		productVariationRepository.save(productVariation);
		
		return ProductVariationMapper.toDTO(productVariation);
    }
    
    public List<ProductResponseDTO> list(){
    	return productRepository.findAll().stream().map(ProductMapper::toDTO).toList();
    }
    
    public List<ProductResponseDTO> listUser(long id){
    	User user = userRepository.findById(id).orElseThrow(
    			()-> new RuntimeException("usuario não encontrado: product service"));
    	return productRepository.findByUser(user).stream().map(ProductMapper::toDTO).toList();
    }
    
    
    public List<ProductVariationResponseDTO> listVariations(long id){
    	Product product = productRepository.findById(id).orElseThrow(
    			()-> new RuntimeException("não achado produto"));
    	return productVariationRepository.findByProduct(product).stream().map(ProductVariationMapper::toDTO).toList();
    }
    
    public ProductResponseDTO show(long id) {
    	return ProductMapper.toDTO(productRepository.findById(id).orElseThrow(
    			()-> new RuntimeException("Pedido não encontrado com este id")));
    }
    
    public void destroy(long id) {
		Product product = productRepository.findById(id).orElseThrow(
			()-> new RuntimeException("Produto não encontrado com este id"));
		productRepository.delete(product);
    }
    
    public void destroyVariant(long id) {
    	ProductVariation pv = productVariationRepository.findById(id).orElseThrow(
    			()-> new RuntimeException("VARIAÇÃO NÃO ENCONTRADA"));
		productVariationRepository.delete(pv);
    }
}
