package br.com.primeshoes.api.services;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.primeshoes.api.dtos.AddressCreateDTO;
import br.com.primeshoes.api.dtos.AddressResponseDTO;
import br.com.primeshoes.api.entities.Address;
import br.com.primeshoes.api.entities.User;
import br.com.primeshoes.api.mappers.AddressMapper;
import br.com.primeshoes.api.repositories.AddressRepository;
import br.com.primeshoes.api.repositories.UserRepository;

@Service
public class AddressService {

	@Autowired
	private AddressRepository addressRepository; 
	
	private UserRepository userRepository;
	
	public AddressService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public AddressResponseDTO findByUser(String email) {
		
		User user = userRepository.findByEmail(email).orElseThrow(
				()-> new RuntimeException("usuario não encontrado"));
		
		Address address = addressRepository.findByUser(user).orElseThrow(
				()-> new RuntimeException("endereço não encontrado"));
		
		return AddressMapper.toDTO(address);		
	}
	
	public AddressResponseDTO findByUser(User user) {
		Address address = addressRepository.findByUser(user).orElseThrow(
				()-> new RuntimeException("endereço não encontrado"));
		
		return AddressMapper.toDTO(address);		
	}
	
	public AddressResponseDTO update(AddressCreateDTO addressCreateDTO) {
		
		User user = userRepository.findById(addressCreateDTO.user()).orElseThrow(()-> new RuntimeException("Usuario não encontrado"));
		
		Address address = addressRepository.findByUser(user).
				orElseThrow(()->new RuntimeException("endereço não encontrado: update address"));
		
		address.setCity(addressCreateDTO.city());
		address.setComplement(addressCreateDTO.complement());
		address.setNeighborhood(addressCreateDTO.neighborhood());
		address.setNumber(addressCreateDTO.number());
		address.setState(addressCreateDTO.state());
		address.setStreet(addressCreateDTO.street());
		address.setZipcode(addressCreateDTO.zipcode());

		addressRepository.save(address);
		
		return AddressMapper.toDTO(address);
	}
}
