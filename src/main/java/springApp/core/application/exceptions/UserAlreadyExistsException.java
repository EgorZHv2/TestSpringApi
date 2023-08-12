package springApp.core.application.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

public class UserAlreadyExistsException extends ApiRuntimeException {
    public UserAlreadyExistsException() {
        super("User already exists", HttpStatus.CONFLICT);
    }
}
