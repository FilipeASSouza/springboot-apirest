package med.voll.api.dto.agendamento;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

public record DadosAgendamentoConsultaDTO(
		Long idMedico,
		@NotNull
		Long idPaciente,
		@NotNull
		@Future
		LocalDateTime data
		) {

}
