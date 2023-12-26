package med.voll.api.dto.cancelamento;

import jakarta.validation.constraints.NotNull;
import med.voll.api.enums.MotivoCancelamento;

public record DadosCancelamentoDeConsultaDTO(
		@NotNull
		Long idConsulta,
		@NotNull
		MotivoCancelamento motivo
		) {

}
