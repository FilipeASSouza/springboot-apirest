package med.voll.api.entidades;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.dto.paciente.DadosAtualizarPacienteDTO;
import med.voll.api.dto.paciente.DadosCadastroPacienteDTO;

@Table(name = "pacientes")
@Entity(name = "Paciente")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Paciente {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String email;
	private String telefone;
	
	@Embedded
	private Endereco endereco;
	
	private Boolean ativo;
	
	public Paciente(DadosCadastroPacienteDTO origemDTO) {
		
		this.nome = origemDTO.nome();
		this.email = origemDTO.email();
		this.telefone = origemDTO.telefone();
		this.endereco = new Endereco(origemDTO.endereco());
		this.ativo = true;
		
	}
	
	public void atualizarInformacoes(@Valid DadosAtualizarPacienteDTO dados) {
		
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
	
	public void inativarPaciente() {
		this.ativo = false;
	}
}
