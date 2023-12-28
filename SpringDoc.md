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