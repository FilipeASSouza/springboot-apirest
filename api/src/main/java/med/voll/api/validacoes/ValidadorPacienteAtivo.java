package med.voll.api.validacoes;

import med.voll.api.dto.agendamento.DadosAgendamentoConsultaDTO;
import med.voll.api.infra.exception.ValidacaoException;
import med.voll.api.repository.PacienteRepository;

public class ValidadorPacienteAtivo {
	
	private PacienteRepository pacienteRepository;
	
	public void validar(DadosAgendamentoConsultaDTO dadosDTO) {
		
		var pacienteEstaAtivo = pacienteRepository.findByAtivoId(dadosDTO.idPaciente());
		if( !pacienteEstaAtivo ) {
			throw new ValidacaoException("Consulta não pode ser agendada com paciente inativo!");
		}
	}

}
