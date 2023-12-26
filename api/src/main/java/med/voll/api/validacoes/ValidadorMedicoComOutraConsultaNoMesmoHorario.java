package med.voll.api.validacoes;

import med.voll.api.dto.agendamento.DadosAgendamentoConsultaDTO;
import med.voll.api.infra.exception.ValidacaoException;
import med.voll.api.repository.ConsultaRepository;

public class ValidadorMedicoComOutraConsultaNoMesmoHorario {
	
	private ConsultaRepository consultaRepository;
	
	public void validar(DadosAgendamentoConsultaDTO dadosDTO) {
		
		var medicoPossuiOutraConsultaNoMesmoHorario = consultaRepository.existsByMedicoIdAndData(dadosDTO.idMedico(), dadosDTO.data());
		if( medicoPossuiOutraConsultaNoMesmoHorario ) {
			throw new ValidacaoException("Medico já possui outra consulta agendada nesse mesmo horário!");
		}
	}

}
