package med.voll.api.infra.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;

import med.voll.api.entidades.Usuarios;

@Service
public class TokenService {
	
	public String gerarToken(Usuarios usuario) {
		
		try {
		    var algoritmo = Algorithm.HMAC256("12345678");
		    return JWT.create()
		        .withIssuer("API Voll.med")//identificacao do token
		        .withSubject(usuario.getLogin())//dono do token, relacionado a esse token
		        //.withClaim("id", usuario.getId())//informação que vc quer guardar sobre aquele dono do token
		        .withExpiresAt(dataExpiracao())
		        .sign(algoritmo);
		} catch (JWTCreationException e){
		    throw new RuntimeException("Erro ao gerar o token jwt." , e);
		}
	}

	private Instant dataExpiracao() {
		return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
	}
}
