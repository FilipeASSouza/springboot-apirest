package med.voll.api.domain.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.validation.Valid;

@Service
public class CadastramentoDeUsuario {

	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Autowired
	private UsuariosRepository usuarioRepository;
	
	public DadosDetalhamentoUsuariosDTO cadastramento(@Valid DadosCadastrarUsuarioDTO dados) {

		String senhaBCrypt = encoder.encode(dados.senha());
		
		var novoUsuario = new Usuarios(dados, senhaBCrypt);
		
		var usuarioCriado = usuarioRepository.save(novoUsuario);
		
		DadosDetalhamentoUsuariosDTO dto = new DadosDetalhamentoUsuariosDTO(usuarioCriado);
		
		return dto;
	}

}
