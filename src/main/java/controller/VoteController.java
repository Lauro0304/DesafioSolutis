package controller;

import dto.ResultDto;
import dto.VoteDto;
import service.VotationService;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping(value = "/api/v1/votacoes")
@Api(value = "Votacao", tags = "Votacao")
public class VoteController {

	private static final Logger LOGGER = LoggerFactory.getLogger(VoteController.class);

	private final VotationService service;

	@Autowired
	    public VoteController(VotationService service){
	        this.service = service;
	    }

	@ApiOperation(value = "Votar em determinado tema, enquanto a sessão de votação estiver aberta")
	@PostMapping(value = "/votar")
	public ResponseEntity<String> votar(@Valid @RequestBody VoteDto dto) {
		LOGGER.debug("Usuario votando usuario = {}", dto.getCpfUsuario());
		String mensagem = service.votar(dto);
		LOGGER.debug("Voto usuario finalizado usuario = {}", dto.getCpfUsuario());
		return ResponseEntity.status(HttpStatus.CREATED).body(mensagem);
	}

	@ApiOperation(value = "Resultado da votacao, somente após a finalização da sessao de votação")
	@GetMapping(value = "/resultado/{idPauta}/{idSessaoVotacao}")
	public ResponseEntity<ResultDto> resultadoVotocao(@PathVariable("idPauta") Integer idTema,
			@PathVariable("idSessaoVotacao") Integer idSessaoVotacao) {
		LOGGER.debug("Buscando resultado da votacao idTema = {}. idSessaoVotacao = {}", idTema, idSessaoVotacao);
		ResultDto dto = service.buscarDadosResultadoVotacao(idTema, idSessaoVotacao);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
	}

}
