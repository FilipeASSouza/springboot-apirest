package med.voll.api.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.enums.Especialidade;

public record DadosCadastroMedicoDTO(
		@NotBlank // verifica se não é nulo e vazio campos string
		String nome, 
		@NotBlank
		@Email //valida o formato
		String email,
		@NotBlank
		String telefone,
		@NotBlank
		@Pattern(regexp = "\\d{4,6}")//\\d informa que é digito e de 4 a 6 digitos
		String crm, 
		@NotNull
		Especialidade especialidade, 
		@NotNull
		@Valid // valida outro dto dentro desse mesmo record
		DadosEnderecoDTO endereco
		) {

}
