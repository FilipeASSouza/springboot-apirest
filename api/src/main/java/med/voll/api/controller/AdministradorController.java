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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.paciente.PacienteRepository;
import med.voll.api.domain.usuario.DadosDetalhamentoUsuariosDTO;
import med.voll.api.domain.usuario.UsuariosRepository;

@RestController
@RequestMapping("/admin")
@SecurityRequirement(name = "bearer-key")
public class AdministradorController {
	
	@Autowired
	private UsuariosRepository usuariosRepository;
	
	@Autowired
	private MedicoRepository medicoRepository;
	
	@Autowired
	private PacienteRepository pacienteRepository;
	
	@GetMapping("/usuario/listarUsuarios")
	@Secured("ROLE_ADMIN")
	public ResponseEntity<Page<DadosDetalhamentoUsuariosDTO>> listarUsuarios(
			@PageableDefault(size = 10, sort = {"login"}) 
			Pageable paginacao
			) {
		
		Page<DadosDetalhamentoUsuariosDTO> page = usuariosRepository
											.findAll(paginacao)
											.map(usuario -> 
													new DadosDetalhamentoUsuariosDTO(usuario.getLogin())
												);
		
		return ResponseEntity.ok(page);
	}
	
	@DeleteMapping("/medico/excluir/{id}")
	@Secured("ROLE_ADMIN")
	@Transactional
	public ResponseEntity excluirMedico(@PathVariable Long id) {
		
		medicoRepository.deleteById(id);
		
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping("/paciente/excluir/{id}")
	@Secured("ROLE_ADMIN")
	@Transactional
	public ResponseEntity excluirPaciente(@PathVariable Long id) {
		
		pacienteRepository.deleteById(id);
		
		return ResponseEntity.noContent().build();
	}

}
