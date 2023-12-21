package med.voll.api.dto.endereco;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DadosEnderecoDTO(
		@NotBlank(message = "Logradouro é um campo obrigátorio.")
		String logradouro, 
		@NotBlank(message = "Bairro é um campo obrigátorio.")
		String bairro, 
		@NotBlank(message = "Cep é um campo obrigátorio.")
		@Pattern(regexp = "\\d{8}")
		String cep, 
		@NotBlank(message = "Cidade é um campo obrigátorio.")
		String cidade, 
		@NotBlank(message = "UF é um campo obrigátorio.")
		String uf, 
		String complemento, 
		String numero
		) {

}
