package springApp.core.application.exceptions;

import org.springframework.http.HttpStatus;

public class CategoryNotFoundException extends ApiRuntimeException {
    public CategoryNotFoundException(){
        super("Category not found", HttpStatus.NOT_FOUND);
    }
}
