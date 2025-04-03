package br.com.primeshoes.api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.primeshoes.api.dtos.UserCreateDTO;
import br.com.primeshoes.api.dtos.UserResponseDTO;
import br.com.primeshoes.api.dtos.UserUpdateDTO;
import br.com.primeshoes.api.entities.User;
import br.com.primeshoes.api.mappers.UserMapper;
import br.com.primeshoes.api.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public UserResponseDTO store(UserCreateDTO userCreateDTO) {
		
		User user = UserMapper.toEntity(userCreateDTO);
		
		return UserMapper.toDTO(userRepository.save(user));
	}
	
	public List<UserResponseDTO> list(){
	   
		return userRepository.findAll().stream().map(UserMapper::toDTO).toList();
	}
	
	public UserResponseDTO show(long id) {
		User user = userRepository.findById(id).orElseThrow(
			() -> new RuntimeException("Usuario não foi encontrado com este id")
			);
		return UserMapper.toDTO(user);
		
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
