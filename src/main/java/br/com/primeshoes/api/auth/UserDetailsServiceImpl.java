package br.com.primeshoes.api.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.primeshoes.api.entities.User;
import br.com.primeshoes.api.repositories.UserRepository;

	@Service
	public class UserDetailsServiceImpl implements UserDetailsService{

	    private final UserRepository userRepository;
	    
	    public UserDetailsServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
    }
    
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(email).orElseThrow(
			()-> new RuntimeException("Não encontrado usuario com este email"));
		return user;
    }

}
