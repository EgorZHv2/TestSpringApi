package springApp.core.application.dto.product;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import springApp.core.domain.enums.ProductStatus;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;
@NoArgsConstructor
@Getter
@Setter
public class ProductOutputDTO implements Serializable {
    private UUID id;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer leftInStock;
    private ProductStatus productStatus;
    private UUID categoryId;

}
