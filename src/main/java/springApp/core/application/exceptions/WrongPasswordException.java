package springApp.core.application.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

public class WrongPasswordException extends ApiRuntimeException {
    public WrongPasswordException(){
        super("Wrong password", HttpStatus.UNAUTHORIZED);
    }
}


