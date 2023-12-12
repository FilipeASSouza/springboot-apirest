package med.voll.api.entidades;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.medico.DadosAtualizarMedicoDTO;
import med.voll.api.medico.DadosCadastroMedicoDTO;
import med.voll.api.medico.Especialidade;

@Table(name = "medicos")
@Entity(name = "Medico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {
	

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String email;
	private String telefone;
	private String crm;
	
	@Enumerated(EnumType.STRING)
	private Especialidade especialidade;
	
	@Embedded
	private Endereco endereco;

	public Medico(DadosCadastroMedicoDTO origemDTO) {
		
		this.nome = origemDTO.nome();
		this.email = origemDTO.email();
		this.telefone = origemDTO.telefone();
		this.crm = origemDTO.crm();
		this.endereco = new Endereco(origemDTO.endereco());
		this.especialidade = origemDTO.especialidade();
		
	}

	public void atualizarInformacoes(@Valid DadosAtualizarMedicoDTO dados) {
		
		if(dados.nome() != null) {			
			this.nome = dados.nome();
		}
		
		if(dados.telefone() != null) {
			this.telefone = dados.telefone();
		}
		
		if(dados.endereco() != null) {
			this.endereco.atualizarInformacoes(dados.endereco());
		}
	}
}
