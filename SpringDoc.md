Link: https://springdoc.org/

## Getting Started

For the integration between spring-boot and swagger-ui, add the library to the list of your project dependencies (No additional configuration is needed)

   <dependency>
      <groupId>org.springdoc</groupId>
      <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
      <version>2.3.0</version>
   </dependency>

This will automatically deploy swagger-ui to a spring-boot application:

Documentation will be available in HTML format, using the official swagger-ui jars

The Swagger UI page will then be available at http://server:port/context-path/swagger-ui.html and the OpenAPI description will be available at the following url for json format: http://server:port/context-path/v3/api-docs

server: The server name or IP

port: The server port

context-path: The context path of the application

Documentation can be available in yaml format as well, on the following path : /v3/api-docs.yaml

## Para saber mais: personalizando a documentação

PRÓXIMA ATIVIDADE

Vimos no vídeo anterior que é possível personalizar a documentação gerada pelo SpringDoc para a inclusão do token de autenticação. Além do token, podemos incluir outras informações na documentação que fazem parte da especificação OpenAPI, como, por exemplo, a descrição da API, informações de contato e de sua licença de uso.

Tais configurações devem ser feitas no objeto OpenAPI, que foi configurado na classe SpringDocConfigurations de nosso projeto:

@Bean
public OpenAPI customOpenAPI() {
    return new OpenAPI()
            .components(new Components()
                    .addSecuritySchemes("bearer-key",
                            new SecurityScheme()
                                    .type(SecurityScheme.Type.HTTP)
                                    .scheme("bearer")
                                    .bearerFormat("JWT")))
                    .info(new Info()
                            .title("Voll.med API")
                            .description("API Rest da aplicação Voll.med, contendo as funcionalidades de CRUD de médicos e de pacientes, além de agendamento e cancelamento de consultas")
                            .contact(new Contact()
                                    .name("Time Backend")
                                    .email("backend@voll.med"))
                    .license(new License()
                            .name("Apache 2.0")
                            .url("http://voll.med/api/licenca")));
}COPIAR CÓDIGO
Usando os imports:

import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;COPIAR CÓDIGO
No código anterior, repare que após a configuração do token JWT foram adicionadas as informações da API. Ao entrar novamente na página do Swagger UI, tais informações serão exibidas, conforme demonstrado na imagem a seguir:

alt text: Página do Swagger Ui exibindo as informações da Voll.med API, onde se lê a mensagem “API Rest da aplicação Voll.med, contendo as funcionalidades de CRUD de médicos e de pacientes, além de agendamento e cancelamento de consultas.”

Para saber mais detalhes sobre quais informações podem ser configuradas na documentação da API, consulte a especificação OpenAPI no site oficial da iniciativa.

## Mais especificações no link abaixo

https://spec.openapis.org/oas/latest.html#schema