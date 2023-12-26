package med.voll.api.validacoes;

import java.time.Duration;
import java.time.LocalDateTime;

import med.voll.api.dto.agendamento.DadosAgendamentoConsultaDTO;
import med.voll.api.infra.exception.ValidacaoException;

public class ValidadorHorarioAntecedencia {
	
	public void validar(DadosAgendamentoConsultaDTO dadosDTO) {
		
		var dataConsulta = dadosDTO.data();
		var dataAgora = LocalDateTime.now();
		var diferencaEmMinutos = Duration.between(dataAgora, dataConsulta).toMinutes();
		
		if( diferencaEmMinutos < 30 ) {
			throw new ValidacaoException("Consulta deve ser agendada com antecedencia minima de 30 minutos!");
		}
		
	}

}
