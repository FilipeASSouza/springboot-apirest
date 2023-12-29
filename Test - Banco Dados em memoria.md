## Para saber mais: testes com in-memory database

PRÓXIMA ATIVIDADE

Como citado no vídeo anterior, podemos realizar os testes de interfaces repository utilizando um banco de dados em memória, como o H2, ao invés de utilizar o mesmo banco de dados da aplicação.

Caso você queira utilizar essa estratégia de executar os testes com um banco de dados em memória, será necessário incluir o H2 no projeto, adicionando a seguinte dependência no arquivo pom.xml:

<dependency>
  <groupId>com.h2database</groupId>
  <artifactId>h2</artifactId>
  <scope>runtime</scope>
</dependency>COPIAR CÓDIGO
E também deve remover as anotações @AutoConfigureTestDatabase e @ActiveProfiles na classe de teste, deixando-a apenas com a anotação @DataJpaTest:

@DataJpaTest
class MedicoRepositoryTest {

  //resto do código permanece igual

}COPIAR CÓDIGO
Você também pode apagar o arquivo application-test.properties, pois o Spring Boot realiza as configurações de url, username e password do banco de dados H2 de maneira automática.