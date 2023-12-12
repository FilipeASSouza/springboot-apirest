package med.voll.api.entidades;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.endereco.DadosEnderecoDTO;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {
	
	private String logradouro;
	private String bairro;
	private String cep;
	private String numero;
	private String complemento;
	private String cidade;
	private String uf;
	
	public Endereco(DadosEnderecoDTO origemDTO) {
		
		this.logradouro = origemDTO.logradouro();
		this.bairro = origemDTO.bairro();
		this.cep = origemDTO.cep();
		this.numero = origemDTO.numero();
		this.complemento = origemDTO.complemento();
		this.cidade = origemDTO.cidade();
		this.uf = origemDTO.uf();
		
	}

	public void atualizarInformacoes(DadosEnderecoDTO dados) {
		if(dados.logradouro() != null) {
			this.logradouro = dados.logradouro();
		}
		if(dados.bairro() != null) {
			this.bairro = dados.bairro();
		}
		if(dados.cep() != null) {
			this.cep = dados.cep();
		}
		if(dados.numero() != null) {
			this.numero = dados.numero();
		}
		if(dados.complemento() != null) {
			this.complemento = dados.complemento();
		}
		if(dados.cidade() != null) {
			this.cidade = dados.cidade();
		}
		if(dados.uf() != null) {
			this.uf = dados.uf();
		}
	}
}
