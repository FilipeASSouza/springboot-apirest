package med.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import med.voll.api.dto.agendamento.DadosAgendamentoConsultaDTO;
import med.voll.api.dto.agendamento.DadosDetalhamentoConsultaDTO;
import med.voll.api.regras.AgendaDeConsultas;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {
	
	@Autowired
	private AgendaDeConsultas regrasAgendamentoDeConsultas;
	
	@PostMapping
	@Transactional
	public ResponseEntity agendar(@RequestBody @Valid DadosAgendamentoConsultaDTO dadosDTO) {
		regrasAgendamentoDeConsultas.agendar(dadosDTO);
		return ResponseEntity.ok(new DadosDetalhamentoConsultaDTO(null, null, null, null));
	}

}
