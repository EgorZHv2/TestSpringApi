package springApp.core.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@Embeddable
public class ProductsInBasketsId implements Serializable {




    @ManyToOne
    @JoinColumn(name = "`userId`",referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "`productId`",referencedColumnName = "id")
    private Product product;
}
