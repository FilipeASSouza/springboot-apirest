package med.voll.api.domain.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuariosRepository extends JpaRepository<Usuarios, Long> {

	UserDetails findByEmail(String email);

	@Query("""
			select u
			from Usuarios u
			where
			u.email = :email
			""")
	Usuarios findByUsuario(String email);

}
