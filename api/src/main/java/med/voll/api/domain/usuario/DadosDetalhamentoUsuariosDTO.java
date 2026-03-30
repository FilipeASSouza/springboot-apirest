package med.voll.api.domain.usuario;

public record DadosDetalhamentoUsuariosDTO(
		String login
		) {

	public DadosDetalhamentoUsuariosDTO(Usuarios usuario) {
		this( usuario.getLogin() );
	}
}
