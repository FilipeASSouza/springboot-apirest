package med.voll.api.domain.paciente;

import med.voll.api.domain.endereco.Endereco;

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
