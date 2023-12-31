package med.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
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
import med.voll.api.dto.medico.DadosAtualizarMedicoDTO;
import med.voll.api.dto.medico.DadosCadastroMedicoDTO;
import med.voll.api.dto.medico.DadosDetalhamentoMedicoDTO;
import med.voll.api.dto.medico.DadosListagemMedicoDTO;
import med.voll.api.entidades.Medico;
import med.voll.api.repository.MedicoRepository;

@RestController
@RequestMapping("/medicos")
@SecurityRequirement(name = "bearer-key")
public class MedicoController {
	
	//repository vai fazer a injecao de dependencia
	@Autowired
	private MedicoRepository repository;
	
	@PostMapping
	@Transactional
	public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroMedicoDTO dadosDTO, UriComponentsBuilder uriBuilder) {
		
		var medico = new Medico(dadosDTO);
		
		repository.save(medico);
		
		//post cadastrar retorno correto é o 201, precisa da uri e do corpo para implementar esse retorno
		
		var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
		
		return ResponseEntity.created(uri).body(new DadosDetalhamentoMedicoDTO(medico));
	}
	
	@GetMapping
	public ResponseEntity<Page<DadosListagemMedicoDTO>> listarTodos(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
		 Page<DadosListagemMedicoDTO> page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedicoDTO::new);
		 
		 return ResponseEntity.ok(page);
	}
	
	@PutMapping
	@Transactional
	public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizarMedicoDTO dados) {
		
		Medico medico = repository.getReferenceById(dados.id());
		medico.atualizarInformacoes(dados);
		
		return ResponseEntity.ok(new DadosDetalhamentoMedicoDTO(medico));
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity excluir(@PathVariable Long id) {
		Medico medico = repository.getReferenceById(id);
		medico.inativarMedico();
		
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity detalhar(@PathVariable Long id) {
		Medico medico = repository.getReferenceById(id);
		
		return ResponseEntity.ok(new DadosDetalhamentoMedicoDTO(medico));
	}
}
