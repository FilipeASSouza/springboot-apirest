package med.voll.api.repository;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

//quando quer testar algo na camada jpa no repository usa essa anotação
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)//esse cenario faz um teste com o banco de dados de verdade
@ActiveProfiles("test")
public class MedicoRepositoryTest {
	
	@Test
	void escolherMedicoAleatorioLivreNaData() {
		
	}

}
