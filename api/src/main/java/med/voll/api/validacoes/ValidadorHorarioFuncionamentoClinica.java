package med.voll.api.validacoes;

import java.time.DayOfWeek;

import med.voll.api.dto.agendamento.DadosAgendamentoConsultaDTO;
import med.voll.api.infra.exception.ValidacaoException;

public class ValidadorHorarioFuncionamentoClinica {
	
	public void validar(DadosAgendamentoConsultaDTO dadosDTO) {
		
		var dataConsulta = dadosDTO.data();
		var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
		var antesDaAberturaDaClinica = dataConsulta.getHour() < 7;
		var depoisDoEncerramentoDaClinica = dataConsulta.getHour() > 18;
		
		if( domingo || antesDaAberturaDaClinica || depoisDoEncerramentoDaClinica ) {
			throw new ValidacaoException("Consulta fora do horario de funcionamento da clinica!");
		}
		
	}

}
