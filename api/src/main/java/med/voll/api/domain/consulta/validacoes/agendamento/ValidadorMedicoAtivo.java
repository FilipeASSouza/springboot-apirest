package med.voll.api.domain.consulta.validacoes.agendamento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.voll.api.domain.consulta.DadosAgendamentoConsultaDTO;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.infra.exception.ValidacaoException;

@Component
public class ValidadorMedicoAtivo implements ValidadorAgendamentoDeConsulta{
	
	@Autowired
	private MedicoRepository medicoRepository;
	
	public void validar(DadosAgendamentoConsultaDTO dadosDTO) {
		
		if( dadosDTO.idMedico() == null ) {
			return;
		}
		
		var medicoEstaAtivo = medicoRepository.findAtivoById(dadosDTO.idMedico());
		if(!medicoEstaAtivo) {
			throw new ValidacaoException("Consulta não pode ser agendada com médico inativo!");
		}
	}

}
