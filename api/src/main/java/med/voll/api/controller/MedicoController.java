package med.voll.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import med.voll.api.entidades.Medico;
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
	public List<DadosListagemMedicoDTO> listarTodos() {
		return repository.findAll().stream().map(DadosListagemMedicoDTO::new).toList();
	}
}
