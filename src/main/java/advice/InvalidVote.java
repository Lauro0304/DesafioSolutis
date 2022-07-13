package advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidVote extends RuntimeException{


    public InvalidVote(String mensagem){
        super(mensagem);
    }
}
