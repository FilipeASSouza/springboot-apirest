package med.voll.api.domain.consulta.validacoes.cancelamento;

import med.voll.api.domain.consulta.DadosCancelamentoDeConsultaDTO;

public interface ValidadorCancelamentoDeConsulta {
	
	void validar(DadosCancelamentoDeConsultaDTO dadosDTO);

}
