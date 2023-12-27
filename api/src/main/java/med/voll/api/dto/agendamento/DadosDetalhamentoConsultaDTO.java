package med.voll.api.dto.agendamento;

import java.time.LocalDateTime;

import med.voll.api.entidades.Consulta;

public record DadosDetalhamentoConsultaDTO(
		Long id,
		Long idMedico,
		Long idPaciente,
		LocalDateTime data
		) {

	public DadosDetalhamentoConsultaDTO(Consulta consulta) {
		this(consulta.getId(), consulta.getMedico().getId(), consulta.getPaciente().getId(), consulta.getData());
	}

}
