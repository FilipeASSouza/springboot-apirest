package med.voll.api.springdoc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SpringDocConfigurations {
	
	@Bean
	 public OpenAPI customOpenAPI() {
	   return new OpenAPI()
	          .components(
	        		  new Components()
	        		  .addSecuritySchemes("bearer-key",
	        			new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")))
	          .info(
	        		  new Info()
	        		  .title("Voll.med API")
	        		  .description("API Rest da aplicação Voll Med")
	        		  .contact(new Contact()
	        				  .name("Filipe Augusto")
	        				  .email("teste@teste.com.br"))
	        		  .license(new License()
	        				  .name("Apache 2.0")
	        				  .url("http://voll.med/api/licenca")));
	}

}
