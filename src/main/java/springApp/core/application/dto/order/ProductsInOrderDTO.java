package springApp.core.application.dto.order;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class ProductsInOrderDTO implements Serializable {
    private UUID productId;
    private Integer productQuantity;
}
