package med.voll.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import med.voll.api.dto.agendamento.DadosAgendamentoConsultaDTO;
import med.voll.api.dto.cancelamento.DadosCancelamentoDeConsultaDTO;
import med.voll.api.entidades.Consulta;
import med.voll.api.entidades.Medico;
import med.voll.api.infra.exception.ValidacaoException;
import med.voll.api.repository.ConsultaRepository;
import med.voll.api.repository.MedicoRepository;
import med.voll.api.repository.PacienteRepository;

@Service
public class AgendaDeConsultas {
	
	@Autowired
	private ConsultaRepository consultaRepository;
	
	@Autowired
	private MedicoRepository medicoRepository;
	
	@Autowired
	private PacienteRepository pacienteRepository;
	
	public void agendar(DadosAgendamentoConsultaDTO dadosDTO) throws ValidacaoException {
		
		if(!pacienteRepository.existsById(dadosDTO.idPaciente())) {
			throw new ValidacaoException("Paciente não está cadastrado!");
		}
		
		if(dadosDTO.idMedico() != null && !medicoRepository.existsById(dadosDTO.idMedico())) {
			throw new ValidacaoException("Medico não está cadastrado!");
		}
		
		var medico = escolherMedico(dadosDTO);
		
		//.get() pega o objeto de fato que foi carregado
		var paciente = pacienteRepository.getReferenceById(dadosDTO.idPaciente());
		var consulta = new Consulta(null, medico, paciente, dadosDTO.data(), null);
		
		consultaRepository.save(consulta);
	}
	
	public void cancelar(DadosCancelamentoDeConsultaDTO dadosDTO) throws ValidacaoException {
		
		if(!consultaRepository.existsById(dadosDTO.idConsulta())) {
			throw new ValidacaoException("ID da consulta informado não existe!");
		}
		
		var consulta = consultaRepository.getReferenceById(dadosDTO.idConsulta());
		consulta.cancelar(dadosDTO.motivo());
	}

	private Medico escolherMedico(DadosAgendamentoConsultaDTO dadosDTO) {
		
		if(dadosDTO.idMedico() != null) {
			return medicoRepository.getReferenceById(dadosDTO.idMedico());
		}
		
		if(dadosDTO.especialidade() == null) {
			throw new ValidacaoException("Especialidade é obrigatória quando o médico não for escolhido!");
		}
		
		return medicoRepository.escolherMedicoAleatorioLivreNaData(dadosDTO.especialidade(), dadosDTO.data());
	}

}
