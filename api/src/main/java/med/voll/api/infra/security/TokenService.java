package med.voll.api.infra.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import med.voll.api.entidades.Usuarios;

@Service
public class TokenService {
	
	@Value("${api.security.token.secret}")
	private String secret;
	
	public String gerarToken(Usuarios usuario) {
		
		try {
		    var algoritmo = Algorithm.HMAC256(secret);
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
	
	public String getSubject(String tokenJWT) {
		
		try {
			var algoritmo = Algorithm.HMAC256(secret);
		    return JWT.require(algoritmo)
		        .withIssuer("API Voll.med")
		        .build()
		        .verify(tokenJWT)
		        .getSubject();
		        
		} catch (JWTVerificationException e){
		    throw new RuntimeException("Token JWT inválido ou expirado!");
		}
	}

	private Instant dataExpiracao() {
		return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
	}
}
