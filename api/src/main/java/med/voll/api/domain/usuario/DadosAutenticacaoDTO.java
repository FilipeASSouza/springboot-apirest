package med.voll.api.domain.usuario;

import jakarta.validation.constraints.NotBlank;

public record DadosAutenticacaoDTO(
		@NotBlank
		String email,
		@NotBlank
		String senha
		) {

}
