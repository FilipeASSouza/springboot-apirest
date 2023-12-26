package med.voll.api.repository;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import med.voll.api.entidades.Medico;
import med.voll.api.enums.Especialidade;

public interface MedicoRepository extends JpaRepository<Medico, Long> {

	Page<Medico> findAllByAtivoTrue(Pageable paginacao);

	@Query("""
			select m from Medicos m
			where
			m.ativo = true
			and
			m.especialidade = :especialidade
			and
			m.id not in(
				select c.medico_id from Consultas c
				where
				c.data = :data
				)
			order by random()
			limit 1
			""")
	Medico escolherMedicoAleatorioLivreNaData(Especialidade especialidade, LocalDateTime data);

}
