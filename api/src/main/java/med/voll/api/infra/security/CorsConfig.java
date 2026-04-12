package med.voll.api.infra.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@Profile("corsvollmed")
public class CorsConfig {
	
	List<String> uri = new ArrayList<>();
	
	@Bean
	public CorsFilter corsFilter() {
		
		uri.add("http://localhost:4200");
		//uri.add("https://springboot-apirest.onrender.com");
		
		CorsConfiguration config = new CorsConfiguration();
		
		config.setAllowCredentials(true);
		config.setAllowedOrigins(uri);
		config.setAllowedHeaders(List.of("*"));
		config.setAllowedMethods(List.of("*"));
		
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", config);
		
		return new CorsFilter(source);
	}

}
