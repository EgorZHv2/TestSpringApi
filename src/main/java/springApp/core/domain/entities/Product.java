package springApp.core.domain.entities;

import lombok.Getter;
import lombok.Setter;
import springApp.core.domain.enums.ProductStatus;
import jakarta.persistence.*;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "`product`")
@Getter
@Setter
public class Product extends AbstractPersistable<UUID> {
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "price", nullable = false,scale = 2)
    private BigDecimal price;

    @Column(name = "`leftInStock`", nullable = false)
    private Integer leftInStock;

    @Column(name = "`productStatus`", nullable = false)
    private ProductStatus productStatus;

    @Column(name = "`categoryId`",insertable = false,updatable = false)
    private UUID categoryId;
    @ManyToOne(optional = false)
    @JoinColumn(name = "`categoryId`")
    private Category category;
    @OneToMany(mappedBy = "pk.product")
    private List<ProductsInBaskets> productsInBasketsList;

    @OneToMany(mappedBy = "pk.product")
    private List<ProductsInOrders> productsInOrders;

}
