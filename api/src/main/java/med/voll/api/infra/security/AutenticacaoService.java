package med.voll.api.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import med.voll.api.repository.UsuariosRepository;

@Service
public class AutenticacaoService implements UserDetailsService {

	@Autowired//anotacao utilizada para fazer a injecao de dependencia
	private UsuariosRepository repository;
	
	//classe sera identificada pelo spring no momento da autenticacao
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		return repository.findByLogin(username);
	}

}
