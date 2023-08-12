package springApp.core.application.dto.category;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
@Getter
@Setter
public class CategoryUpdateDTO {
    private UUID id;
    private String name;

    public CategoryUpdateDTO(){}
}
