package med.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import med.voll.api.dto.agendamento.DadosAgendamentoConsultaDTO;
import med.voll.api.dto.agendamento.DadosDetalhamentoConsultaDTO;
import med.voll.api.dto.cancelamento.DadosCancelamentoDeConsultaDTO;
import med.voll.api.services.AgendaDeConsultas;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {
	
	@Autowired
	private AgendaDeConsultas agendamentoDeConsultasService;
	
	@PostMapping
	@Transactional
	public ResponseEntity agendar(@RequestBody @Valid DadosAgendamentoConsultaDTO dadosDTO) {
		DadosDetalhamentoConsultaDTO consultaDTO = agendamentoDeConsultasService.agendar(dadosDTO);
		return ResponseEntity.ok(consultaDTO);
	}
	
	@DeleteMapping
	@Transactional
	public ResponseEntity cancelar(@RequestBody @Valid DadosCancelamentoDeConsultaDTO dadosDTO) {
		agendamentoDeConsultasService.cancelar(dadosDTO);
		return ResponseEntity.noContent().build();
	}

}
