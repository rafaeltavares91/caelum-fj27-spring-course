package br.com.alura.forum.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alura.forum.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByEmail(String email);
	
}
