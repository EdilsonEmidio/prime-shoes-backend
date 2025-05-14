package br.com.primeshoes.api.services;

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
	@Autowired
	private UserRepository userRepository;
	
	public AddressResponseDTO update(AddressCreateDTO addressCreateDTO) {
		
		User user = userRepository.findById(addressCreateDTO.user()).orElseThrow(()-> new RuntimeException("Usuario não encontrado"));
		
		Address address = AddressMapper.toEntity(addressCreateDTO);		
		address.setUser(user);
		
		addressRepository.save(address);
		
		return AddressMapper.toDTO(address);
	}
}
