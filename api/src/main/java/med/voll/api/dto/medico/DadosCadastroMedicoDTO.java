package med.voll.api.dto.medico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.dto.endereco.DadosEnderecoDTO;
import med.voll.api.enums.Especialidade;

public record DadosCadastroMedicoDTO(
		@NotBlank(message = "Nome é um campo obrigátorio.") // verifica se não é nulo e vazio campos string
		String nome, 
		@NotBlank(message = "E-mail é um campo obrigátorio.")
		@Email //valida o formato
		String email,
		@NotBlank(message = "Telefone é um campo obrigátorio.")
		String telefone,
		@NotBlank(message = "Crm é um campo obrigátorio.")
		@Pattern(regexp = "\\d{4,6}")//\\d informa que é digito e de 4 a 6 digitos
		String crm, 
		@NotNull(message = "Especialidade é um campo obrigátorio.")
		Especialidade especialidade, 
		@NotNull(message = "Endereco é obrigátorio.")
		@Valid // valida outro dto dentro desse mesmo record
		DadosEnderecoDTO endereco
		) {

}
