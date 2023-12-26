package med.voll.api.regras;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import med.voll.api.dto.agendamento.DadosAgendamentoConsultaDTO;
import med.voll.api.entidades.Consulta;
import med.voll.api.entidades.Medico;
import med.voll.api.infra.exception.ValidacaoException;
import med.voll.api.repository.ConsultaRepository;
import med.voll.api.repository.MedicoRepository;
import med.voll.api.repository.PacienteRepository;

@Service
public class AgendaDeConsultas {
	
	@Autowired
	private ConsultaRepository repository;
	
	@Autowired
	private MedicoRepository repositoryMedico;
	
	@Autowired
	private PacienteRepository repositoryPaciente;
	
	public void agendar(DadosAgendamentoConsultaDTO dadosDTO) throws ValidacaoException {
		
		if(!repositoryPaciente.existsById(dadosDTO.idPaciente())) {
			throw new ValidacaoException("Paciente não está cadastrado!");
		}
		
		if(dadosDTO.idMedico() != null && !repositoryMedico.existsById(dadosDTO.idMedico())) {
			throw new ValidacaoException("Medico não está cadastrado!");
		}
		
		var medico = escolherMedico(dadosDTO);
		
		//.get() pega o objeto de fato que foi carregado
		var paciente = repositoryPaciente.getReferenceById(dadosDTO.idPaciente());
		var consulta = new Consulta(null, medico, paciente, dadosDTO.data());
		
		repository.save(consulta);
	}

	private Medico escolherMedico(DadosAgendamentoConsultaDTO dadosDTO) {
		
		if(dadosDTO.idMedico() != null) {
			return repositoryMedico.getReferenceById(dadosDTO.idMedico());
		}
		
		if(dadosDTO.especialidade() == null) {
			throw new ValidacaoException("Especialidade é obrigatória quando o médico não for escolhido!");
		}
		
		return repositoryMedico.escolherMedicoAleatorioLivreNaData(dadosDTO.especialidade(), dadosDTO.data());
	}

}
