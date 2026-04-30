package med.voll.api.domain.usuario;

import org.hibernate.validator.constraints.br.CPF;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record DadosCadastrarUsuarioDTO(
		@NotBlank
		String login,
		@NotBlank
		String senha,
		@NotBlank
		@CPF
		String cpf,
		@NotBlank
		@Email
		String email,
		@NotBlank
		String nome,
		String perfil
		) {
}
