package med.voll.api.dto.paciente;

import med.voll.api.entidades.Endereco;
import med.voll.api.entidades.Paciente;

public record DadosDetalhamentoPacienteDTO(
		Long id,
		String nome,
		String email,
		Endereco endereco
		) {
	
	public DadosDetalhamentoPacienteDTO(Paciente paciente) {
		this(
				paciente.getId(),
				paciente.getNome(),
				paciente.getEmail(),
				paciente.getEndereco()
				);
	}

}
