package springApp.core.application.dto.product;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import springApp.core.domain.enums.ProductStatus;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;
@Getter
@Setter
@NoArgsConstructor
public class ProductCreateDTO implements Serializable {
    private String name;
    private String description;
    private BigDecimal price;
    private Integer leftInStock;
    private ProductStatus productStatus;
    private UUID categoryId;


}
