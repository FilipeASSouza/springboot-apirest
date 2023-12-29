package med.voll.api.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import med.voll.api.dto.endereco.DadosEnderecoDTO;
import med.voll.api.dto.medico.DadosCadastroMedicoDTO;
import med.voll.api.dto.paciente.DadosCadastroPacienteDTO;
import med.voll.api.entidades.Consulta;
import med.voll.api.entidades.Medico;
import med.voll.api.entidades.Paciente;
import med.voll.api.enums.Especialidade;

//quando quer testar algo na camada jpa no repository usa essa anotação
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)//esse cenario faz um teste com o banco de dados de verdade
@ActiveProfiles("test")
public class MedicoRepositoryTest {
	
	@Autowired
	private MedicoRepository medicoRepository;
	
	@Autowired
	private TestEntityManager em;
	
	@Test
	@DisplayName("Deveria devolver null quando unico medico cadastrado nao esta disponivel na data")
	void escolherMedicoAleatorioLivreNaData_Cenario01() {
		
		var proximaSegundaAsDez = LocalDate.now()
				.with(TemporalAdjusters
				.next(DayOfWeek.MONDAY))
				.atTime(10, 0);
		
		var medico = cadastrarMedico("medico", "medico@teste.com.br", "123456", Especialidade.CARDIOLOGIA);
		var paciente = cadastrarPaciente("paciente", "paciente@teste.com.br", "0000000000");
		cadastrarConsulta(medico, paciente, proximaSegundaAsDez);
		
		Medico retorno = medicoRepository.escolherMedicoAleatorioLivreNaData(Especialidade.CARDIOLOGIA, proximaSegundaAsDez);
		assertThat(retorno).isNull();
		
	}
	
	@Test
	@DisplayName("Deveria devolver medico quando ele estiver disponivel na data")
	void escolherMedicoAleatorioLivreNaData_Cenario02() {
		//giver ou arrange
		var proximaSegundaAsDez = LocalDate.now()
				.with(TemporalAdjusters
				.next(DayOfWeek.MONDAY))
				.atTime(10, 0);
		//when ou act
		Medico medico = cadastrarMedico("medico", "medico@teste.com.br", "123456", Especialidade.CARDIOLOGIA);
		//then ou assert
		Medico retorno = medicoRepository.escolherMedicoAleatorioLivreNaData(Especialidade.CARDIOLOGIA, proximaSegundaAsDez);
		assertThat(retorno).isEqualTo(medico);
		
	}
	
	private void cadastrarConsulta(Medico medico, Paciente paciente, LocalDateTime data) {
		em.persist(new Consulta(null, medico, paciente, data, null));
	}
	
	private Medico cadastrarMedico(String nome, String email, String crm, Especialidade especialidade) {
		var medico = new Medico(dadosMedicoDTO(nome, email, crm, especialidade));
		em.persist(medico);
		return medico;
	}
	
	private Paciente cadastrarPaciente(String nome, String email, String cpf) {
		var paciente = new Paciente(dadosPacienteDTO(nome, email, cpf));
		em.persist(paciente);
		return paciente;
	}
	
	private DadosCadastroMedicoDTO dadosMedicoDTO(String nome, String email, String crm, Especialidade especialidade) {
		
		return new DadosCadastroMedicoDTO(
				nome,
	            email,
	            "61999999999",
	            crm,
	            especialidade,
	            dadosEnderecoDTO()
				);
	}
	
	private DadosCadastroPacienteDTO dadosPacienteDTO(String nome, String email, String cpf) {
		return new DadosCadastroPacienteDTO(
				nome,
	            email,
	            "61999999999",
	            dadosEnderecoDTO()
				);
	}
	
	private DadosEnderecoDTO dadosEnderecoDTO() {
		return new DadosEnderecoDTO(
				"rua xpto",
	            "bairro",
	            "00000000",
	            "Brasilia",
	            "DF",
	            null,
	            null
				);
	}

}
