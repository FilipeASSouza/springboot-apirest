package med.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import med.voll.api.domain.usuario.CadastramentoDeUsuario;
import med.voll.api.domain.usuario.DadosAutenticacaoDTO;
import med.voll.api.domain.usuario.DadosCadastrarUsuarioDTO;
import med.voll.api.domain.usuario.Usuarios;
import med.voll.api.domain.usuario.UsuariosRepository;
import med.voll.api.infra.security.DadosTokenJWT;
import med.voll.api.infra.security.TokenService;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

	@Autowired
	private AuthenticationManager manager;
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private CadastramentoDeUsuario cadastramentoDeUsuarioService;
	
	@Autowired
	private UsuariosRepository usuarioRepository;
	
	@PostMapping("/login")
	public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutenticacaoDTO dados) {
		
		var autenticationToken = new UsernamePasswordAuthenticationToken(dados.email(), dados.senha());
		var autentication = manager.authenticate(autenticationToken);
		
		String tokenJWT = tokenService.gerarToken((Usuarios) autentication.getPrincipal());
		
		return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
	}
	
	@PostMapping("/usuario/cadastrarUsuario")
	@Transactional
	public ResponseEntity cadastrarUsuario(@RequestBody @Valid DadosCadastrarUsuarioDTO dados) {
		
		cadastramentoDeUsuarioService.cadastramento(dados);
		
		return ResponseEntity.ok().build();
	}
}
