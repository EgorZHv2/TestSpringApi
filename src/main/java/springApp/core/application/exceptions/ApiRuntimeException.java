package springApp.core.application.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public  class ApiRuntimeException extends RuntimeException {
    protected HttpStatus status;
    public ApiRuntimeException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}
