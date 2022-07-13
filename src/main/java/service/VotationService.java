package service;

import java.time.Instant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import advice.NotFoundException;
import dto.VoteDto;
import lombok.extern.slf4j.Slf4j;
import model.SessionVote;
import model.User;
import model.Vote;
import repository.SessionVoteRepository;
import repository.VoteReposiroty;

@Service
@Slf4j
public class VotationService {
	
	@Autowired
	private VoteReposiroty voteRepository;
	
	@Autowired
 	private SessionVoteRepository sessionVoteRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
    public VotationService(VoteReposiroty voteRepository, SessionVoteRepository sessionVoteRepository, UserService userService) {
        this.voteRepository = voteRepository;
        this.sessionVoteRepository = sessionVoteRepository;
        this.userService = userService;
    }
	
	@Transactional
	public Vote votacao(VoteDto dto) {
		SessionVote sessaoVotacao = sessionVoteRepository.findById(dto.getSessaoVotacaoId()).orElse(null);
		User usuario = userService.buscarPorCPF(dto.getCpf());
		log.info("Verificando sessao votacao");
		if (sessaoVotacao==null) {
			log.info("Sessao nao encontrada");
			throw new NotFoundException("Sessao votacao nao encontrada");
		}
		
		Vote voto = Vote.builder()
				.vote(dto.getVoto())
				.vote(Instant.now())
				.usuario(usuario)
				.sessaoVotacao(sessaoVotacao).build();
		
		if (Boolean.TRUE.equals(verificarVotoUnico(sessaoVotacao, usuario))){

            throw new RuntimeException("Usuario ja votou");
        }
		
		verificaSessaoVotoValidoTempo(voto);
		
		log.info("Salvando o voto");
		return voteRepository.save(voto);
	}
	
	private Boolean verificarVotoUnico(SessionVote sessaoVotacao, User usuario){

        return voteRepository.existsBySessaoVotacaoAndUsuario(sessaoVotacao, usuario);
    }
	
	@Transactional
	private void verificaSessaoVotoValidoTempo(Vote voto) {
		log.info("Verificando tempo valido da sessao");
		if (voto.getVotoInstant().isAfter(voto.getSessaoVotacao().getFechado())) {
			log.info("Sessao expirada");
			throw new RuntimeException("Sessao votacao expirada");
		}
	}

}
