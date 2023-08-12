package springApp.core.domain.entities;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Embeddable
public class ProductsInOrdersId implements Serializable {

    @ManyToOne(optional = false)
    @JoinColumn(name = "`productId`")
    private Product product;
    @ManyToOne(optional = false)
    @JoinColumn(name = "`orderId`")
    private Order order;
}
