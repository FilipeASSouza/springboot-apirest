package med.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import med.voll.api.entidades.Medico;
import med.voll.api.medico.DadosAtualizarMedicoDTO;
import med.voll.api.medico.DadosCadastroMedicoDTO;
import med.voll.api.medico.DadosListagemMedicoDTO;
import med.voll.api.repository.MedicoRepository;

@RestController
@RequestMapping("/medicos")
public class MedicoController {
	
	//repository vai fazer a injecao de dependencia
	@Autowired
	private MedicoRepository repository;
	
	@PostMapping
	@Transactional
	public void cadastrar(@RequestBody @Valid DadosCadastroMedicoDTO dados) {
		repository.save(new Medico(dados));		
	}
	
	@GetMapping
	public Page<DadosListagemMedicoDTO> listarTodos(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
		return repository.findAll(paginacao).map(DadosListagemMedicoDTO::new);
	}
	
	@PutMapping
	@Transactional
	public void atualizar(@RequestBody @Valid DadosAtualizarMedicoDTO dados) {
		
		Medico medico = repository.getReferenceById(dados.id());
		medico.atualizarInformacoes(dados);
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public void excluir(@PathVariable Long id) {
		repository.deleteById(id);
	}
}
