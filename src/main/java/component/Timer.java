package component;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import dto.SessionVoteDto;
import service.SessionVoteService;



@Component
public class Timer {

    private static final Logger LOGGER = LoggerFactory.getLogger(Timer.class);


    private final SessionVoteService sessionVoteService;

    public Timer(SessionVoteService sessaoVotacaoService){
        this.sessionVoteService = sessaoVotacaoService;
    }

    @Scheduled(cron = "60 * * * * *")
    private void teste(){
        LOGGER.debug("Contador de tempo sendo executado...");
        List<SessionVoteDto> list = sessionVoteService.buscarSessoesEmAndamento();
        LOGGER.debug("Quantidade de sessões abertas = {}", list.size());
        list.forEach(dto -> {
            LOGGER.debug("Sessao encerrada {}", dto.getId());
            if (dto.getAtiva()) {
            	sessionVoteService.EncerrarSessaoVotacao(dto);
            }
        });

    }

}
