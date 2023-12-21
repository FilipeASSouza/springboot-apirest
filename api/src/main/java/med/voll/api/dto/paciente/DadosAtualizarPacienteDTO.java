package med.voll.api.dto.paciente;

import jakarta.validation.constraints.NotNull;
import med.voll.api.dto.endereco.DadosEnderecoDTO;

public record DadosAtualizarPacienteDTO(
		@NotNull
		Long id,
		String nome,
		String telefone,
		DadosEnderecoDTO endereco
		) {

}
