package med.voll.api.validacoes.cancelamento;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.voll.api.dto.cancelamento.DadosCancelamentoDeConsultaDTO;
import med.voll.api.infra.exception.ValidacaoException;
import med.voll.api.repository.ConsultaRepository;

@Component
public class ValidadorCancelamentoHorarioAntecedencia implements ValidadorCancelamentoDeConsulta {

	@Autowired
	private ConsultaRepository consultaRepository;
	
	@Override
	public void validar(DadosCancelamentoDeConsultaDTO dadosDTO) {
		
		var consulta = consultaRepository.getReferenceById(dadosDTO.idConsulta());
		var agora = LocalDateTime.now();
		var diferencaEmHoras = Duration.between(agora, consulta.getData()).toHours();
		
		if( diferencaEmHoras < 24) {
			throw new ValidacaoException("Consulta somente pode ser cancelada com antecedência mínima de 24h!");
		}

	}

}
