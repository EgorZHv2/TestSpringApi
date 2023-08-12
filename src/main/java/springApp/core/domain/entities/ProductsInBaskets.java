package springApp.core.domain.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "`productsInBaskets`")
@NoArgsConstructor
public class ProductsInBaskets implements Serializable {

    @EmbeddedId
    ProductsInBasketsId pk;


    @Column(name = "`productQuantity`")
    private Integer productQuantity;

}
