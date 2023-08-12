package springApp.core.application.dto.order;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;
@Getter
@Setter
@NoArgsConstructor
public class ProductsInOrderOutputDTO implements Serializable {
    private UUID productId;
    private String productName;
    private Integer productQuantity;
}
