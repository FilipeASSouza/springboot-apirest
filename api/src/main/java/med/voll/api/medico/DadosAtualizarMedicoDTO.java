package med.voll.api.medico;

import jakarta.validation.constraints.NotNull;
import med.voll.api.endereco.DadosEnderecoDTO;

public record DadosAtualizarMedicoDTO(
		@NotNull
		Long id,
		String nome,
		String telefone,
		DadosEnderecoDTO endereco
		) {

}
