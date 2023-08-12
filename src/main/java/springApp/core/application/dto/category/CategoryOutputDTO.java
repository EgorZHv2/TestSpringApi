package springApp.core.application.dto.category;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
public class CategoryOutputDTO implements Serializable {
    private UUID id;
    private String name;

    public CategoryOutputDTO(){}


}
