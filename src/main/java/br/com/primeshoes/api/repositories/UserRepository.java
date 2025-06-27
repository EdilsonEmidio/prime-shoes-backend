package br.com.primeshoes.api.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.primeshoes.api.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

    
	@Query(value = "SELECT * FROM users WHERE EMAIL=:email", nativeQuery = true)
    Optional<User> findByEmail(@Param("email") String email);
}
