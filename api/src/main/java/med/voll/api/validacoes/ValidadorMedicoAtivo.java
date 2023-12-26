package med.voll.api.validacoes;

import med.voll.api.dto.agendamento.DadosAgendamentoConsultaDTO;
import med.voll.api.infra.exception.ValidacaoException;
import med.voll.api.repository.MedicoRepository;

public class ValidadorMedicoAtivo {
	
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
