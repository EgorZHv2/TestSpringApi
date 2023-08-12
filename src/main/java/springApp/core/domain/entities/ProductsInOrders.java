package springApp.core.domain.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "`productsInOrders`")
public class ProductsInOrders implements Serializable {

    @EmbeddedId
    ProductsInOrdersId pk;

    @Column(name = "`productQuantity`")
    private Integer productQuantity;
}
