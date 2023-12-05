package med.voll.api.medico;

import med.voll.api.endereco.DadosEnderecoDTO;

public record DadosCadastroMedicoDTO(String nome, String email, String crm, Especialidade especialidade, DadosEnderecoDTO endereco) {

}
