package med.voll.api.validacoes;

import med.voll.api.dto.agendamento.DadosAgendamentoConsultaDTO;

public interface ValidadorAgendamentoDeConsulta {
	
	void validar(DadosAgendamentoConsultaDTO dadosDTO);

}
