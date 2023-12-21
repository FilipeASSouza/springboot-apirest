package med.voll.api.dto.paciente;

import med.voll.api.entidades.Paciente;

public record DadosListagemPacienteDTO(
		Long id,
		String nome, 
		String email
		) {
	
	public DadosListagemPacienteDTO(Paciente paciente) {
		this(
				paciente.getId(),
				paciente.getNome(),
				paciente.getEmail()
				);
	}

}
