package br.com.alura.forum.config;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import br.com.alura.forum.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class TokenManager {

	@Value("${alura.forum.jwt.secret}")
	private String secret;
	
	@Value("${alura.forum.jwt.expirationTime}")
	private long expirationIsMillis;
	
	public String generateToken(Authentication authentication) {
		User user = (User) authentication.getPrincipal();
		Date today = new Date();
		return Jwts.builder()
				.setIssuer("Alura forum API")
				.setSubject(String.valueOf(user.getId()))
				.setIssuedAt(today)
				.setExpiration(new Date(today.getTime() + expirationIsMillis))
				.signWith(SignatureAlgorithm.HS256, this.secret)
				.compact();
	}
	
	public boolean isValid(String token) {
		try {
			Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
			return true;
		} catch (JwtException | IllegalArgumentException e) {
			return false;
		}
	}
	
	public Long getUserIdFromToken(String token) {
		Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
		String userId = claims.getSubject();
		return Long.parseLong(userId);
	}
	
}
