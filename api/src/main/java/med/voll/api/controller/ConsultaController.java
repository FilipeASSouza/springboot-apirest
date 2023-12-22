package med.voll.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import med.voll.api.dto.agendamento.DadosAgendamentoConsultaDTO;
import med.voll.api.dto.agendamento.DadosDetalhamentoConsultaDTO;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {
	
	@PostMapping
	@Transactional
	public ResponseEntity agendar(@RequestBody @Valid DadosAgendamentoConsultaDTO dadosDTO) {
		return ResponseEntity.ok(new DadosDetalhamentoConsultaDTO(null, null, null, null));
	}

}
