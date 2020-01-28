package br.com.alura.forum.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.alura.forum.model.User;
import br.com.alura.forum.service.UserService;

public class JwtAuthenticationFilter extends OncePerRequestFilter {
	
	private static final String BEARER_TOKEN_PREFIX = "Bearer ";
	
	private TokenManager tokenManager;
	private UserService userService;

	public JwtAuthenticationFilter(TokenManager tokenManager, UserService userService) {
		this.tokenManager = tokenManager;
		this.userService = userService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String token = getTokenFromRequest(request);
		if (tokenManager.isValid(token)) {
			Long userId = tokenManager.getUserIdFromToken(token);
			User user = userService.loadUserByUserId(userId);
			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
					user, null, user.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		filterChain.doFilter(request, response);
	}

	private String getTokenFromRequest(HttpServletRequest request) {
		String bearerToken = request.getHeader("Authorization");
		
		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_TOKEN_PREFIX)) {
			bearerToken = bearerToken.replace(BEARER_TOKEN_PREFIX, "");
		}
		
		return bearerToken;
	}

}
