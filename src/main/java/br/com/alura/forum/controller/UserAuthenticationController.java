package br.com.alura.forum.controller;

import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.forum.config.TokenManager;
import br.com.alura.forum.dto.AuthenticationTokenOutputDto;
import br.com.alura.forum.dto.LoginInputDto;

@RestController
@RequestMapping("api/auth")
public class UserAuthenticationController {

	private AuthenticationManager authenticationManager;
	private TokenManager tokenManager;

	public UserAuthenticationController(AuthenticationManager authenticationManager,
			TokenManager tokenManager) {
		this.authenticationManager = authenticationManager;
		this.tokenManager = tokenManager;
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public AuthenticationTokenOutputDto authenticate(@RequestBody LoginInputDto loginDto) {
		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
				loginDto.getEmail(), loginDto.getPassword());
		Authentication authentication = authenticationManager.authenticate(authToken);
		String token = tokenManager.generateToken(authentication);
		return new AuthenticationTokenOutputDto("Bearer", token);
	}
	
}
