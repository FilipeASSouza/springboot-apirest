package med.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import med.voll.api.entidades.Medico;
import med.voll.api.medico.DadosCadastroMedicoDTO;
import med.voll.api.repository.MedicoRepository;

@RestController
@RequestMapping("/medicos")
public class MedicoController {
	
	//repository vai fazer a injecao de dependencia
	@Autowired
	private MedicoRepository repository;
	
	@PostMapping
	public void cadastrar(@RequestBody DadosCadastroMedicoDTO dados) {
		
		repository.save(new Medico(dados));
		
	}

}
