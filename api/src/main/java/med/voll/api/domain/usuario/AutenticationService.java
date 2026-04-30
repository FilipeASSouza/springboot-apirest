package med.voll.api.domain.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import med.voll.api.infra.exception.ValidacaoException;

@Service
public class AutenticationService {
	
	@Autowired
	private UsuariosRepository usuarioRepository;
	
	public void validarUsuario(DadosAutenticacaoDTO dados) throws ValidacaoException {
		
		verificarPerfil(dados);
		
	}

	private void verificarPerfil(DadosAutenticacaoDTO dados) throws ValidacaoException {
		
		Usuarios usuario = usuarioRepository.findByUsuario(dados.email());
		
		if(usuario == null) {
			throw new ValidacaoException("Perfil não cadastrado no sistema!\nFaça o cadastro do usuário!");
		}
		
		if(usuario != null 
				&& usuario.getPerfil() == null ) {
			
			throw new ValidacaoException("Aguarde a liberação do perfil!\nProcure o administrador do sistema!");
		}
		
	}

}
