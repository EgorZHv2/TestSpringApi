package springApp.core.application.dto.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class ExceptionResponseDTO implements Serializable {
    public String message;
}
