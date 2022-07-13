package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import model.SessionVote;
import model.User;
import model.Vote;


@Repository
public interface VoteReposiroty extends JpaRepository<Vote, Long>{
	
	Boolean existsBySessaoVotacaoAndUsuario(SessionVote sessaoVotacao, User usuario);
	

}
