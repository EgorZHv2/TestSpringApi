package springApp.core.application.dto.category;

import java.io.Serializable;

public class CategoryCreateDTO implements Serializable {
    private String name;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public CategoryCreateDTO(){}

}
