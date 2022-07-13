package repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import model.User;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findById(Long id);

	Optional<User> findByNome(String nome);
	
	Optional<User> findByCpf(String cpf);
	
	Optional<User> findByEmail(String email);

}
