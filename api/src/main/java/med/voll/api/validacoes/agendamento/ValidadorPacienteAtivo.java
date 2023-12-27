package med.voll.api.validacoes.agendamento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.voll.api.dto.agendamento.DadosAgendamentoConsultaDTO;
import med.voll.api.infra.exception.ValidacaoException;
import med.voll.api.repository.PacienteRepository;

@Component
public class ValidadorPacienteAtivo implements ValidadorAgendamentoDeConsulta{
	
	@Autowired
	private PacienteRepository pacienteRepository;
	
	public void validar(DadosAgendamentoConsultaDTO dadosDTO) {
		
		var pacienteEstaAtivo = pacienteRepository.findByAtivoId(dadosDTO.idPaciente());
		if( !pacienteEstaAtivo ) {
			throw new ValidacaoException("Consulta não pode ser agendada com paciente inativo!");
		}
	}

}
