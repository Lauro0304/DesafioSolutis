package service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import advice.NotFoundException;

import dto.SessionDto;
import dto.SessionStartDto;

import enuns.StatusEnum;
import enuns.VoteStatus;
import model.SessionVote;
import model.Theme;
import repository.SessionVoteRepository;
import repository.ThemeRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SessionVoteService {

	private SessionVoteRepository sessionVoteRepository;
	private ThemeRepository themeRepository;
	private ThemeService themeService;

	@Autowired
	public SessionVoteService(SessionVoteRepository sessionVoteRepository, ThemeRepository themeRepository,
			ThemeService themeService) {
		this.sessionVoteRepository = sessionVoteRepository;
		this.themeService = themeService;
		this.themeRepository = themeRepository;

	}

	@Transactional
	private boolean verificarExistenciaPauta(Long idTema) {
		log.info("Verificando se o tema {} existe", idTema);
		return sessionVoteRepository.existsById(idTema);
	}

	@Transactional
	public List<SessionVote> buscarTodas() {
		log.info("Buscando todas as sessoes");
		return sessionVoteRepository.findAll();
	}

	@Transactional
	public Optional<Theme> buscaPorId(Long id) {
		log.info("Buscando tema por Id {}", id);
		return themeRepository.findById(id);
	}

	@Transactional
	public SessionVote criarSessao(SessionDto dto) {
		if (verificarExistenciaPauta(dto.getIdTema())) {
			log.info("Sessao ja criada");
			throw new RuntimeException("Sessao ja criada");
		}
		Theme theme = themeService.buscarTemaPeloID(dto.getIdTema());

		SessionVote sessionVote = SessionVote.builder().duracao(tempoFechado(dto.getDuracao())).theme(theme).build();
		log.info("Criando sessao");
		return sessionVoteRepository.save(sessionVote);
	}

	@Transactional
	private Integer tempoFechado(Integer duracao) {

		return Objects.isNull(duracao) ? 60 : duracao;
	}

	@Transactional
	private Theme buscarTema(Long idTema) {
		return themeRepository.findById(idTema).orElseThrow(() -> new NotFoundException("Tema nao encontrado"));
	}

	@Transactional
	public SessionVote iniciarVotacao(SessionStartDto dto) {
		SessionVote sessionVote = sessionVoteRepository.findById(dto.getSessaoId())
				.orElseThrow(() -> new NotFoundException("Sessao votacao nao encontrada"));

		if (sessionVote.getTheme().getStatus().equals(StatusEnum.valueOf("FECHADA"))) {
			throw new RuntimeException("O Tema está FECHADO");
		}

		sessionVote.setFechado(Instant.now().plus(sessionVote.getDuracao(), ChronoUnit.SECONDS));

		return sessionVoteRepository.save(sessionVote);
	}

	@Scheduled(fixedDelay = 60000)
	@Transactional
	public void fecharSessao() {
		List<SessionVote> listaSessaoVotacao = obterVotacaoExpiradaMasNaoFechada();

		listaSessaoVotacao.forEach(votacao -> {
			log.info("Estatisticas da sessao");
			votacao.getTheme().setQtdVotos(votacao.getVote().size());
			votacao.getTheme().setQtdVotosSim(qtSim(votacao));
			votacao.getTheme().setQtdVotosNao(qtNao(votacao));
			sessionVoteRepository.save(votacao);
			themeService.mudancaStatus(votacao.getTheme());
			themeService.definirPercentual(votacao.getTheme());
			themeService.definirVencedor(votacao.getTheme());
		});
	}

	@Transactional
	public Integer qtSim(SessionVote sessaoVotacao) {
		return Math.toIntExact(
				sessaoVotacao.getVote().stream().filter(c -> c.getVoto().equals(VoteStatus.valueOf("SIM"))).count());
	}

	@Transactional
	public Integer qtNao(SessionVote sessaoVotacao) {
		return Math.toIntExact(
				sessaoVotacao.getVote().stream().filter(c -> c.getVoto().equals(VoteStatus.valueOf("NAO"))).count());
	}

	@Transactional
	private List<SessionVote> obterVotacaoExpiradaMasNaoFechada() {
		return sessionVoteRepository.findAll().stream()
				.filter(votingSession -> votingSession.fechada() && votingSession.aberta())
				.collect(Collectors.toList());
	}

}
