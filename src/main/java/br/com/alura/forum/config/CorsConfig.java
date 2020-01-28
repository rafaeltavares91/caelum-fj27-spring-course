package br.com.alura.forum.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class CorsConfig implements WebMvcConfigurer {
	
	private static final Logger logger = LoggerFactory.getLogger(CorsConfig.class);
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/api/**")
	        .allowedOrigins("http://localhost:3000")
	        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD", "TRACE", "CONNECT");

		logger.info("CORS configurations has been registered");
	}
	
}
