package controller;

import java.util.List;


import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dto.OpenSessionVoteDto;
import dto.SessionVoteDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import model.SessionVote;
import service.SessionVoteService;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/v1/sessão-de-voto")
@CrossOrigin(origins = "*")
@Slf4j
@Data
public class SessionController {

	@Autowired
	private SessionVoteService service;

	@Autowired
	public SessionController(SessionVoteService service) {
		this.service = service;
	}

	@GetMapping("/{id}")
	public ResponseEntity<SessionVote> buscarPorId(@PathVariable Long id) {
		log.info("Buscando por Id {}", id);
		return new ResponseEntity(sessionVoteService.buscaPorId(id), HttpStatus.OK);
	}

	@GetMapping
	@Cacheable(value = "ListaDeSessoes")
	public ResponseEntity<List<SessionVote>> buscarTodas() {
		log.info("Buscando todas sessoes");
		return new ResponseEntity<>(sessionVoteService.buscarTodas(), HttpStatus.OK);
	}

	@PostMapping("/criar")
	@CacheEvict(value = "ListaDeSessoes", allEntries = true)
	public ResponseEntity<SessionVote> criar(@Valid @RequestBody SessaoRequestDTO dto) {
		log.info("Criando sessao Votacao");
		return new ResponseEntity<>(sessionVoteService.criarSessao(dto), HttpStatus.CREATED);
	}

	@PostMapping("/iniciar")
	public ResponseEntity<SessionVote> iniciarVotacao(@Valid @RequestBody SessaoStartRequestDTO dto) {
		log.info("Iniciando sessao votacao");
		return new ResponseEntity<>(sessionVoteService.iniciarVotacao(dto), HttpStatus.OK);
	}

}
