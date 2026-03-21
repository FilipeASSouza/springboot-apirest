package med.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import med.voll.api.domain.paciente.DadosAtualizarPacienteDTO;
import med.voll.api.domain.paciente.DadosCadastroPacienteDTO;
import med.voll.api.domain.paciente.DadosDetalhamentoPacienteDTO;
import med.voll.api.domain.paciente.DadosListagemPacienteDTO;
import med.voll.api.domain.paciente.Paciente;
import med.voll.api.domain.paciente.PacienteRepository;

@RestController
@RequestMapping("/pacientes")
@SecurityRequirement(name = "bearer-key")
public class PacienteController {
	
	@Autowired
	private PacienteRepository repository;
	
	@PostMapping
	@Transactional
	public ResponseEntity cadastrar(
			@RequestBody @Valid DadosCadastroPacienteDTO dadosDTO, 
			UriComponentsBuilder uriBuilder
			) {
		
		var paciente = new Paciente(dadosDTO);
		
		repository.save(paciente);
		
		var uri = uriBuilder.path("/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new DadosDetalhamentoPacienteDTO(paciente));
		
	}
	
	@GetMapping("/listarPacientesAtivos")
	public ResponseEntity<Page<DadosListagemPacienteDTO>> listarPacientesAtivos(
			@PageableDefault(size = 10, sort = {"nome"}) 
			Pageable paginacao
			) {
		
		Page<DadosListagemPacienteDTO> page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemPacienteDTO::new);
		
		return ResponseEntity.ok(page);
	}
	
	@GetMapping("/listarPacientes")
	public ResponseEntity<Page<DadosListagemPacienteDTO>> listarPacientes(
			@PageableDefault(size = 10, sort = {"nome"}) 
			Pageable paginacao
			) {
		
		Page<DadosListagemPacienteDTO> page = repository.findAll(paginacao).map(DadosListagemPacienteDTO::new);
		
		return ResponseEntity.ok(page);
	}
	
	@PutMapping
	@Transactional
	public ResponseEntity atualizarPaciente(
			@RequestBody @Valid DadosAtualizarPacienteDTO dadosDTO
			) {
		
		Paciente paciente = repository.getReferenceById(dadosDTO.id());
		paciente.atualizarInformacoes(dadosDTO);
		
		return ResponseEntity.ok(new DadosDetalhamentoPacienteDTO(paciente));
	}
	
	@DeleteMapping("/inativarPaciente/{id}")
	@Transactional
	public ResponseEntity inativarPaciente(@PathVariable Long id) {
		Paciente paciente = repository.getReferenceById(id);
		paciente.inativarPaciente();
		
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/excluir/{id}")
	@Transactional
	@Secured("ROLE_ADMIN")
	public ResponseEntity excluirPaciente(@PathVariable Long id) {
		
		repository.deleteById(id);
		
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity detalharPaciente(@PathVariable Long id) {
		Paciente paciente = repository.getReferenceById(id);
		
		return ResponseEntity.ok(new DadosDetalhamentoPacienteDTO(paciente));
	}
}
