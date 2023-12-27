package med.voll.api.validacoes.cancelamento;

import med.voll.api.dto.cancelamento.DadosCancelamentoDeConsultaDTO;

public interface ValidadorCancelamentoDeConsulta {
	
	void validar(DadosCancelamentoDeConsultaDTO dadosDTO);

}
