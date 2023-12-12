package med.voll.api.dto;

import med.voll.api.entidades.Endereco;
import med.voll.api.entidades.Medico;
import med.voll.api.enums.Especialidade;

public record DadosDetalhamentoMedicoDTO(
		Long id,
		String nome,
		String email,
		String crm,
		Especialidade especialidade,
		Endereco endereco
		) {
	
	public DadosDetalhamentoMedicoDTO(Medico medico) {
		this(
				medico.getId(),
				medico.getNome(),
				medico.getEmail(),
				medico.getCrm(),
				medico.getEspecialidade(),
				medico.getEndereco()
				);
	}

}
