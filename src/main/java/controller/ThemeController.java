package controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dto.ThemeDto;
import io.swagger.annotations.ApiOperation;
import service.ThemeService;

@RestController
@RequestMapping(value = "/v1/temas")
@CrossOrigin(origins = "*")
public class ThemeController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ThemeController.class);

	private final ThemeService service;

	@Autowired
	public ThemeController(ThemeService service) {
		this.service = service;
	}

	@ApiOperation(value = "Buscar o tema utilizando o ID")
	@GetMapping(value = "/{id}")
	public ResponseEntity<ThemeDto> buscarTemaPeloId(@PathVariable("id") Integer id) {
		LOGGER.debug("Buscando o tema pelo o ID = {id}", id);
		return ResponseEntity.ok(service.buscarTemaPeloId(id));

	}

	@ApiOperation(value = "Cria um tema para ser votado")
	@PostMapping
	public ResponseEntity<ThemeDto> salvarTema(@Valid @RequestBody ThemeDto themeDto) {
		LOGGER.debug("Salvando a pauta = {id}", themeDto.getDescricao());
		themeDto = service.salvar(themeDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(themeDto);
	}
}
