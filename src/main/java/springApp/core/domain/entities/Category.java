package springApp.core.domain.entities;

import jakarta.persistence.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.util.List;
import java.util.UUID;
@Entity
@Table(name = "`category`")
public class Category extends AbstractPersistable<UUID> {
    @Column(name = "name",nullable = false)
    private String name;
    @OneToMany(mappedBy = "category",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Product> products;
    public String getName() {
        return name;
    }
    @Override
    public void setId(UUID id){
        super.setId(id);
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<Product> getProducts() {
        return products;
    }
    public void setProducts(List<Product> products) {
        this.products = products;
    }

}
