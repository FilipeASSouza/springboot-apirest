package med.voll.api.dto.paciente;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import med.voll.api.dto.endereco.DadosEnderecoDTO;

public record DadosCadastroPacienteDTO(
		@NotBlank(message = "Nome é um campo obrigátorio.") // verifica se não é nulo e vazio campos string
		String nome, 
		@NotBlank(message = "E-mail é um campo obrigátorio.")
		@Email //valida o formato
		String email,
		@NotBlank(message = "Telefone é um campo obrigátorio.")
		String telefone, 
		@NotNull(message = "Endereco é obrigátorio.")
		@Valid // valida outro dto dentro desse mesmo record
		DadosEnderecoDTO endereco
		) {

}
