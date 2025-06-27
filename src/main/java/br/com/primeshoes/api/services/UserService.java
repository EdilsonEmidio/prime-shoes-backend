package br.com.primeshoes.api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.primeshoes.api.dtos.UserCreateDTO;
import br.com.primeshoes.api.dtos.UserResponseDTO;
import br.com.primeshoes.api.dtos.UserUpdateDTO;
import br.com.primeshoes.api.entities.Address;
import br.com.primeshoes.api.entities.User;
import br.com.primeshoes.api.enuns.Role;
import br.com.primeshoes.api.mappers.UserMapper;
import br.com.primeshoes.api.repositories.AddressRepository;
import br.com.primeshoes.api.repositories.UserRepository;

@Service
public class UserService {


	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AddressRepository addressRepository;
	
	public UserResponseDTO store(UserCreateDTO userCreateDTO) {
		
		User user = UserMapper.toEntity(userCreateDTO);

		user.setRole(Role.BUYER);
		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		User userResponse = userRepository.save(user);
	
		Address address = new Address();
		address.setUser(userResponse);
		addressRepository.save(address);
		
		return UserMapper.toDTO(userRepository.save(userResponse));
	}
	
	public List<UserResponseDTO> list(){
	   
		return userRepository.findAll().stream().map(UserMapper::toDTO).toList();
	}
	
	public UserResponseDTO find(long id) {
		User user = userRepository.findById(id).orElseThrow(
			() -> new RuntimeException("Usuario não foi encontrado com este id")
			);
		return UserMapper.toDTO(user);
	}
	public User findByEmail(String email) {
		User user = userRepository.findByEmail(email).orElseThrow(
			() -> new RuntimeException("Usuario não foi encontrado com este email")
			);
		return user;
	}
	public UserResponseDTO update(UserUpdateDTO userUpdateDTO) {
			
		User user = userRepository.findById(userUpdateDTO.id()).orElseThrow( 
			() -> new RuntimeException("usuario não encontrado")
			);
			
		user.setName(userUpdateDTO.name());
		user.setEmail(userUpdateDTO.email());
		user.setRole(userUpdateDTO.role());
		user.setPassword(userUpdateDTO.password());
		return UserMapper.toDTO(user);
	}
	
	public void destroy(long id) {
		
		User user = userRepository.findById(id).orElseThrow( 
			() -> new RuntimeException("usuario não encontrado com este id")
			);

		userRepository.delete(user);
	}
}
