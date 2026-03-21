package med.voll.api.domain.consulta.validacoes.agendamento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DadosAgendamentoConsultaDTO;
import med.voll.api.infra.exception.ValidacaoException;

@Component
public class ValidadorPacienteSemOutraConsultaNoDia implements ValidadorAgendamentoDeConsulta{
	
	@Autowired
	private ConsultaRepository consultaRepository;
	
	public void validar(DadosAgendamentoConsultaDTO dadosDTO) {
		
		var primeiroHorario = dadosDTO.data().withHour(7);
		var ultimoHorario = dadosDTO.data().withHour(18);
		var pacientePossuiOutraConsultaNoDia = consultaRepository.existsByPacienteIdAndDataBetween(dadosDTO.idPaciente(), primeiroHorario, ultimoHorario);
		
		if( pacientePossuiOutraConsultaNoDia ) {
			throw new ValidacaoException("Paciente ja possui uma consulta agendada nesse dia!");
		}
	}

}
