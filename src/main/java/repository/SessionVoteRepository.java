package repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import model.SessionVote;




@Repository
public interface SessionVoteRepository extends JpaRepository<SessionVote, Long> {

	boolean existsByTemaId(Long tema);

	Optional<SessionVote> findById(Long id);

}
