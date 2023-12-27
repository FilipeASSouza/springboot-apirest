package med.voll.api.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;

import med.voll.api.entidades.Consulta;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

	Boolean existsByMedicoIdAndDataAndMotivoCancelamentoIsNull(Long idMedico, LocalDateTime data);

	Boolean existsByPacienteIdAndDataBetween(Long idPaciente, LocalDateTime primeiroHorario, LocalDateTime ultimoHorario);

}
