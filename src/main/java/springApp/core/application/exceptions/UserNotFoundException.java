package springApp.core.application.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

public class UserNotFoundException extends ApiRuntimeException {
    public UserNotFoundException() {
        super("User not found", HttpStatus.NOT_FOUND);
    }
}
