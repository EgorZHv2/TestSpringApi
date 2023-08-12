package springApp.core.application.exceptions;

import org.springframework.http.HttpStatus;

public class ProductNotFoundException extends ApiRuntimeException{
    public ProductNotFoundException(){
        super("Product not found", HttpStatus.NOT_FOUND);
    }
}
