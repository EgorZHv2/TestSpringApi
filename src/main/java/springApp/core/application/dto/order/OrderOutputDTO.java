package springApp.core.application.dto.order;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class OrderOutputDTO  implements Serializable {
   private UUID orderId;
   private List<ProductsInOrderOutputDTO> products;
}
