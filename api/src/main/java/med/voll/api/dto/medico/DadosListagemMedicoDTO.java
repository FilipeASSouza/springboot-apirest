package med.voll.api.dto.medico;

import med.voll.api.entidades.Medico;
import med.voll.api.enums.Especialidade;

public record DadosListagemMedicoDTO(
		Long id,
		String nome, 
		String email, 
		String crm, 
		Especialidade especialidade
		) {
	
	public DadosListagemMedicoDTO(Medico medico) {
		
		this(
				medico.getId(),
				medico.getNome(), 
				medico.getEmail(),
				medico.getCrm(),
				medico.getEspecialidade()
				);
	}

}
