package br.com.primeshoes.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.primeshoes.api.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
