package br.com.alura.forum.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.alura.forum.model.User;
import br.com.alura.forum.repository.UserRepository;

@Service
public class UserService implements UserDetailsService{

	private UserRepository userRepository;
	
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		return userRepository
				.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException(
						new StringBuilder()
						.append("O usuario ")
						.append(email)
						.append(" não foi encontrado.")
						.toString()));
	}

	public User loadUserByUserId(Long userId) {
		return userRepository
				.findById(userId)
				.orElseThrow(() -> new UsernameNotFoundException(
						new StringBuilder()
						.append("O usuario ")
						.append(userId)
						.append(" não foi encontrado.")
						.toString()));
	}

}
