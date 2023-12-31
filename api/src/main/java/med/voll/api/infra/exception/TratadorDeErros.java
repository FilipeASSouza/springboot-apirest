package med.voll.api.infra.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class TratadorDeErros {

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity tratarErro404() {
		
		return ResponseEntity.notFound().build();
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity tratarErro400(MethodArgumentNotValidException e) {
		
		var erros = e.getFieldErrors();
		
		return ResponseEntity.badRequest().body(erros.stream().map(DadosErroValidacaoDTO::new).toList());
	}
	
	@ExceptionHandler(ValidacaoException.class)
	public ResponseEntity tratarErroRegraDeNegocio(ValidacaoException e) {
				
		return ResponseEntity.badRequest().body(e.getMessage());
	}
	
	private record DadosErroValidacaoDTO(String campo, String mensagem) {
		
		public DadosErroValidacaoDTO(FieldError erro) {
			this(erro.getField(), erro.getDefaultMessage());
		}
	}
	
}
