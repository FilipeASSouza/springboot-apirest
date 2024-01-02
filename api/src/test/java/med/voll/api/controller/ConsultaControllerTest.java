package med.voll.api.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.time.LocalDateTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import med.voll.api.dto.agendamento.DadosAgendamentoConsultaDTO;
import med.voll.api.dto.agendamento.DadosDetalhamentoConsultaDTO;
import med.voll.api.enums.Especialidade;
import med.voll.api.services.AgendaDeConsultas;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class ConsultaControllerTest {
	
	@Autowired
	private MockMvc mvc;//simula requisições http
	
	@Autowired
	private JacksonTester<DadosAgendamentoConsultaDTO> dadosAgendamentoJson;
	
	@Autowired
	private JacksonTester<DadosDetalhamentoConsultaDTO> dadosDetalhamentoJson;
	
	@MockBean
	private AgendaDeConsultas agendasDeConsultas;//quando precisar utilizar esse objeto utilize o mock
	
	@Test
	@DisplayName("Deveria devolver codigo http 400 quando informacoes estao invalidas")
	@WithMockUser//pra simular um usuario esta logado
	public void agendar_cenario01() throws Exception {
		
		var response = mvc.perform(post("/consultas"))//endereco da requisicao
		.andReturn()
		.getResponse();
		
		assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
		
	}
	
	@Test
	@DisplayName("Deveria devolver codigo http 200 quando informacoes estao validas")
	@WithMockUser//pra simular um usuario esta logado
	public void agendar_cenario02() throws Exception {
		
		var data = LocalDateTime.now().plusHours(1);
		var especialidade = Especialidade.CARDIOLOGIA;
		var dadosDetalhamento = new DadosDetalhamentoConsultaDTO(null, 2l, 5l, data);
		
		when(agendasDeConsultas.agendar(any())).thenReturn(dadosDetalhamento);
		
		var response = mvc.perform(post("/consultas")//endereco da requisicao
				.contentType(MediaType.APPLICATION_JSON)
				.content(dadosAgendamentoJson.write(
						new DadosAgendamentoConsultaDTO(2l, 5l, data, especialidade))
						.getJson()))
		.andReturn()
		.getResponse();
		
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		
		var jsonEsperado = dadosDetalhamentoJson.write(dadosDetalhamento).getJson();
		
		assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
		
	}

}
