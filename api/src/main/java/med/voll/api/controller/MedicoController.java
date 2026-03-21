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
import med.voll.api.domain.medico.DadosAtualizarMedicoDTO;
import med.voll.api.domain.medico.DadosCadastroMedicoDTO;
import med.voll.api.domain.medico.DadosDetalhamentoMedicoDTO;
import med.voll.api.domain.medico.DadosListagemMedicoDTO;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;

@RestController
@RequestMapping("/medicos")
@SecurityRequirement(name = "bearer-key")
public class MedicoController {
	
	@Autowired
	private MedicoRepository repository;
	
	@PostMapping
	@Transactional
	public ResponseEntity cadastrarMedico(
			@RequestBody @Valid DadosCadastroMedicoDTO dadosDTO, 
			UriComponentsBuilder uriBuilder
			) {
		
		var medico = new Medico(dadosDTO);
		
		repository.save(medico);
		
		var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new DadosDetalhamentoMedicoDTO(medico));
	}
	
	@GetMapping("/listarMedicos")
	public ResponseEntity<Page<DadosListagemMedicoDTO>> listarMedicos(
			@PageableDefault(size = 10, sort = {"nome"}) 
			Pageable paginacao
			) {
		
		 Page<DadosListagemMedicoDTO> page = repository.findAll(paginacao).map(DadosListagemMedicoDTO::new);
		 
		 return ResponseEntity.ok(page);
	}
	
	@GetMapping("/listarMedicosAtivos")
	public ResponseEntity<Page<DadosListagemMedicoDTO>> listarMedicosAtivos(
			@PageableDefault(size = 10, sort = {"nome"}) 
			Pageable paginacao
			) {
		
		 Page<DadosListagemMedicoDTO> page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedicoDTO::new);
		 
		 return ResponseEntity.ok(page);
	}
	
	@PutMapping
	@Transactional
	public ResponseEntity atualizarMedico(@RequestBody @Valid DadosAtualizarMedicoDTO dados) {
		
		Medico medico = repository.getReferenceById(dados.id());
		medico.atualizarInformacoes(dados);
		
		return ResponseEntity.ok(new DadosDetalhamentoMedicoDTO(medico));
	}
	
	@DeleteMapping("/inativarMedico/{id}")
	@Transactional
	public ResponseEntity inativarMedico(@PathVariable Long id) {
		Medico medico = repository.getReferenceById(id);
		medico.inativarMedico();
		
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/excluir/{id}")
	@Transactional
	@Secured("ROLE_ADMIN")
	public ResponseEntity excluirMedico(@PathVariable Long id) {
		
		repository.deleteById(id);
		
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity detalharMedico(@PathVariable Long id) {
		Medico medico = repository.getReferenceById(id);
		
		return ResponseEntity.ok(new DadosDetalhamentoMedicoDTO(medico));
	}
}
