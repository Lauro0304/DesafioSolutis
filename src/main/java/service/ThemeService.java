package service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.math3.util.Precision;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dto.ThemeDto;
import enuns.StatusEnum;
import enuns.VoteEnum;
import model.Theme;
import repository.ThemeRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ThemeService {
	

	@Autowired
	private final ThemeRepository themeRepository;

	public ThemeService(ThemeRepository repository) {
		this.themeRepository = repository;
	}

	  @Transactional
		public List<Theme> buscarTodasPautas(){
			log.info("Buscando todas os temas");
			return themeRepository.findAll();
		}

	    @Transactional
	    public Theme buscarTemaPeloID(Long id){
			log.info("Buscando pauta pelo Id {}", id);
	        return themeRepository.findById(id).orElse(null);
	    }
		
		@Transactional
		public Theme criarTema(Theme estado) {
			estado.setStatus(StatusEnum.ABERTA);
			log.info("Abrindo Pauta");
			return themeRepository.save(estado);
		}
		
		@Transactional
		public void mudancaStatus(Theme estado) {
			estado.setStatus(StatusEnum.FECHADA);
			log.info("Fechando Pauta");
			themeRepository.save(estado);
		}
		
		@Transactional
		public void definirVencedor(Theme theme) {
			log.info("Informando vencedor do tema");
			if (theme.getPercentualSim() > theme.getPercentualNao()){

				theme.setVencedor(VoteEnum.SIM.getLabel());
	        }else if(theme.getPercentualSim() < theme.getPercentualNao()){

	        	theme.setVencedor(VoteEnum.NAO.getLabel());
	        }else {

	        	theme.setVencedor("EMPATE");
	        }
		}
	    
		@Transactional
		public void definirPercentual(Theme theme) {
	        log.info("Definindo a porcentagem");
	        theme.setPercentualSim(Precision.round(((
	                Double.valueOf(theme.getQtdVotosSim())/ theme.getQtdVotos())*100), 2));
	        theme.setPercentualNao(Precision.round(((
	                Double.valueOf(theme.getQtdVotosNao())/ theme.getQtdVotos())*100), 2));
	    }	

}
