package advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.LOCKED)
public class EndSession extends  RuntimeException{

    public EndSession(String mensagem){
        super(mensagem);
    }
}
