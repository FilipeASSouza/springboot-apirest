package med.voll.api.domain.paciente;

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
