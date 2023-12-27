package med.voll.api.validacoes.agendamento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.voll.api.dto.agendamento.DadosAgendamentoConsultaDTO;
import med.voll.api.infra.exception.ValidacaoException;
import med.voll.api.repository.ConsultaRepository;

@Component
public class ValidadorMedicoComOutraConsultaNoMesmoHorario implements ValidadorAgendamentoDeConsulta{
	
	@Autowired
	private ConsultaRepository consultaRepository;
	
	public void validar(DadosAgendamentoConsultaDTO dadosDTO) {
		
		var medicoPossuiOutraConsultaNoMesmoHorario = consultaRepository.existsByMedicoIdAndDataAndMotivoCancelamentoIsNull(dadosDTO.idMedico(), dadosDTO.data());
		if( medicoPossuiOutraConsultaNoMesmoHorario ) {
			throw new ValidacaoException("Medico já possui outra consulta agendada nesse mesmo horário!");
		}
	}

}
